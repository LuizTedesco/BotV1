
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jalt.model.action.Action;

import bwapi.*;
import bwta.BWTA;
import bwta.BaseLocation;

public class TestBot1 extends DefaultBWListener {

    private Mirror mirror = new Mirror();
    private Game game;
    private Player self;
    private Player enemy;
    SCBot mybot = new SCBot();
    Action actionToPerform;
	private int match;
    
    public void run() {
        mirror.getModule().setEventListener(this);
        mirror.startGame();
    }

    @Override
    public void onUnitCreate(Unit unit) {
        System.out.println("New unit discovered " + unit.getType());
    }

    
    public void onEnd() {
    	FileOutputStream qtable;
		try {
			qtable = new FileOutputStream("path.out");
			ObjectOutputStream oos = new ObjectOutputStream(qtable);
	    	oos.writeObject(mybot.q);
	    	oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private double[][] loadQTable() {
		try {
			FileInputStream fin = new FileInputStream("path.out");
			ObjectInputStream ois = new ObjectInputStream(fin);
			mybot.q = (double[][]) ois.readObject();
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mybot.q;
	}
    
    @Override
    public void onStart() {
        game = mirror.getGame();
        self = game.self();
        enemy = game.enemy();
        init();
        

        //Use BWTA to analyze map
        //This may take a few minutes if the map is processed first time!
        System.out.println("Analyzing map...");
        BWTA.readMap();
        BWTA.analyze();
        System.out.println("Map data ready");
        
        int i = 0;
        for(BaseLocation baseLocation : BWTA.getBaseLocations()){
        	System.out.println("Base location #" + (++i) + ". Printing location's region polygon:");
        	for(Position position : baseLocation.getRegion().getPolygon().getPoints()){
        		System.out.print(position + ", ");
        	}
        	System.out.println();
        }

    }

    private void init() {
		//loadMatchs();
		match++;
		mybot.init((double[][]) loadQTable());
	}
    
    private Object getState(Unit myUnit){
    	
    	double myHpLife = myUnit.getHitPoints();
    	double contHpLife = 0.d;
    	int contnumberOfEnemyUnitsThatCanBeAttacked = 0;
		int contnumberOfEnemyUnitsThatCanAttackMe = 0;
			for (Unit enemyUnit : enemy.getUnits()) {
				contHpLife+=enemyUnit.getHitPoints();
				if(myUnit.isInWeaponRange(enemyUnit))
					contnumberOfEnemyUnitsThatCanAttackMe++;
				if(enemyUnit.isInWeaponRange(myUnit))
					contnumberOfEnemyUnitsThatCanBeAttacked++;
			}
		
    	return myUnit;
    }

	@Override
    public void onFrame() {

		double contHpLife = 0.d;
		int contnumberOfEnemyUnitsThatCanBeAttacked = 0;
		int contnumberOfEnemyUnitsThatCanAttackMe = 0;
		double attckOrExploreOrFleeMark = 0;
		
		for (Unit myUnit : self.getUnits()) {
			for (Unit enemyUnit : enemy.getUnits()) {
				contHpLife+=enemyUnit.getHitPoints();
				if(myUnit.isInWeaponRange(enemyUnit))
					contnumberOfEnemyUnitsThatCanAttackMe++;
				if(enemyUnit.isInWeaponRange(myUnit))
					contnumberOfEnemyUnitsThatCanBeAttacked++;
			}
			actionToPerform = mybot.step1(myUnit.getHitPoints(),contHpLife,contnumberOfEnemyUnitsThatCanBeAttacked,contnumberOfEnemyUnitsThatCanAttackMe);
			if(actionToPerform.equals(Actions.ATTACK)){
				attckOrExploreOrFleeMark = attack(myUnit);
			}else if(actionToPerform.equals(Actions.EXPLORE)){
				attckOrExploreOrFleeMark = explore(myUnit);
			}else{
				attckOrExploreOrFleeMark = flee(myUnit);
			}
			mybot.step2(attckOrExploreOrFleeMark);
			
		}
		
		
		
		//game.setTextSize(10);
        game.drawTextScreen(10, 10, "Playing as " + self.getName() + " - " + self.getRace());

        StringBuilder units = new StringBuilder("My units:\n");

        //iterate through my units
        for (Unit myUnit : self.getUnits()) {
            units.append(myUnit.getType()).append(" ").append(myUnit.getTilePosition()).append("\n");

            //if there's enough minerals, train an SCV
            if (myUnit.getType() == UnitType.Terran_Command_Center && self.minerals() >= 50) {
                myUnit.train(UnitType.Terran_SCV);
            }

            //if it's a worker and it's idle, send it to the closest mineral patch
            if (myUnit.getType().isWorker() && myUnit.isIdle()) {
                Unit closestMineral = null;

                //find the closest mineral
                for (Unit neutralUnit : game.neutral().getUnits()) {
                    if (neutralUnit.getType().isMineralField()) {
                        if (closestMineral == null || myUnit.getDistance(neutralUnit) < myUnit.getDistance(closestMineral)) {
                            closestMineral = neutralUnit;
                        }
                    }
                }

                //if a mineral patch was found, send the worker to gather it
                if (closestMineral != null) {
                    myUnit.gather(closestMineral, false);
                }
            }
        }

        //draw my units on screen
        game.drawTextScreen(10, 25, units.toString());
    }

	private double flee(Unit myUnit) {
		System.out.println("flee");
		myUnit.move(new Position(-myUnit.getPoint().getX(),-myUnit.getPoint().getY()));
		return -0.01;
	}

	private double explore(Unit myUnit) {
		System.out.println("explore");
		myUnit.move(new Position(2*myUnit.getPoint().getX(),2*myUnit.getPoint().getY()));
		return -0.001;
	}

	private int attack(Unit myUnit) {
		System.out.println("attack");
		int enemyHpT0,enemyHpT1;
		for (Unit enemyUnit : enemy.getUnits()) {
			if(myUnit.isInWeaponRange(enemyUnit)){
				myUnit.stop();
				enemyHpT0 = enemyUnit.getHitPoints();
				myUnit.attack(enemyUnit.getPosition());
				enemyHpT1 = enemyUnit.getHitPoints();
				return enemyHpT0-enemyHpT1;
			}
				
		}
		return 0;
	}

	public static void main(String[] args) {
        new TestBot1().run();
    }
}
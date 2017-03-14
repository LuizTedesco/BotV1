package scrl.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import bwapi.Color;
import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;
import bwapi.Position;
import bwapi.Unit;
import bwta.BWTA;
import scrl.SCRL;
import scrl.algorithm.QTable;
import scrl.model.Actions;
import scrl.model.PolicyDataStructure;
import scrl.model.SecondaryDataStructure;
import scrl.model.UnitState;
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;

public class TestBotSC1 extends DefaultBWListener {

	public static final int MAX_GAMES = 15;
	private static final boolean DEBUG = true;
	private static final boolean printer = true;

	private Mirror mirror = new Mirror();
	private Game game;
	private Player self;
	private SCRL rl;
	static File outFile = new File("teste1.txt");
	private int initCounter = 1;
	private static int match = 0;
	public int actionCounter = 0;
	private int winCounter = 0;
	private int lossCounter = 0;
	private static BufferedWriter writer;
	public CopyOnWriteArrayList<Unit> avaiableUnitsList;
	private ConcurrentHashMap<Unit, SecondaryDataStructure> dataSet = new ConcurrentHashMap<>();
	List<PolicyDataStructure> policyDataList = new ArrayList();
	private int counter = 0;
	private int atckOrderCounter = 0;
	private int atckRWCounter = 0;
	private int exploreOrderCounter = 0;
	private int exploreRWCounter = 0;
	private int fleeOrderCounter = 0;
	private int fleeRWCounter = 0;

	public void run() {
		avaiableUnitsList = new CopyOnWriteArrayList<Unit>();
		try {
			writer = new BufferedWriter(new FileWriter(outFile, true));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}

	@Override
	public void onStart() {
		game = mirror.getGame();
		self = game.self();

		BWTA.readMap();
		BWTA.analyze();
		
		game.setGUI(false);
		game.setLocalSpeed(0);

		init();
	}

	private void init() {
		rl = new SCRL();
		log("match N: " + match);
		rl.init(match);
		/*rl.initTeste(match);*/
		setInitCounter(getInitCounter() + 1);

	}
	

	@Override
	public void onFrame() {
		for (Unit unit : self.getUnits()) {
			if (unit.isIdle()) {
				System.out.println("Idle Game Frame: "+ game.getFrameCount());
				UnitState curState = getCurrentState(unit);
				Actions actionToPerform = rl.getNextAction(curState);
				
				if(dataSet.containsKey(unit)) // ta relacionada?
				{
					System.out.println("Contains");
					if(dataSet.get(unit).rewardedAction == true) // foi recompensada? pr�xima acao
					{
						System.out.println("Rewarded");
						executeAction(actionToPerform, unit);	// AGIR
						SecondaryDataStructure info = new SecondaryDataStructure(actionToPerform,(double)(game.getFrameCount()),curState, false);
						dataSet.put(unit, info);
					}else // caso contr�rio, recompense
					{
						System.out.println("Not Rewarded");
						switch (dataSet.get(unit).choosenAction) {
						case ATTACK:
							if(dataSet.get(unit).givenOrderFrame + 35 <= game.getFrameCount())
							{
								atckRWCounter++;
								System.out.println("Go RW F() 1");
								counter++;
								UnitState newState = getCurrentState(unit);
								rl.updateState(dataSet.get(unit).choosenAction,dataSet.get(unit).currentState, newState);
								dataSet.get(unit).rewardedAction = true;
							}
							
							break;
						case FLEE:
							if(dataSet.get(unit).givenOrderFrame + 30 <= game.getFrameCount())
							{
								fleeRWCounter++;
								System.out.println("Go RW F() 2");
								counter++;
								UnitState newState = getCurrentState(unit);
								rl.updateState(dataSet.get(unit).choosenAction,dataSet.get(unit).currentState, newState);
								dataSet.get(unit).rewardedAction = true;			
							}
							
							break;
						case EXPLORE:
							if(dataSet.get(unit).givenOrderFrame + 85 <= game.getFrameCount())
							{
								exploreRWCounter++;
								System.out.println("Go RW F() 3 ");
								counter++;
								UnitState newState = getCurrentState(unit);
								rl.updateState(dataSet.get(unit).choosenAction,dataSet.get(unit).currentState, newState);
								dataSet.get(unit).rewardedAction = true;					
							}
							break;
						default:
							System.out.println("Chora");
							break;
						}
					}
					
				}else
				{
					System.out.println("No Contains");
					executeAction(actionToPerform, unit);	// AGIR
					SecondaryDataStructure info = new SecondaryDataStructure(actionToPerform,(double)(game.getFrameCount()),curState, false);
					dataSet.put(unit, info);
				}				
			}/*else if(unit.isUnderAttack())
			{
				
			}*/
		}
	}

	@Override
	public void onEnd(boolean isWinner) {
		/*printOrganizedStateList();*/
		rl.end();
		if (isWinner) {
			winCounter++;
		} else {
			lossCounter++;
		}
		match++;
		if (match == MAX_GAMES) {
			if (DEBUG) {
				try {
					log("actionCounter: " + actionCounter);
					log("winCounter: " + winCounter);
					log("lossCounter: " + lossCounter);
					
					log("atckOrderCounter: " + atckOrderCounter);
					log("atckRWCounter: " + atckRWCounter);
					log("exploreOrderCounter: " + exploreOrderCounter);
					log("exploreRWCounter: " + exploreRWCounter);
					log("fleeOrderCounter: " + fleeOrderCounter);
					log("fleeRWCounter: " + fleeRWCounter);
					
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("actionCounter: " + actionCounter);
			System.out.println("rewardsCounter: " + counter);
			System.out.println("winCounter: " + winCounter);
			System.out.println("lossCounter: " + lossCounter);
			if (printer)
			{
				printPolicy();
				printQ();
			}
			System.exit(0);
		}
	}

	public void executeActionStop(Unit me) {
		me.stop(false);
	}

	public void executeAction(Actions actionToPerform, Unit me) {
		/*log("ACTION TO PERFORM: "+ actionToPerform);*/
		
		actionCounter++;
		if (actionToPerform.equals(Actions.ATTACK)) {
			atckOrderCounter++;
			attack2(me);
//			attack(me);
		} else if (actionToPerform.equals(Actions.FLEE)) {
			fleeOrderCounter++;
			flee(me);
		} else {
			exploreOrderCounter++;
			explore(me);
		}
	}
	

	private void flee(Unit myUnit) {
		Position safePlace = getSaferPlace(myUnit);
		if (safePlace.isValid() && myUnit.exists()) {
//			System.out.println("SafePlace Is Valid");
			myUnit.move(safePlace);
		} else {
			System.out.println("SafePlace Is Not Valid, DO NOTHING");
		}
	}
	

	private Position getSaferPlace(Unit myUnit) {
		int myUnitX = myUnit.getPosition().getX();
		int myUnitY = myUnit.getPosition().getY();
		int numberofEnemiesOnUpperRight = 0;
		int numberofEnemiesOnLowerRight = 0;
		int numberofEnemiesOnUpperLeft = 0;
		int numberofEnemiesOnLowerLeft = 0;
		int enemyX = 0;
		int enemyY = 0;
		double dist = 0.0;
		int numberOfEnemyUnits = game.enemy().getUnits().size();
		
		for (Unit enemyUnit : myUnit.getUnitsInRadius(320)) {
			enemyX = enemyUnit.getPosition().getX();
			enemyY = enemyUnit.getPosition().getY();
			dist += myUnit.getDistance(enemyUnit);
			if (enemyX > myUnitX) {
				if (enemyY > myUnitY) {
					numberofEnemiesOnUpperRight++;
				} else {
					numberofEnemiesOnLowerRight++;
				}
			} else {
				if (enemyY > myUnitY) {
					numberofEnemiesOnUpperLeft++;
				} else {
					numberofEnemiesOnLowerLeft++;
				}
			}
		}
		
		Random generator = new Random();
		int low = -50;
		int high = 50;
		int aux1 = generator.nextInt(high - low) + low;
		int aux2 = generator.nextInt(high - low) + low;

		Position safePlace = new bwapi.Position(myUnitX + aux1, myUnitY + aux2);
		if (numberofEnemiesOnUpperRight > numberofEnemiesOnUpperLeft
				|| numberofEnemiesOnLowerRight > numberofEnemiesOnLowerLeft) {
			System.out.println("Esquerda");
			// esquerda
			if (numberofEnemiesOnLowerLeft > numberofEnemiesOnUpperLeft
					|| numberofEnemiesOnLowerRight > numberofEnemiesOnUpperRight) {
				// cima
				System.out.println("Cima");
				safePlace = new bwapi.Position(myUnitX - (int) (dist / numberOfEnemyUnits),
						myUnitY + (int) (dist / numberOfEnemyUnits));
			} else if (numberofEnemiesOnUpperLeft > numberofEnemiesOnLowerLeft
					|| numberofEnemiesOnUpperRight > numberofEnemiesOnLowerRight) {
				// baixo
				System.out.println("Baixo");
				safePlace = new bwapi.Position(myUnitX - (int) (dist / numberOfEnemyUnits),
						myUnitY - (int) (dist / numberOfEnemyUnits));
			}
		} else if (numberofEnemiesOnUpperLeft > numberofEnemiesOnUpperRight
				|| numberofEnemiesOnLowerLeft > numberofEnemiesOnLowerRight) {
			System.out.println("Direita");
			// direita
			if (numberofEnemiesOnLowerLeft > numberofEnemiesOnUpperLeft
					|| numberofEnemiesOnLowerRight > numberofEnemiesOnUpperRight) {
				// cima
				System.out.println("Cima");
				safePlace = new bwapi.Position(myUnitX + (int) (dist / numberOfEnemyUnits),
						myUnitY + (int) (dist / numberOfEnemyUnits));
			} else if (numberofEnemiesOnUpperLeft > numberofEnemiesOnLowerLeft
					|| numberofEnemiesOnUpperRight > numberofEnemiesOnLowerRight) {
				// baixo
				System.out.println("Baixo");
				safePlace = new bwapi.Position(myUnitX + (int) (dist / numberOfEnemyUnits),
						myUnitY - (int) (dist / numberOfEnemyUnits));
			}
		}
//		System.out.println("return do getSaferPlace");
		return safePlace;
	}

	private void explore(Unit myUnit) {
		boolean flag = true;
		Random generator = new Random();
		for( int i = 0; i<10 && flag; i++)
		{
			int low = -200;
			int high = 200;
			int aux1 = generator.nextInt(high - low) + low;
			int aux2 = generator.nextInt(high - low) + low;
			game.drawCircleMap(myUnit.getPosition().getX() + (aux1), myUnit.getPosition().getY() + (aux2), 15, Color.Blue);
			game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), "EXPLORE");

			Position exploreLocation = new bwapi.Position(myUnit.getPosition().getX() + (aux1),
					myUnit.getPosition().getY() + (aux2));
			if (exploreLocation.isValid()) {
				System.out.println("exploreLocation is Valid");
				myUnit.move(exploreLocation, false);
				flag = false;
			} else {
				System.out.println("exploreLocation is NOT Valid");
				System.out.println("exploreLocation try Again. Cont: "+ i);
			}
		}
	}
	
	private void attack2(Unit myUnit) {

		int aux;
		int aux2;
		int closest = 99999;
		int lowestLife = 99999;
		int lowestUnitId = 0;
		@SuppressWarnings("unused")
		int closestUnitId = 0;
		
		
		for (Unit enemyUnit : game.enemy().getUnits()) {
			aux = myUnit.getDistance(enemyUnit);
			if (aux < closest) {
				closest = aux;
				closestUnitId = enemyUnit.getID();
			}
			aux2 = enemyUnit.getHitPoints();
			if (aux2 < lowestLife) {
				lowestLife = aux2;
				lowestUnitId = enemyUnit.getID();
			}
		}
		for (Unit enemyUnit : game.enemy().getUnits()) {
			if (lowestUnitId == enemyUnit.getID())
			{
				myUnit.attack(enemyUnit.getPosition());
			}
		}
	}

	private void attack(Unit myUnit) {
		System.out.println("ATTACK");
		int aux;
		int aux2;
		int closest = 9999;
		int lowestLife = 9999;
		int lowestUnitId = 0;
		int closestUnitId = 0;
		
		for (Unit enemyUnit : game.enemy().getUnits()) {
			
//			IMPORTANTE
//		for (Unit enemyUnit : discoveredEnemiesUnits) {

			aux = myUnit.getDistance(enemyUnit);
			if (aux < closest) {
				closest = aux;
				closestUnitId = enemyUnit.getID();
			}
			aux2 = enemyUnit.getHitPoints();
			if (aux2 < lowestLife) {
				lowestLife = aux2;
				lowestUnitId = enemyUnit.getID();
			}
		}
		boolean flag = true;
		for (Unit enemyUnit : game.enemy().getUnits()) {
			if (lowestUnitId == enemyUnit.getID()) {
				System.out.println("Attack this unit");
				System.out.println("lowestUnitId: "+lowestUnitId);
				System.out.println("enemyUnit.getID()"+enemyUnit.getID());
				kitting(myUnit,enemyUnit);
				flag = false;
				break;
			}
		}
		if (flag)
		{
			explore(myUnit);
//			findEnemy(myUnit);
		}
	}

	private void kitting(Unit myUnit, Unit enemyUnit) {
		System.out.println("kitting");
		boolean flag = true;
		while(flag && myUnit.isInWeaponRange(enemyUnit))
		{
			log("While");
			System.out.println("while");
			if(myUnit.canAttack(enemyUnit.getPosition()) && myUnit.getLastCommandFrame() <= game.getFrameCount() && myUnit.isAttackFrame())
			{
				log("if");
				System.out.println("if");
				myUnit.attack(enemyUnit);
			}

			Position safePlace = getSaferPlace(myUnit);
			if (safePlace.isValid() && myUnit.exists() && myUnit.canMove() && myUnit.getLastCommandFrame() < game.getFrameCount()) {
				log("if do While with SafePlace");
				System.out.println("if do While with SafePlace");
				myUnit.move(safePlace);
				log("after move to safeplace");
			}else
			{
				log("SafePlace do Kitting is not Valid");
				System.out.println("SafePlace do Kitting is not Valid");
				flag = false;
				
			}

			if(!enemyUnit.exists() ||  !myUnit.exists() || myUnit.getDistance(enemyUnit) > 50)
			{
				log("if final do kitting");
				System.out.println("if. Flag is going to False");
				flag = false;
			}
			myUnit.stop();
		}
	}

	private UnitState getCurrentState(Unit givenUnit) {
		Unit me = givenUnit;
		double contHpEnemyLife = 0.d;
		double contHpAlliesLife = 0.d;
		double mediumHpFromNearbyEnemies = 0.d;
		int numberOfEnemiesUnitsNearby = 0;
		double mediumHpFromNearbyAllies = 0.d;
		int numberOfAlliesUnitsNearby = 0;
		int distanceToClosestEnemyUnit = 400000;

		List<Unit> units = me.getUnitsInRadius(300);
		for (Unit unit : units) {
			if (unit.getPlayer().isAlly(self)) {
				contHpAlliesLife += unit.getHitPoints();
				numberOfAlliesUnitsNearby++;
			} else if (unit.getPlayer().isEnemy(self)) {
				contHpEnemyLife += unit.getHitPoints();
				numberOfEnemiesUnitsNearby++;
				distanceToClosestEnemyUnit = me.getDistance(unit) < distanceToClosestEnemyUnit ? me.getDistance(unit)
						: distanceToClosestEnemyUnit;
			} else {
				System.out.println("Cai no else em alguma vez?");
			}
		}
		// ADDING me to this COUNTer
		contHpAlliesLife += me.getHitPoints();
		numberOfAlliesUnitsNearby++;

		if (numberOfEnemiesUnitsNearby != 0)
			mediumHpFromNearbyEnemies = contHpEnemyLife / numberOfEnemiesUnitsNearby;
		if (numberOfAlliesUnitsNearby != 0)
			mediumHpFromNearbyAllies = contHpAlliesLife / numberOfAlliesUnitsNearby;

		UnitState curState = new UnitState(mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby,
				mediumHpFromNearbyAllies, numberOfAlliesUnitsNearby);

		return curState;
	}

	public static void log(String msg) {
		if (DEBUG) {
			if (!outFile.isFile()) {
				try {
					outFile.createNewFile();
					writer.append(msg);
					writer.newLine();
					writer.flush();
					// writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					writer.append(msg);
					writer.newLine();
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public int getInitCounter() {
		return initCounter;
	}

	public void setInitCounter(int initCounter) {
		this.initCounter = initCounter;
	}
	
	public void printOrganizedStateList(){
		List<UnitState> stado = new ArrayList<>();
		for (RangeHP mediumHpFromNearbyEnemies : RangeHP.values()) {
			for (RangeUnits numberOfEnemiesUnitsNearby : RangeUnits.values() ) {
				for (RangeHP hpFromNearbyAllies : RangeHP.values() ) {
					for (RangeUnits numberOfAlliesUnitsNearby : RangeUnits.values() ) {
							UnitState newUnit = new UnitState(mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, hpFromNearbyAllies, numberOfAlliesUnitsNearby);
							stado.add(newUnit);
					}
				}
			}
		}
		PrintWriter qwriter;
		try {
			qwriter = new PrintWriter("states.txt", "UTF-8");
			for (UnitState unitState : stado) {
				qwriter.println(unitState.toString2());
			}
		    qwriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    
	
	}
	
	
	private void printPolicy()
	{
		QTable qT;
		try {
			FileInputStream fis = new FileInputStream("marineTable.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			qT = (QTable) ois.readObject();
			Map<Actions, Double> map;
			for (UnitState state : qT.keySet()) {
				PolicyDataStructure policyData;
				double max = Double.NEGATIVE_INFINITY;
				map = qT.get(state);
				Actions bestAction = null;
				for (Actions act : map.keySet()) {
					if (map.get(act) > max) {
						max = map.get(act);
						bestAction = act;
					}
				}
				policyData = new PolicyDataStructure(state, bestAction);
				policyDataList.add(policyData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
		    PrintWriter qwriter = new PrintWriter("policy.txt", "UTF-8");
		    qwriter.println(policyDataList.toString());
		    qwriter.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	private void printQ()
	{
		
		QTable qT;
		try {
			FileInputStream fis = new FileInputStream("marineTable.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			qT = (QTable) ois.readObject();
			try{
			    PrintWriter qwriter = new PrintWriter("qTable.txt", "UTF-8");
			    qwriter.println(qT.toString());
			    qwriter.close();
			} catch (IOException e) {
			   // do something
			}
			
			ois.close();
		} catch (Exception e) {
			// System.out.println("File nao abriu");
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		new TestBotSC1().run();
	}
}
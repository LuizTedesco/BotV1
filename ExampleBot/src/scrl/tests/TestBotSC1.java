package scrl.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

import bwapi.Color;
import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;
import bwapi.Position;
import bwapi.Unit;
import bwta.BWTA;
import scrl.SCRL;
import scrl.model.Actions;
import scrl.model.UnitState;

public class TestBotSC1 extends DefaultBWListener {

	public static final int MAX_GAMES = 2000;
	private static final boolean DEBUG = false;
	private Mirror mirror = new Mirror();
	private Game game;
	private Player self;
	private SCRL rl;
	static File outFile = new File("teste1.txt");
	private int initCounter = 1;
	private static int match = 0;
	// ExecutorService executor = Executors.newFixedThreadPool(5);
	public int actionCounter = 0;
	private int winCounter = 0;
	private int lossCounter = 0;
	private static BufferedWriter writer;
	public List<Unit> avaiableUnitsList;
	public List<Unit> discoveredEnemiesUnits;


	// Funcao 1
	public void run() {
		// System.out.println("TestBotSC1 RUN");
		avaiableUnitsList = new ArrayList<Unit>();
		try {
			System.out.println("Created the outFile");

			writer = new BufferedWriter(new FileWriter(outFile, true));
		} catch (IOException e1) {
			System.out.println("Did NOT Create the outFile");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}

	// Funcao 2
	@Override
	public void onStart() {
		log("Entrou na fun��o onStart");
		game = mirror.getGame();
		self = game.self();

		BWTA.readMap();
		BWTA.analyze();

//		game.setLocalSpeed(15);
		
		game.setGUI(false);
		game.setLocalSpeed(0);

		init();
	}

	// Funcao 3
	private void init() {
		rl = new SCRL();
		log("match N: " + match);
		rl.init(match);
		setInitCounter(getInitCounter() + 1);
	}

	@Override
	public void onFrame() {
//		log("OnFrame ");
		for (Unit unit : self.getUnits()) {
			game.drawCircleMap(unit.getPosition().getX(), unit.getPosition().getY(), 50, Color.Brown);
			game.drawCircleMap(unit.getPosition().getX(), unit.getPosition().getY(), 100, Color.Cyan);
			game.drawCircleMap(unit.getPosition().getX(), unit.getPosition().getY(), 150, Color.Green);
			game.drawCircleMap(unit.getPosition().getX(), unit.getPosition().getY(), 200, Color.Orange);
			game.drawCircleMap(unit.getPosition().getX(), unit.getPosition().getY(), 300, Color.Red);
			
			if (unit.isIdle()) {
				log("                                                     ");
				System.out.println("FrameCount: " + game.getFrameCount());
				System.out.println("Unit Id OnFrame: " + unit.getID());
				UnitState curState = getCurrentState(unit);
				Actions actionToPerform = rl.getNextAction(curState);
				executeAction(actionToPerform, unit);
				// colocar um wait?
				UnitState newState = getCurrentState(unit);
				rl.updateState(actionToPerform, curState, newState);
				game.drawCircleMap(unit.getPosition().getX(), unit.getPosition().getY(), 25, Color.Green);
				game.drawTextMap(unit.getPosition().getX() + 30, unit.getPosition().getY() + 30, "IDLE  -  UNIT");
//				log("Fim OnFrame");
//				log("                                                                            ");
			}
		}
	}

	@Override
	public void onEnd(boolean isWinner) {
		System.out.println("TestBotSC1 onEnd");
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
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("actionCounter: " + actionCounter);
			System.out.println("winCounter: " + winCounter);
			System.out.println("lossCounter: " + lossCounter);
			System.exit(0);
		}
	}

	public void executeActionStop(Unit me) {
		me.stop(false);
	}

	public void executeAction(Actions actionToPerform, Unit me) {
		log("ACTION TO PERFORM: "+ actionToPerform);
		actionCounter++;
		System.out.println("Unit Id Execute Action: " + me.getID());
		System.out.println(actionToPerform);
		if (actionToPerform.equals(Actions.ATTACK)) {
			attack2(me);
//			attack(me);
		} else if (actionToPerform.equals(Actions.FLEE)) {
			flee(me);
		} else {
			explore(me);
		}
		System.out.println("Unit Id Execute Action FIM: " + me.getID());
		System.out.println("Fim da ordem da ac�o");
		System.out.println("Unit is already Idle?");
//		log("Unit is already Idle? "+ me.isIdle());
		System.out.println(me.isIdle());
	}
	

	private void flee(Unit myUnit) {
		Position safePlace = getSaferPlace(myUnit);
		if (safePlace.isValid() && myUnit.exists()) {
			System.out.println("SafePlace Is Valid");
			myUnit.move(safePlace);
		} else {
			System.out.println("SafePlace Is Not Valid, DO NOTHING");
//			explore(myUnit);
//			myUnit.stop();
		}
	}
	

	private Position getSaferPlace(Unit myUnit) {
		System.out.println("getSaferPlace");
		
		int myUnitX = myUnit.getPosition().getX();
		int myUnitY = myUnit.getPosition().getY();
		int numberofEnemiesOnUpperRight = 0;
		int numberofEnemiesOnLowerRight = 0;
		int numberofEnemiesOnUpperLeft = 0;
		int numberofEnemiesOnLowerLeft = 0;
		int enemyX = 0;
		int enemyY = 0;
//		int distX = 0;
//		int distY = 0;
//		double distXElevated = 0;
//		double distYElevated = 0;
		double dist = 0.0;
		// double distMedia = 0.0;
		int numberOfEnemyUnits = game.enemy().getUnits().size();
		
//		for (Unit enemyUnit : game.enemy().getUnits()) {
		for (Unit enemyUnit : myUnit.getUnitsInRadius(320)) {
			enemyX = enemyUnit.getPosition().getX();
			enemyY = enemyUnit.getPosition().getY();
//			distX = enemyX - myUnitX;
//			distY = enemyY - myUnitY;
//			distXElevated = Math.pow(distX, 2);
//			distYElevated = Math.pow(distY, 2);
//			dist += Math.sqrt(distXElevated + distYElevated);
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
		int low = -10;
		int high = 10;
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
		System.out.println("return do getSaferPlace");
		return safePlace;
	}

	private void explore(Unit myUnit) {
		// System.out.println("EXPLORE, avaiableUnitsList.size():
		// "+avaiableUnitsList.size());
		// System.out.println("EXPLORE");
		boolean flag = true;
		Random generator = new Random();
		for( int i = 0; i<10 && flag; i++)
		{
			int low = -150;
			int high = 150;
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
		// TODO como relacionar lowestUnitId & closestUnitId
//		for (Unit enemyUnit : game.enemy().getUnits()) {
//			if (lowestUnitId == enemyUnit.getID())
//				myUnit.attack(enemyUnit);
//		}
		
		
		for (Unit enemyUnit : game.enemy().getUnits()) {
			if (lowestUnitId == enemyUnit.getID())
			{
				myUnit.attack(enemyUnit.getPosition()); 
			}
		}
		
		/*
		 * for (Unit enemyUnit : game.enemy().getUnits()) { if
		 * (myUnit.isInWeaponRange(enemyUnit)) { myUnit.stop();
		 * myUnit.attack(enemyUnit.getPosition()); break; } }
		 */
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
		// TODO como relacionar lowestUnitId & closestUnitId
//		for (Unit enemyUnit : myUnit.getUnitsInRadius(320)) {
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

//	private void findEnemy(Unit myUnit) {
//		for (Unit enemyUnit : discoveredEnemiesUnits) {
//			
//		}
//		myUnit.attack(discoveredEnemiesUnits.iterator())
//		
//	}

	private void kitting(Unit myUnit, Unit enemyUnit) {
		System.out.println("kitting");
		boolean flag = true;
		while(flag && myUnit.isInWeaponRange(enemyUnit))
		{
			log("While");
			System.out.println("while");
//			if(myUnit.canAttack(enemyUnit.getPosition()) && myUnit.getLastCommandFrame() <= game.getFrameCount())
			if(myUnit.canAttack(enemyUnit.getPosition()) && myUnit.getLastCommandFrame() <= game.getFrameCount() && myUnit.isAttackFrame())
			{
				//				unit.isStartingAttack()
				//				unit.isAttackFrame()
				//				unit.isAttacking()
				log("if");
				System.out.println("if");
				myUnit.attack(enemyUnit);
			}

			Position safePlace = getSaferPlace(myUnit);
			if (safePlace.isValid() && myUnit.exists() && myUnit.canMove() && myUnit.getLastCommandFrame() < game.getFrameCount()) {
				log("if do While with SafePlace");
				System.out.println("if do While with SafePlace");
//				myUnit.move(safePlace, false);
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
//			System.out.println("for (Unit unit : units)");
			if (unit.getPlayer().isAlly(self)) {
				contHpAlliesLife += unit.getHitPoints();
				numberOfAlliesUnitsNearby++;
			} else if (unit.getPlayer().isEnemy(self)) {
//				if(!discoveredEnemiesUnits.contains(unit.id))
//				{
//					System.out.println("if do !discoveredEnemiesUnits.contains(unit)");
//					discoveredEnemiesUnits.add(unit);
//				}else
//				{
//					System.out.println("Else do !discoveredEnemiesUnits.contains(unit)");
//				}
					
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

		UnitState curState = new UnitState(me.getHitPoints(), mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby,
				mediumHpFromNearbyAllies, numberOfAlliesUnitsNearby, distanceToClosestEnemyUnit);

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

	public static void main(String[] args) {
		new TestBotSC1().run();
	}
}
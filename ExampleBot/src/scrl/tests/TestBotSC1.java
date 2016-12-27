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

	public static final int MAX_GAMES = 500;
	private static final boolean DEBUG = true;
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
		// log("função RUN DENTRO DE TESTBOTSC1, Primeiro item a ser chamado");
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}

	// Funcao 2
	@Override
	public void onStart() {
		log("Entrou na função onStart");
		game = mirror.getGame();
		self = game.self();

		 game.setLocalSpeed(0);
		 game.setGUI(false);

		BWTA.readMap();
		BWTA.analyze();

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
		for (Unit unit : self.getUnits()) {
			if (unit.isIdle()) {
				log("Unit Is Idle, FrameStart");
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
				log("                                                                            ");
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
		log("Execute Action: "+ actionToPerform);
		actionCounter++;
		System.out.println("Unit Id Execute Action: " + me.getID());
		System.out.println(actionToPerform);
		if (actionToPerform.equals(Actions.ATTACK)) {
			attack(me);
		} else if (actionToPerform.equals(Actions.FLEE)) {
			flee(me);
		} else {
			explore(me);
		}
		System.out.println("Unit Id Execute Action FIM: " + me.getID());
		System.out.println("Fim da ordem da acão");
		System.out.println("Unit is already Idle?");
		log("Unit is already Idle? "+ me.isIdle());
		System.out.println(me.isIdle());
	}

	private void flee(Unit myUnit) {
		Position safePlace;
		// System.out.println("Funcao flee");
		int myUnitX = myUnit.getPosition().getX();
		int myUnitY = myUnit.getPosition().getY();
		int numberofEnemiesOnUpperRight = 0;
		int numberofEnemiesOnLowerRight = 0;
		int numberofEnemiesOnUpperLeft = 0;
		int numberofEnemiesOnLowerLeft = 0;
		int enemyX = 0;
		int enemyY = 0;
		int distX = 0;
		int distY = 0;
		double distXElevated = 0;
		double distYElevated = 0;
		double dist = 0.0;
		// double distMedia = 0.0;
		int numberOfEnemyUnits = game.enemy().getUnits().size();
		for (Unit enemyUnit : game.enemy().getUnits()) {
			enemyX = enemyUnit.getPosition().getX();
			enemyY = enemyUnit.getPosition().getY();
			distX = enemyX - myUnitX;
			distY = enemyY - myUnitY;
			distXElevated = Math.pow(distX, 2);
			distYElevated = Math.pow(distY, 2);
			dist += Math.sqrt(distXElevated + distYElevated);
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

		safePlace = new bwapi.Position(myUnitX - 15, myUnitY - 15);
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
		} else {
			System.out.println("Else do Flee");
		}

		// if(numberofEnemiesOnUpperRight > numberofEnemiesOnLowerRight)
		// { // ir pra baixo
		// if(numberofEnemiesOnUpperLeft > numberofEnemiesOnUpperRight)
		// {
		// //ir pra direita
		// safePlace = new bwapi.Position(myUnitX +
		// (int)(dist/numberOfEnemyUnits),myUnitY -
		// (int)(dist/numberOfEnemyUnits));
		// }
		// else
		// {
		// //ir pra esquerda
		// safePlace = new bwapi.Position(myUnitX -
		// (int)(dist/numberOfEnemyUnits),myUnitY -
		// (int)(dist/numberOfEnemyUnits));
		// }
		// }else{
		// // pra cima
		// if(numberofEnemiesOnUpperLeft > numberofEnemiesOnUpperRight)
		// {
		// //ir pra direita
		// safePlace = new bwapi.Position(myUnitX +
		// (int)(dist/numberOfEnemyUnits),myUnitY +
		// (int)(dist/numberOfEnemyUnits));
		// }
		// else
		// {
		// //ir pra esquerda
		// safePlace = new bwapi.Position(myUnitX -
		// (int)(dist/numberOfEnemyUnits),myUnitY +
		// (int)(dist/numberOfEnemyUnits));
		// }
		// }
		if (safePlace.isValid()) {
			System.out.println("SafePlace Is Valid");
			myUnit.move(safePlace);
		} else {
			System.out.println("SafePlace Is Not Valid");
			explore(myUnit);
		}

		// int distX = 0;
		// int distY = 0;
		// double distXElevated = 0;
		// double distYElevated = 0;
		// double dist = 0.0;
		// double distMedia = 0.0;
		// for (Unit enemyUnit : enemy.getUnits()) {
		// int enemyX = enemyUnit.getPosition().getX();
		// int enemyY = enemyUnit.getPosition().getY();
		// distX = enemyX - myUnitX;
		// distY = enemyY - myUnitY;
		// distXElevated = Math.pow(distX, 2);
		// distYElevated = Math.pow(distY, 2);
		// dist += Math.sqrt(distXElevated+distYElevated);
		// }
		// int numberOfEnemies = enemy.getUnits().size();
		// if(numberOfEnemies != 0)
		// {
		// distMedia = dist / numberOfEnemies;
		// System.out.println("Complex safePlace ");
		// game.drawCircleMap((int)(myUnitX - distMedia/numberOfEnemies),
		// (int)(myUnitY - distMedia/numberOfEnemies), 7, Color.White);
		// safePlace = new bwapi.Position((int)(myUnitX -
		// distMedia/numberOfEnemies), (int)(myUnitY -
		// distMedia/numberOfEnemies));
		// }
		// else
		// {
		// System.out.println("Simples safePlace");
		// safePlace = new bwapi.Position(myUnitX,myUnitY);
		// }
		// game.drawTextMap(myUnit.getPosition().getX(),
		// myUnit.getPosition().getY(), "flee -- GetToSafePlace");
		// if(safePlace.isValid()){
		// System.out.println("safePlace is Valid");
		// myUnit.move(safePlace, false);
		// }
		// else
		// {
		// System.out.println("safePlace is NOT Valid");
		// myUnit.move(new bwapi.Position(myUnitX,myUnitY));
		// }
	}

	private void explore(Unit myUnit) {
		// System.out.println("EXPLORE, avaiableUnitsList.size():
		// "+avaiableUnitsList.size());
		// System.out.println("EXPLORE");
		Random generator = new Random();
		int low = -225;
		int high = 225;
		int aux1 = generator.nextInt(high - low) + low;
		int aux2 = generator.nextInt(high - low) + low;
		game.drawCircleMap(myUnit.getPosition().getX() + (aux1), myUnit.getPosition().getY() + (aux2), 15, Color.Blue);
		game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), "EXPLORE");

		Position exploreLocation = new bwapi.Position(myUnit.getPosition().getX() + (aux1),
				myUnit.getPosition().getY() + (aux2));
		if (exploreLocation.isValid()) {
			System.out.println("exploreLocation is Valid");
			myUnit.move(exploreLocation, false);
		} else {
			System.out.println("exploreLocation is NOT Valid");
		}
	}

	private void attack(Unit myUnit) {
		int aux;
		int aux2;
		boolean areThereEnemiesNearby = false;
		int closest = 9999;
		int lowestLife = 9999;
		int lowestUnitId = 0;
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
		for (Unit enemyUnit : game.enemy().getUnits()) {
			if (lowestUnitId == enemyUnit.getID()) {
				// TODO Talvez esteja errado
				System.out.println("Attack this poor thing");
				myUnit.attack(enemyUnit);
				areThereEnemiesNearby = true;
				// reward ruim para nao atackar?
			}
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

		List<Unit> units = me.getUnitsInRadius(270);
		game.drawCircleMap(me.getPosition().getX(), me.getPosition().getY(), 270, Color.Green);
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
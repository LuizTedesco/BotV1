package scrl.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import bwapi.Color;
import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;
import bwapi.Position;
import bwapi.Unit;
import bwta.BWTA;
import scrl.RLUnitThread;
import scrl.SCRL;
import scrl.model.Actions;

public class TestBotSC1 extends DefaultBWListener {

	public static final int MAX_GAMES = 5;
	private static final boolean DEBUG = false;
	private Mirror mirror = new Mirror();
	private Game game;
	private Player self;
	// private Player enemy;
	private SCRL rl;
	static File outFile = new File("teste1.txt");
	private int initCounter = 1;
	private static int match = 0;
	ExecutorService executor = Executors.newFixedThreadPool(5);
	private int auxCounter = 0;
	@SuppressWarnings("unused")
	private int auxCounter2 = 1;
	private int actionCounter = 0;
	private int winCounter = 0;
	private int lossCounter = 0;
	@SuppressWarnings("unused")
	private int drawCounter = 0;
	private static BufferedWriter writer;
	public List<Unit> avaiableUnitsList;


	// Funcao 1
	public void run() {
		System.out.println("TestBotSC1 RUN");
		avaiableUnitsList = new ArrayList<Unit>();
		try {
			writer = new BufferedWriter(new FileWriter(outFile, true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		TestBotSC1.log("função RUN DENTRO DE TESTBOTSC1, Primeiro item a ser chamado");

		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}

	// Funcao 2
	@Override
	public void onStart() {
		log("Entrou na função onStart");
		game = mirror.getGame();
		self = game.self();

//		game.setLocalSpeed(20);

		BWTA.readMap();
		BWTA.analyze();

		init();
	}

	// Funcao 3
	private void init() {
		rl = new SCRL();
		TestBotSC1.log("match: " + match);

		rl.init(match);
		setInitCounter(getInitCounter() + 1);
		for (Unit unit : self.getUnits()) {
			avaiableUnitsList.add(unit);
			System.out.println("UnitId: "+ unit.getID()+"  Foi adicionado a avaiableUnitsList. Match N: "+ match );
		}
	}
	
	
	//				System.out.println(unit.getLastCommand().isQueued());
	//				unit.isStartingAttack()
	//				unit.isAttackFrame()
	//				unit.isAttacking()

	@Override
	public void onFrame() {
		for (Unit unit : self.getUnits()) {
			if (unit.isIdle() && avaiableUnitsList.contains(unit)) {
				unit.stop(false);
//				if(game.getFrameCount() % 15 == 0 ){
					avaiableUnitsList.remove(unit);
					System.out.println("avaiableUnitsList.size() After Removing: "+avaiableUnitsList.size());
					//					System.out.println("UnitId: "+ unit.getID()+" Foi removido da avaiableUnitsList");
					//					System.out.println("game.getFrameCount(): "+ game.getFrameCount());
					//					System.out.println("unit.getLastCommandFrame(): "+ unit.getLastCommandFrame());
					//						
					//						System.out.println("UnitId: "+ unit.getID()+" is Idle Entrou" );

					//System.out.println("ThisFrame: "+ game.getFrameCount());
					//if(game.getFrameCount()%3 == 0)
					//{
					game.drawCircleMap(unit.getPosition().getX(), unit.getPosition().getY(), 25, Color.Green);
					game.drawTextMap(unit.getPosition().getX() + 30, unit.getPosition().getY() + 30, "IDLE  -  UNIT");
					game.drawTextMap(unit.getPosition().getX() + 10, unit.getPosition().getY() + 10, "UnitId: "+unit.getID()+ " "+ avaiableUnitsList.contains(unit));
					auxCounter++;
					log("Vai chamar o new RLUnitThread");
					Runnable rlUnit = new RLUnitThread(game, rl, unit, self, this, game.enemy());
					// RLUnitThread rlUnit = new RLUnitThread(game, rl, unit, self, this, game.enemy());

					log("Thread Created for idle Unit: Id: " + unit.getID() + " Will execute");
					executor.execute(rlUnit);

					//System.out.println("LastCommandFrame: "+unit.getLastCommandFrame());
					log("Thread Created for idle Unit: Id: " + unit.getID() + " was given execute command");	
					//				}
			}
		}
		log("AuxCounter FRAME : " + auxCounter);
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
			System.out.println("actionCounter: " + actionCounter);
			System.out.println("winCounter: " + winCounter);
			System.out.println("lossCounter: " + lossCounter);

			System.exit(0);
		}

	}
	
	public void executeActionStop(Unit me)
	{
		me.stop(false);
	}

	public void executeAction(Actions actionToPerform, Unit me){
		actionCounter++;
		System.out.println(actionToPerform);
		if (actionToPerform.equals(Actions.ATTACK)) {
			attack(me);
		} else if (actionToPerform.equals(Actions.FLEE)) {
			flee(me);
		} else {
			explore(me);
		}
	}

	private void flee(Unit myUnit){
//		getToSafePlace(myUnit, game.enemy());
		Position safePlace ;
		Player enemy = game.enemy();
		System.out.println("Funcao flee");
		int myUnitX = myUnit.getPosition().getX();
		int myUnitY = myUnit.getPosition().getY();
		int distX = 0;
		int distY = 0;
		double distXElevated = 0;
		double distYElevated = 0;
		double dist = 0.0;
		double distMedia = 0.0;
		for (Unit enemyUnit : enemy.getUnits()) {
			int enemyX = enemyUnit.getPosition().getX();
			int enemyY = enemyUnit.getPosition().getY();
			distX = enemyX - myUnitX;
			distY = enemyY - myUnitY;
			distXElevated = Math.pow(distX, 2);
			distYElevated = Math.pow(distY, 2);
			dist += Math.sqrt(distXElevated+distYElevated);
//			System.out.println("Enemy position:" + enemyX + "," + enemyY);
		}
		int numberOfEnemies = enemy.getUnits().size();
		if(numberOfEnemies != 0)
		{
			distMedia = dist / numberOfEnemies;
			System.out.println("Complex safePlace ");
			game.drawCircleMap((int)(myUnitX - distMedia/numberOfEnemies), (int)(myUnitY - distMedia/numberOfEnemies), 7, Color.White);
			safePlace = new bwapi.Position((int)(myUnitX - distMedia/numberOfEnemies), (int)(myUnitY - distMedia/numberOfEnemies));
			
		}
		else
		{
			System.out.println("Simples safePlace");
			safePlace = new bwapi.Position(myUnitX,myUnitY);
		}
		game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), "flee -- GetToSafePlace");
		if(safePlace.isValid()){
			System.out.println("safePlace is Valid");
			myUnit.move(safePlace, false);
		}
		else
		{
			System.out.println("safePlace is NOT Valid");
			myUnit.move(new bwapi.Position(myUnitX,myUnitY));
		}
	}

	private void explore(Unit myUnit){
		System.out.println("EXPLORE, avaiableUnitsList.size(): "+avaiableUnitsList.size());
		Random generator = new Random();
		int low = -150;
		int high = 150;
		int aux1 = generator.nextInt(high - low) + low;
		int aux2 = generator.nextInt(high - low) + low;
		game.drawCircleMap(myUnit.getPosition().getX() + (aux1), myUnit.getPosition().getY() + (aux2), 15, Color.Blue);
		game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), "EXPLORE");
		
		Position exploreLocation = new bwapi.Position(myUnit.getPosition().getX() + (aux1), myUnit.getPosition().getY() + (aux2));
		if(exploreLocation.isValid()){
			System.out.println("exploreLocation is Valid");
			myUnit.move(exploreLocation,false);
		}else
		{
			System.out.println("exploreLocation is NOT Valid");
		}
		
		
	}

	@SuppressWarnings("unused")
	private void getToSafePlace(Unit myUnit, Player enemy) {
		
		int myUnitX = myUnit.getPosition().getX();
		int myUnitY = myUnit.getPosition().getY();
		int distX = 0;
		int distY = 0;
		for (Unit enemyUnit : enemy.getUnits()) {
			distX += myUnitX - enemyUnit.getPosition().getX();
			distY += myUnitY - enemyUnit.getPosition().getY();
		}
		//game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), "flee -- GetToSafePlace");
		Position gogogo = new bwapi.Position(myUnitX+distX,myUnitY+distY);
		if(gogogo.isValid()){
			System.out.println("gogogo is Valid");
			myUnit.move(gogogo, false);
		}else
		{
			System.out.println("gogogo is NOT Valid");
		}
			
		
//		System.out.println("Funcao getToSafePlace");
//		int myUnitX = myUnit.getPosition().getX();
//		int myUnitY = myUnit.getPosition().getY();
//		int distX = 0;
//		int distY = 0;
//		for (Unit enemyUnit : enemy.getUnits()) {
//			int enemyX = enemyUnit.getPosition().getX();
//			int enemyY = enemyUnit.getPosition().getY();
//			distX += enemyX - myUnitX;
//			distY += enemyY - myUnitY;
//			System.out.println("Enemy position:" + enemyX + "," + enemyY);
//		}
//		game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), "flee -- GetToSafePlace");
//		System.out.println(myUnitX + "," + myUnitY);
//		System.out.println(-distX + "," + -distY);
//		System.out.println("Funcao getToSafePlace");
//		return new bwapi.Position(-distX, -distY);
	}

	private void attack(Unit myUnit){
		int aux;
		int aux2;
		int closest = 9999;
		int lowestLife = 9999;
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
		for (Unit enemyUnit : game.enemy().getUnits()) {
			if (lowestUnitId == enemyUnit.getID())
				myUnit.attack(enemyUnit);
		}

		/*
		 * for (Unit enemyUnit : game.enemy().getUnits()) { if
		 * (myUnit.isInWeaponRange(enemyUnit)) { myUnit.stop();
		 * myUnit.attack(enemyUnit.getPosition()); break; } }
		 */
	}

	public static void log(String msg) {
		if (DEBUG) {
			if (!outFile.isFile())
				try {
					outFile.createNewFile();
					writer.append(msg);
					writer.newLine();
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
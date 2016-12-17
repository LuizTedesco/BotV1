package scrl.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import scrl.model.UnitState;

public class TestBotSC1 extends DefaultBWListener{

	public static final int MAX_GAMES = 10;
	private static final boolean DEBUG = false;
	private Mirror mirror = new Mirror();
	private Game game;
	private Player self;
//	private Player enemy;
	private SCRL rl;
	static File outFile = new File("teste1.txt");
	private int initCounter = 1;
	private static int match = 0;
	ExecutorService executor = Executors.newFixedThreadPool(10);
	private int auxCounter = 0;
	private int auxCounter2 = 1;
	private int actionCounter = 0;
	private int winCounter = 0;
	private int lossCounter = 0;
	private int drawCounter = 0;
	public Set<Unit> myUnitsSet;

	// Funcao 1
	public void run() {
		System.out.println("TestBotSC1 RUN");
		try {
			TestBotSC1.log("função RUN DENTRO DE TESTBOTSC1, Primeiro item a ser chamado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}
	
	// Funcao 2
	@Override
	public void onStart() {
		System.out.println("TestBotSC1 onStart");
		try {
			log("Entrou na função onStart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game = mirror.getGame();
		self = game.self();
		
		//enemy = game.enemy();
		game.setLocalSpeed(20);
		
		BWTA.readMap();
		BWTA.analyze();
		
		init();
	}

	// Funcao 3
	private void init(){
		System.out.println("TestBotSC1 init");
		rl = new SCRL();
		
		try {
			TestBotSC1.log("match: "+ match);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rl.init(match);
		setInitCounter(getInitCounter() + 1);
		System.out.println("ANTES");
		for (Unit unit : self.getUnits()) {
			System.out.println("entroy");
			myUnitsSet.add(unit);
			System.out.println("Add");
		}
		System.out.println("DPS");
	}
	

	@Override
	public void onFrame()
	{
		System.out.println("onFrame");
		
		System.out.println(myUnitsSet.toString());
		
		List<Unit> units = self.getUnits();
		for (Unit unit : units) {
			if(unit.isIdle() && myUnitsSet.contains(unit)){
				myUnitsSet.remove(unit);
				//if(game.getFrameCount()%3 == 0)
				//{
					game.drawCircleMap(unit.getPosition().getX(),unit.getPosition().getY(),15,Color.Orange);
					game.drawTextMap(unit.getPosition().getX() + 25, unit.getPosition().getY() + 25, "IDLE  -  UNIT");
					auxCounter++;
					try {
						log("Vai chamar o new RLUnitThread");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Runnable rlUnit = new RLUnitThread(game, rl, unit, self, this, game.enemy());
					//RLUnitThread rlUnit = new RLUnitThread(game, rl, unit, self, this, game.enemy());
					try {
						log("Thread Created for idle Unit: Id: "+ unit.getID()+ " Will execute");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Future<?> future = executor.submit(rlUnit);
					executor.execute(rlUnit);
					try {
						log("Thread Created for idle Unit: Id: "+ unit.getID()+ " was given execute command");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//}
			}
		}
		try {
			log("AuxCounter FRAME : "+auxCounter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	@Override
	public void onEnd(boolean isWinner) {
		System.out.println("TestBotSC1 onEnd");
		rl.end();
		if(isWinner)
		{
			winCounter++;
		}else
		{
			lossCounter++;
		}
		match++;
		if (match == MAX_GAMES)
		{
			System.out.println("actionCounter: " + actionCounter);
			System.out.println("winCounter: " + winCounter);
			System.out.println("lossCounter: " + lossCounter);
			
			System.exit(0);
		}

	}
	
	public void executeAction(Actions actionToPerform, Unit me) throws IOException{
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

	private void flee(Unit myUnit) throws IOException {
		myUnit.move(getToSafePlace(myUnit,game.enemy()));
	}

	private void explore(Unit myUnit) throws IOException {
		Random generator = new Random();
		int low = -150;
		int high = 150;
		int aux1 = generator.nextInt(high-low)+low;
		int aux2 = generator.nextInt(high-low)+low;
		myUnit.move(new bwapi.Position(myUnit.getPosition().getX() + (aux1), myUnit.getPosition().getY() +(aux2)));
		game.drawCircleMap(myUnit.getPosition().getX() + (aux1), myUnit.getPosition().getY() +(aux2),15,Color.Blue);
		game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), "EXPLORE");
	}

	private  Position getToSafePlace(Unit myUnit, Player enemy) {
		int myUnitX = myUnit.getPosition().getX();
		int myUnitY = myUnit.getPosition().getY();
		int distX = 0;
		int distY = 0;
		for (Unit enemyUnit : enemy.getUnits()) {
			distX += myUnitX - enemyUnit.getPosition().getX();
			distY += myUnitY - enemyUnit.getPosition().getY();
		}
		game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), "flee -- GetToSafePlace");
		return new bwapi.Position(myUnitX+distX,myUnitY+distY);
	}

	private void attack(Unit myUnit) throws IOException {
		int aux;
		int aux2;
		int closest = 9999;
		int lowestLife = 9999;
		int lowestUnitId = 0;
		int closestUnitId = 0;
		for (Unit enemyUnit : game.enemy().getUnits()) {
			aux = myUnit.getDistance(enemyUnit);
			if(aux < closest){
				closest = aux;
				closestUnitId = enemyUnit.getID();
			}
			aux2 = enemyUnit.getHitPoints();
			if(aux2 < lowestLife){
				lowestLife = aux2;
				lowestUnitId = enemyUnit.getID();
			}
		}
		// TODO como relacionar lowestUnitId & closestUnitId
		for (Unit enemyUnit : game.enemy().getUnits()) {
			if(lowestUnitId == enemyUnit.getID())
				myUnit.attack(enemyUnit);
		}
		
		/*for (Unit enemyUnit : game.enemy().getUnits()) {
			if (myUnit.isInWeaponRange(enemyUnit)) {
				myUnit.stop();
				myUnit.attack(enemyUnit.getPosition());
				break;
			}
		}*/
	}
	
	public static void log(String msg) throws IOException {
		if (DEBUG){
			if(!outFile.isFile())
				outFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile,true));
			writer.append(msg);
			writer.newLine();
			writer.flush();
			writer.close();
		}
	}
	
	public int getInitCounter() {
		return initCounter;
	}

	public void setInitCounter(int initCounter) {
		this.initCounter = initCounter;
	}


	public static void main(String[] args) {
		System.out.println("TestBotSC1 MAIN antes do RUN");
		new TestBotSC1().run();
		System.out.println("TestBotSC1 MAIN depois do RUN");
	}

}
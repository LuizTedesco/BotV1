package scrl.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

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

	public static final int MAX_GAMES = 100;
	private static final boolean DEBUG = true;
	private Mirror mirror = new Mirror();
	private Game game;
	private Player self;
//	private Player enemy;
	private SCRL rl;
	static File outFile = new File("teste1.txt");
	private int initCounter = 1;
	


	private static int match = 0;

	public void run() {
		System.out.println("Run");
		try {
			TestBotSC1.log("Entrou na função RUN DENTRO DE TESTBOTSC1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}
	
	@Override
	public void onStart() {
		try {
			TestBotSC1.log("Entrou na função onStart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game = mirror.getGame();
		self = game.self();
		
		//enemy = game.enemy();
		//game.setLocalSpeed(0);
		
		BWTA.readMap();
		BWTA.analyze();
		
		try {
			log("Map data ready");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
	}
	
	
	private void init(){
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função init");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rl = new SCRL();
		rl.init(match);

		for (Unit myUnit : self.getUnits()) {
			new RLUnitThread(game, rl, myUnit, self, this, game.enemy()).start();
			//new RLUnitThread(rl, myUnit, self, this, game.enemy()).start();
			try {
				log(Thread.currentThread().getId()+" thread Created on init: " + initCounter);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		initCounter++;
	}
	
	@Override
	public void onEnd(boolean isWinner) {
		try {
			TestBotSC1.log("Entrou na função onEnd pela thread" + Thread.currentThread().getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rl.end(isWinner);
		match++;
		if (match == 15)
			System.exit(0);

	}
	
	/*
	@Override
	public void onFrame() {
		if(game.getFrameCount()%(game.getFPS()) == 0)
		{
			System.out.println("FrameCount");
			System.out.println(game.getFrameCount());
			System.out.println("Game FPS");
			System.out.println(game.getFPS());
			System.out.println(game.getFPS()*3);
			for (Unit myUnit : self.getUnits()) {
				new RLUnitThread(game, rl, myUnit, self, this, game.enemy()).start();
				try {
					log(Thread.currentThread().getId()+" thread Created on init: " + initCounter);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	*/

	public void executeAction(Actions actionToPerform, Unit me) throws IOException{
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função executeAction");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (actionToPerform.equals(Actions.ATTACK)) {
			attack(me);
		} else if (actionToPerform.equals(Actions.EXPLORE)) {
			explore(me);
		} else {
			flee(me);
		}
	}

	private void flee(Unit myUnit) throws IOException {
		System.out.println("Flee Function");
		log(Thread.currentThread().getId()+" Entrou na funcao Action: FLEE");
		log(Thread.currentThread().getId()+" UnitID = " + myUnit.getID());
		log(Thread.currentThread().getId()+" HP = " + myUnit.getHitPoints());
		log(Thread.currentThread().getId()+" position = " + myUnit.getPosition());
		myUnit.move(getToSafePlace(myUnit,game.enemy()));
	}

	private void explore(Unit myUnit) throws IOException {
		System.out.println("Explore Function");
		log(Thread.currentThread().getId()+" Entrou na funcao Action: EXPLORE");
		log(Thread.currentThread().getId()+" UnitID = " + myUnit.getID());
		log(Thread.currentThread().getId()+" HP = " + myUnit.getHitPoints());
		log(Thread.currentThread().getId()+" position = " + myUnit.getPosition());
		
		Random generator = new Random();
		int low = -200;
		int high = 200;
		int aux1 = generator.nextInt(high-low)+low;
		int aux2 = generator.nextInt(high-low)+low;
		//myUnit.move(new bwapi.Position(myUnit.getPosition().getX() + (aux1), myUnit.getPosition().getY() +(aux2)));
		myUnit.move(new bwapi.Position(aux1, aux2));
	}

	private  Position getToSafePlace(Unit myUnit, Player enemy) {
		
		int posX = 0;
		int posy = 0;
		for (Unit enemyUnit : enemy.getUnits()) {
			posX += enemyUnit.getPosition().getX();
			posy += enemyUnit.getPosition().getY();
		}
		return new bwapi.Position(-posX,-posy);
		
		
	}

	private void attack(Unit myUnit) throws IOException {
		System.out.println("Attack Function");
		log(Thread.currentThread().getId()+" entrou na funcao ACTION: Attack");
		log(Thread.currentThread().getId()+" UnitID = " + myUnit.getID());
		log(Thread.currentThread().getId()+" HP = " + myUnit.getHitPoints());
		log(Thread.currentThread().getId()+" position = " + myUnit.getPosition());
		for (Unit enemyUnit : game.enemy().getUnits()) {
			if (myUnit.isInWeaponRange(enemyUnit)) {
				myUnit.stop();
				myUnit.attack(enemyUnit.getPosition());
				break;
			}
		}
	}
	
	/*	@Override
	public void onUnitCreate(Unit unit) {
		log("New unit discovered " + unit.getType());
	
	@SuppressWarnings("unused")
	private Object getState(Unit myUnit) {
		double myHpLife = myUnit.getHitPoints();
		double contHpLife = 0.d;
		int contnumberOfEnemyUnitsThatCanBeAttacked = 0;
		int contnumberOfEnemyUnitsThatCanAttackMe = 0;
		for (Unit enemyUnit : enemy.getUnits()) {
			contHpLife += enemyUnit.getHitPoints();
			if (myUnit.isInWeaponRange(enemyUnit))
				contnumberOfEnemyUnitsThatCanAttackMe++;
			if (enemyUnit.isInWeaponRange(myUnit))
				contnumberOfEnemyUnitsThatCanBeAttacked++;
		}
		return myUnit;
	}
	}*/
	
	

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

	public static void main(String[] args) {
		new TestBotSC1().run();
	}
}
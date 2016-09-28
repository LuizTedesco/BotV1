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
	private Player enemy;
	private SCRL rl;
	static File outFile = new File("teste1.txt");
	private int initCounter = 1;


	private static int match = 0;

	public void run() {
		try {
			TestBotSC1.log("Entrou na função RUN DENTRO DE TESTBOTSC1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}

/*	@Override
	public void onUnitCreate(Unit unit) {
		log("New unit discovered " + unit.getType());
	}*/

	@Override
	public void onEnd(boolean isWinner) {
		try {
			TestBotSC1.log("Entrou na função onEnd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rl.end(isWinner);
		try {
			log(match + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		match++;
		if (match == 500)
			System.exit(0);

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
		enemy = game.enemy();
		game.setLocalSpeed(20);
		init();
		
		//rl = new SCRL();
		//rl.init(match);

		BWTA.readMap();
		BWTA.analyze();
		try {
			log("Map data ready");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			new RLUnitThread(rl, myUnit, self, this, enemy).start();
			try {
				log(Thread.currentThread().getId()+" thread Created on init" + initCounter);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		initCounter++;
	}

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

	@Override
	public void onFrame() {
		//game.drawTextScreen(10, 10, "Playing as " + self.getName() + " - " + self.getRace());
	}

	public void executeAction(Actions actionToPerform, Unit myUnit) throws IOException{
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função executeAction");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("ActionToPerform "+ actionToPerform);
		if (actionToPerform.equals(Actions.ATTACK)) {
			attack(myUnit);
		} else if (actionToPerform.equals(Actions.EXPLORE)) {
			explore(myUnit);
		} else {
			flee(myUnit);
		}
	}

	private void flee(Unit myUnit) throws IOException {
		log(Thread.currentThread().getId()+" Entrou na funcao Action: FLEE");
		log(Thread.currentThread().getId()+" UnitID = " + myUnit.getID());
		log(Thread.currentThread().getId()+" HP = " + myUnit.getHitPoints());
		log(Thread.currentThread().getId()+" position = " + myUnit.getPosition());
		//log("HPFromEnemies = " + myUnit.getID());
		//log("NumerOfEnemiesThatCanAttackMe = " + myUnit.getID());
		//log("NumerOfEnemiesThatICanAttack = " + myUnit.getID());
		myUnit.move(getToSafePlace(myUnit,enemy));
		// -myUnit.getPoint().getY()));
		//myUnit.move(new bwapi.Position(-1*myUnit.getPosition().getX(), -1*myUnit.getPosition().getY()));
	}

	private void explore(Unit myUnit) throws IOException {
		log(Thread.currentThread().getId()+" Entrou na funcao Action: EXPLORE");
		log(Thread.currentThread().getId()+" UnitID = " + myUnit.getID());
		log(Thread.currentThread().getId()+" HP = " + myUnit.getHitPoints());
		log(Thread.currentThread().getId()+" position = " + myUnit.getPosition());
		Random generator = new Random();
		myUnit.move(new bwapi.Position(myUnit.getPosition().getX() + (generator.nextInt(30)-31), myUnit.getPosition().getY() +(generator.nextInt(30)-31)));
		//myUnit.move(new bwapi.Position(3 * myUnit.getPosition().getX(), 2 * myUnit.getPosition().getY()));
		// myUnit.move(new Position(3 * myUnit.getPoint().getX(), 2 *
		// myUnit.getPoint().getY()));
	}

	private  Position getToSafePlace(Unit myUnit, Player enemies) {
		
		int posX = 0;
		int posy = 0;
		for (Unit enemyUnit : enemies.getUnits()) {
			posX += enemyUnit.getPosition().getX();
			posy += enemyUnit.getPosition().getY();
		}
		return new bwapi.Position(-posX,-posy);
		
		
	}

	private void attack(Unit myUnit) throws IOException {
		log(Thread.currentThread().getId()+" entrou na funcao ACTION: Attack");
		log(Thread.currentThread().getId()+" UnitID = " + myUnit.getID());
		log(Thread.currentThread().getId()+" HP = " + myUnit.getHitPoints());
		log(Thread.currentThread().getId()+" position = " + myUnit.getPosition());
		for (Unit enemyUnit : enemy.getUnits()) {
			if (myUnit.isInWeaponRange(enemyUnit)) {
				myUnit.stop();
				myUnit.attack(enemyUnit.getPosition());
				break;
			}
		}
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

	public static void main(String[] args) {
		new TestBotSC1().run();
	}
}
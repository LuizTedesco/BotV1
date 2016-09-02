package scrl.tests;

import java.util.Random;

import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;
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
	private Random rand = new Random();

	private static int match = 0;

	public void run() {
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}

/*	@Override
	public void onUnitCreate(Unit unit) {
		log("New unit discovered " + unit.getType());
	}*/

	@Override
	public void onEnd(boolean isWinner) {
		rl.end(isWinner);
		log(match + "");
		match++;
		if (match == 500)
			System.exit(0);

	}

	@Override
	public void onStart() {
		game = mirror.getGame();
		self = game.self();
		enemy = game.enemy();
		game.setLocalSpeed(20);
		//init();
		
		rl = new SCRL();
		rl.init(match);

		for (Unit myUnit : self.getUnits()) {
			new RLUnitThread(rl, myUnit, this, enemy).start();
		}
		
		

		// Use BWTA to analyze map
		// This may take a few minutes if the map is processed first time!
		// log("Analyzing map...");
		BWTA.readMap();
		BWTA.analyze();
		// log("Map data ready");

	}

	@SuppressWarnings("unused")
	private void init() {
		rl = new SCRL();
		rl.init(match);

		for (Unit myUnit : self.getUnits()) {
			new RLUnitThread(rl, myUnit, this, enemy).start();
		}
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
		game.drawTextScreen(10, 10, "Frame");
		//game.drawTextScreen(10, 10, "Playing as " + self.getName() + " - " + self.getRace());
	}

	public void executeAction(Actions actionToPerform, Unit myUnit) {
		System.out.println("ActionToPerform "+ actionToPerform);
		if (actionToPerform.equals(Actions.ATTACK)) {
			attack(myUnit);
		} else if (actionToPerform.equals(Actions.EXPLORE)) {
			explore(myUnit);
		} else {
			flee(myUnit);
		}
	}

	private void flee(Unit myUnit) {
		log("FLEE  +UnitID = " + myUnit.getID());
		int v = rand.nextInt(50);
		// myUnit.move(new Position(-myUnit.getPoint().getX(),
		// -myUnit.getPoint().getY()));
		myUnit.move(new bwapi.Position(myUnit.getPosition().getX() - v, myUnit.getPosition().getY() - v));
	}

	private void explore(Unit myUnit) {
		log("EXPLORE  +UnitID = " + myUnit.getID());
		myUnit.move(new bwapi.Position(3 * myUnit.getPosition().getX(), 2 * myUnit.getPosition().getY()));
		// myUnit.move(new Position(3 * myUnit.getPoint().getX(), 2 *
		// myUnit.getPoint().getY()));
	}

	private void attack(Unit myUnit) {
		log("ATTACK  +UnitID = " + myUnit.getID());
		for (Unit enemyUnit : enemy.getUnits()) {
			if (myUnit.isInWeaponRange(enemyUnit)) {
				myUnit.stop();
				myUnit.attack(enemyUnit.getPosition());
				break;
			}
		}
	}

	private void log(String msg) {
		if (DEBUG)
			System.out.println(msg);
	}

	public static void main(String[] args) {
		new TestBotSC1().run();
	}
}
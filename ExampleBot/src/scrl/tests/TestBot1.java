package scrl.tests;

import javax.swing.text.Position;

import org.jalt.model.action.Action;

import scrl.SCRL;
import scrl.model.Actions;
import scrl.model.UnitState;

public class TestBot1 extends DefaultBWListener {
	private static int victories;
	private static int defeats;

	private Mirror mirror = new Mirror();
	private Game game;
	private Player self;
	private Player enemy;
	SCRL rl = new SCRL();

	private int match;

	public void run() {
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}

	@Override
	public void onUnitCreate(Unit unit) {
		System.out.println("New unit discovered " + unit.getType());
	}

	@Override
	public void onEnd(boolean isWinner) {
		rl.end(isWinner);
		if (isWinner)
			victories++;
		else
			defeats++;
	}

	@Override
	public void onStart() {
		game = mirror.getGame();
		self = game.self();
		enemy = game.enemy();
		game.setLocalSpeed(15);
		init();

		// Use BWTA to analyze map
		// This may take a few minutes if the map is processed first time!
		System.out.println("Analyzing map...");
		BWTA.readMap();
		BWTA.analyze();
		System.out.println("Map data ready");

		int i = 0;
		for (BaseLocation baseLocation : BWTA.getBaseLocations()) {
			System.out.println("Base location #" + (++i) + ". Printing location's region polygon:");
			for (Position position : baseLocation.getRegion().getPolygon().getPoints()) {
				System.out.print(position + ", ");
			}
			System.out.println();
		}
	}

	private void init() {
		// loadMatchs();
		match++;
		rl.init(match);
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
		double contHpLife = 0.d;
		int contnumberOfEnemyUnitsThatCanBeAttacked = 0;
		int contnumberOfEnemyUnitsThatCanAttackMe = 0;
		double attckOrExploreOrFleeMark = 0;

		for (Unit myUnit : self.getUnits()) {
			for (Unit enemyUnit : enemy.getUnits()) {
				contHpLife += enemyUnit.getHitPoints();
				if (myUnit.isInWeaponRange(enemyUnit))
					contnumberOfEnemyUnitsThatCanAttackMe++;
				if (enemyUnit.isInWeaponRange(myUnit))
					contnumberOfEnemyUnitsThatCanBeAttacked++;
			}

			UnitState state = new UnitState(myUnit.getHitPoints(), contHpLife, contnumberOfEnemyUnitsThatCanBeAttacked, contnumberOfEnemyUnitsThatCanAttackMe,
					0);
			Action actionToPerform = rl.getNextAction(state);

			executeAction(myUnit);

			rl.updateState(actionToPerform, state);
		}
		// game.setTextSize(10);
		game.drawTextScreen(10, 10, "Playing as " + self.getName() + " - " + self.getRace());
		// draw my units on screen
		game.drawTextScreen(10, 25, units.toString());
	}

	private void executeAction(Actions actionToPerform, Unit myUnit) {
		double attckOrExploreOrFleeMark;
		// actionToPerform.toString().equals(Actions.ATTACK.getName())
		// if(actionToPerform.equals(Actions.ATTACK.getName())){
		if (actionToPerform.equals(Actions.ATTACK)) {
			attack(myUnit);
		} else if (actionToPerform.equals(Actions.EXPLORE)) {
			explore(myUnit);
		} else {
			flee(myUnit);
		}
	}

	private void flee(Unit myUnit) {
		System.out.println("flee");
		myUnit.move(new Position(-myUnit.getPoint().getX(), -myUnit.getPoint().getY()));
	}

	private void explore(Unit myUnit) {
		System.out.println("explore");
		myUnit.move(new Position(3 * myUnit.getPoint().getX(), 2 * myUnit.getPoint().getY()));
	}

	private void attack(Unit myUnit) {
		System.out.println("attack");
		int enemyHpT0, enemyHpT1;
		for (Unit enemyUnit : enemy.getUnits()) {
			if (myUnit.isInWeaponRange(enemyUnit)) {
				myUnit.stop();
				enemyHpT0 = enemyUnit.getHitPoints();
				myUnit.attack(enemyUnit.getPosition());
				enemyHpT1 = enemyUnit.getHitPoints();
				return enemyHpT0 - enemyHpT1;
			}
		}
	}

	public static void main(String[] args) {
		new TestBot1().run();
	}
}
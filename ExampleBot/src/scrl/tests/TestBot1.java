package scrl.tests;

import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;
import bwapi.Unit;
import bwta.BWTA;
import scrl.SCRL;
import scrl.model.Actions;
import scrl.model.UnitState;

public class TestBot1 extends DefaultBWListener {
	private int victories;
	private int defeats;

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
			setVictories(getVictories() + 1);
		else
			setDefeats(getDefeats() + 1);
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

		for (Unit myUnit : self.getUnits()) {
			for (Unit enemyUnit : enemy.getUnits()) {
				contHpLife += enemyUnit.getHitPoints();
				if (myUnit.isInWeaponRange(enemyUnit))
					contnumberOfEnemyUnitsThatCanAttackMe++;
				if (enemyUnit.isInWeaponRange(myUnit))
					contnumberOfEnemyUnitsThatCanBeAttacked++;
			}
			UnitState state = new UnitState(myUnit.getHitPoints(), contHpLife, contnumberOfEnemyUnitsThatCanBeAttacked, contnumberOfEnemyUnitsThatCanAttackMe);
			Actions actionToPerform = rl.getNextAction(state);

			executeAction(actionToPerform, myUnit);

			rl.updateState(actionToPerform, state);
		}
		game.drawTextScreen(10, 10, "Playing as " + self.getName() + " - " + self.getRace());
	}

	private void executeAction(Actions actionToPerform, Unit myUnit) {
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
		//myUnit.move(new Position(-myUnit.getPoint().getX(), -myUnit.getPoint().getY()));
		myUnit.move(new bwapi.Position(-myUnit.getPosition().getX(), -myUnit.getPosition().getY()));
	}

	private void explore(Unit myUnit) {
		System.out.println("explore");
		myUnit.move(new bwapi.Position(3* myUnit.getPosition().getX(), 2* myUnit.getPosition().getY()));
		//myUnit.move(new Position(3 * myUnit.getPoint().getX(), 2 * myUnit.getPoint().getY()));
	}

	private void attack(Unit myUnit) {
		System.out.println("attack");
		for (Unit enemyUnit : enemy.getUnits()) {
			if (myUnit.isInWeaponRange(enemyUnit)) {
				myUnit.stop();
				myUnit.attack(enemyUnit.getPosition());
			}
		}
	}

	public static void main(String[] args) {
		new TestBot1().run();
	}

	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public int getDefeats() {
		return defeats;
	}

	public void setDefeats(int defeats) {
		this.defeats = defeats;
	}
}
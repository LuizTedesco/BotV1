package scrl;

import bwapi.Player;
import bwapi.Unit;
import scrl.model.Actions;
import scrl.model.UnitState;
import scrl.tests.TestBotSC1;

public class RLUnitThread extends Thread {

	private Unit me;
	private SCRL rl;
	private TestBotSC1 bot;
	private Player enemies;

	public RLUnitThread(SCRL rl, Unit me, TestBotSC1 bot, Player enemies) {
		this.me = me;
		this.rl = rl;
		this.bot = bot;
		this.enemies = enemies;
	}

	@Override
	public void run() {
		double contHpLife = 0.d;
		int contnumberOfEnemyUnitsThatCanBeAttacked = 0;
		int contnumberOfEnemyUnitsThatCanAttackMe = 0;

		for (Unit enemyUnit : enemies.getUnits()) {
			contHpLife += enemyUnit.getHitPoints();
			if (me.isInWeaponRange(enemyUnit))
				contnumberOfEnemyUnitsThatCanAttackMe++;
			if (enemyUnit.isInWeaponRange(me))
				contnumberOfEnemyUnitsThatCanBeAttacked++;
		}
		UnitState state = new UnitState(me.getHitPoints(), contHpLife, contnumberOfEnemyUnitsThatCanBeAttacked,
				contnumberOfEnemyUnitsThatCanAttackMe);

		System.out.println("'pegou o estate'");
		Actions actionToPerform = rl.getNextAction(state);
		bot.executeAction(actionToPerform, me);

		// esperando acao acabar
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		rl.updateState(actionToPerform, state);
	}

}

package scrl;

import java.io.IOException;

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
		UnitState curState = new UnitState(me.getHitPoints(), contHpLife, contnumberOfEnemyUnitsThatCanBeAttacked,
				contnumberOfEnemyUnitsThatCanAttackMe);

		
		Actions actionToPerform = rl.getNextAction(curState);
		
		try {
			TestBotSC1.log(actionToPerform.toString());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			bot.executeAction(actionToPerform, me);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
		UnitState newState = getCurrentState(me,enemies);
		
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		rl.updateState(actionToPerform, curState,newState);
	}

	private UnitState getCurrentState(Unit me, Player enemies) {
		
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" getCurrentState");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" contnumberOfEnemyUnitsThatCanAttackMe: "+contnumberOfEnemyUnitsThatCanAttackMe);
			TestBotSC1.log(Thread.currentThread().getId()+" contnumberOfEnemyUnitsThatCanBeAttacked: "+contnumberOfEnemyUnitsThatCanBeAttacked);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new UnitState(me.getHitPoints(), contHpLife, contnumberOfEnemyUnitsThatCanBeAttacked,
				contnumberOfEnemyUnitsThatCanAttackMe);
		
	}

}

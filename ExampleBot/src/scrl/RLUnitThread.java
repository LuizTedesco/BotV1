package scrl;

import java.io.IOException;
import java.util.List;

import bwapi.Player;
import bwapi.Unit;
import scrl.model.Actions;
import scrl.model.UnitState;
import scrl.model.range.RangeDistance;
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;
import scrl.tests.TestBotSC1;

public class RLUnitThread extends Thread {

	private Unit me;
	private SCRL rl;
	private TestBotSC1 bot;
	private Player enemy;
	private Player self;

	public RLUnitThread(SCRL rl, Unit me, Player self, TestBotSC1 bot, Player enemy) {
		this.me = me;
		this.self = self;
		this.rl = rl;
		this.bot = bot;
		this.enemy = enemy;
	}

	@Override
	public void run() {
		UnitState curState = getCurrentState();

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
				
		UnitState newState = getCurrentState();
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		rl.updateState(actionToPerform, curState,newState);
	}

	private UnitState getCurrentState() {
		
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" getCurrentState");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double contHpEnemyLife = 0.d;
		double contHpAlliesLife = 0.d;
		double mediumHpFromNearbyEnemies = 0.d;
		int numberOfEnemiesUnitsNearby =0;
		double hpFromNearbyAllies = 0.d;
		int numberOfAlliesUnitsNearby = 0;
		int distanceToClosestEnemyUnit = 1000;	
		
		List<Unit> units = me.getUnitsInRadius(50);
		for (Unit unit : units) {
			if(unit.getPlayer().isAlly(self))
			{
				contHpAlliesLife += unit.getHitPoints();
				numberOfAlliesUnitsNearby++;
			}else if(unit.getPlayer().isEnemy(self))
			{
				contHpEnemyLife += unit.getHitPoints();
				numberOfEnemiesUnitsNearby++;
				distanceToClosestEnemyUnit = me.getDistance(unit) < distanceToClosestEnemyUnit ? me.getDistance(unit) : distanceToClosestEnemyUnit;
			}	
		}
		mediumHpFromNearbyEnemies = contHpEnemyLife / numberOfEnemiesUnitsNearby;
		hpFromNearbyAllies = contHpAlliesLife / numberOfAlliesUnitsNearby;

		UnitState curState = new UnitState(me.getHitPoints(), mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, 
											hpFromNearbyAllies, numberOfAlliesUnitsNearby, distanceToClosestEnemyUnit);
		return curState;
	}
}

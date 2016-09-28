package scrl.model;

import java.io.IOException;

import scrl.tests.TestBotSC1;

public class RewardFunction {
	public static double getValue(final UnitState pState, UnitState next, final Actions pAction) {
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função getValue");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("getValueForGiverAction:  " + pAction);
		if (pAction == Actions.ATTACK) {
			//return next.getHpFromNearbyEnemies().isLower(pState.getHpFromNearbyEnemies()) ? -10 : 10;
			return 30;
		} else if (pAction == Actions.EXPLORE){
			return 0.2;
		}
		else if (pAction == Actions.FLEE) {
			// if(next.getNumberOfEnemyUnitsThatCanAttackMe() >
			// pState.getNumberOfEnemyUnitsThatCanAttackMe())
			//return next.getHp().isLower(pState.getHp()) ? 10 : -10;
			return 0.1;
		}
		return -0.5;
	}
}

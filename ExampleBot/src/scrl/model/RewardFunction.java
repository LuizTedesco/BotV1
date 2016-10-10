package scrl.model;

import java.io.IOException;

import scrl.tests.TestBotSC1;

public class RewardFunction {
	public static double getValue(final UnitState pState, UnitState next, final Actions pAction) {
		//System.out.println(Thread.currentThread().getId()+" Reward Function  " + pAction);
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função getValue");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pAction == Actions.ATTACK) {
			// problema, tinha que pegar o VALOR mesmo para comparar.
			if(next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())){
				return 10;
			}
			else{
				return -10;
			}
			//System.out.println(Thread.currentThread().getId()+ "   "+(next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies()) ? 15 : 0.3));
			//System.out.println(next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies()) ? -10 : 10);
			//System.exit(0);
			//return 30;
		} else if (pAction == Actions.EXPLORE){
			return 1;
		}
		else if (pAction == Actions.FLEE) {
			// if(next.getNumberOfEnemyUnitsThatCanAttackMe() >
			// pState.getNumberOfEnemyUnitsThatCanAttackMe())
			//return next.getHp().isLower(pState.getHp()) ? 10 : -10;
			return 0.1;
		}
		return -1;
	}
}

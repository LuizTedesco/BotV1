package scrl.model;

public class RewardFunction {
	public static double getValue(final UnitState pState, UnitState next, final Actions pAction) {
		//System.out.println("getValue Function");
		
		/*try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função getValue");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if (pAction == Actions.ATTACK) {
			//System.out.println("getValue Function+ attack");
			// problema, tinha que pegar o VALOR mesmo para comparar.
			if(next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())){
				//System.out.println(next.getMediumHpFromNearbyEnemies());
				//System.out.println(pState.getMediumHpFromNearbyEnemies());
				return 5;
			}
			else{
				//System.out.println(next.getMediumHpFromNearbyEnemies());
				//System.out.println(pState.getMediumHpFromNearbyEnemies());
				return -3;
			}
			//System.out.println(Thread.currentThread().getId()+ "   "+(next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies()) ? 15 : 0.3));
			//System.out.println(next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies()) ? -10 : 10);
			//System.exit(0);
			//return 30;
		} else if (pAction == Actions.EXPLORE){
			//System.out.println("getValue Function+ explore");
			return -2;
		}
		else if (pAction == Actions.FLEE) {
			//System.out.println("getValue Function+ flee");
			// if(next.getNumberOfEnemyUnitsThatCanAttackMe() >
			// pState.getNumberOfEnemyUnitsThatCanAttackMe())
			//return next.getHp().isLower(pState.getHp()) ? 10 : -10;
			return -1;
		}
		//System.out.println("-1");
		return -1;
	}
}

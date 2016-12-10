package scrl.model;

public class RewardFunction {
	public static double getValue(final UnitState pState, UnitState next, final Actions pAction) {
		
		if (pAction == Actions.ATTACK) {
			if(next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())){
				return 5;
			}
			else{
				return -3;
			}
		} else if (pAction == Actions.EXPLORE){
			return -2;
		}
		else if (pAction == Actions.FLEE) {
			return -1;
		}
		return -1;
	}
}

package scrl.model;

public class RewardFunction {
	public static double getValue(final UnitState pState, UnitState next, final Actions pAction) {
		
		if (pAction == Actions.ATTACK) {
			if(next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())){
				return 7;
			}
			else{
				return -4;
			}
		} else if (pAction == Actions.EXPLORE){
			return -1;
		}
		else if (pAction == Actions.FLEE) {
			return -2;
		}
		return -0.1;
	}
}

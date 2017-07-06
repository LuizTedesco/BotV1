package scrl.model.function;

import scrl.model.State;
import scrl.model.actions.Action;
import scrl.model.actions.Attack;
import scrl.model.actions.Explore;
import scrl.model.actions.Flee;

public class RewardFunction {
	private static final double DEFAULT_REWARD = 100d;

	public static double getValue(final State state, State next, final Action action) {
		double diffAlliesUnits;
		double diffEnemyUnits;
		boolean noEnemiesNearby;
		noEnemiesNearby = state.getHpFromNearbyEnemies().getValue() == 0;
		
		if (action instanceof Attack) {
			if (noEnemiesNearby) {
				return -DEFAULT_REWARD;
			} else {
				diffAlliesUnits = (state.getNumberOfAlliesUnitsNearby().getValue() - next.getNumberOfAlliesUnitsNearby().getValue());
				diffEnemyUnits = (state.getNumberOfEnemiesUnitsNearby().getValue() - next.getNumberOfEnemiesUnitsNearby().getValue());
				if(diffAlliesUnits>0 || diffEnemyUnits<0)
				{ // Bad RW
					return -hpDiff(state, next);
				}else{
					return hpDiff(state, next);
				}
			}
		} else if (action instanceof Explore) {
			return noEnemiesNearby ? DEFAULT_REWARD : -DEFAULT_REWARD ;
		} else if (action instanceof Flee) {
			if (noEnemiesNearby) {
				return -DEFAULT_REWARD;
			} else {
				if(state.getHpFromNearbyEnemies().getValue() > (1.5*state.getHpFromNearbyAllies().getValue()))
				{
					return -DEFAULT_REWARD;
				}
				else
				{
					return DEFAULT_REWARD;
				}

			}
		}
		return -DEFAULT_REWARD;
	}

	private static double hpDiff(final State state, State next) {
		double diffAllies = (state.getHpFromNearbyAllies().getValue() - next.getHpFromNearbyAllies().getValue());
		double diffEnemy = (state.getHpFromNearbyEnemies().getValue() - next.getHpFromNearbyEnemies().getValue());
		return (diffEnemy - diffAllies) * DEFAULT_REWARD;
	}
}
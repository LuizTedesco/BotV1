package scrl.model.function;

import scrl.model.State;
import scrl.model.actions.Action;
import scrl.model.actions.Attack;
import scrl.model.actions.Explore;
import scrl.model.actions.Flee;
import scrl.model.range.RangeDistance;

public class RewardFunction {
	private static final double DEFAULT_REWARD = 100d;

	public static double getValue(final State state, State next, final Action action) {
		boolean isFar = state.getDistanceFromClosestEnemy().equals(RangeDistance.FAR);
		if (action instanceof Attack) {
			if (state.getHpFromNearbyEnemies().getValue() == 0) {
				return -DEFAULT_REWARD;
			} else {
				double diffAllies = (state.getHpFromNearbyAllies().getValue()
						- next.getHpFromNearbyAllies().getValue());
				double diffEnemy = (state.getHpFromNearbyEnemies().getValue()
						- next.getHpFromNearbyEnemies().getValue());
				return (diffAllies - diffEnemy) * DEFAULT_REWARD;
			}
		} else if (action instanceof Explore) {
			return isFar ? DEFAULT_REWARD : -DEFAULT_REWARD / 10;
		} else if (action instanceof Flee) {
			double diffAllies = (state.getHpFromNearbyAllies().getValue() - next.getHpFromNearbyAllies().getValue());
			double diffEnemy = (state.getHpFromNearbyEnemies().getValue() - next.getHpFromNearbyEnemies().getValue());
			return (diffEnemy - diffAllies) * DEFAULT_REWARD;
		}
		return -DEFAULT_REWARD;
	}
}
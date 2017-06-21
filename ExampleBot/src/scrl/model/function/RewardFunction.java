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
			if (isFar) {
				return -DEFAULT_REWARD;
			} else {
				int diffAllies = (state.getHpFromNearbyAllies().ordinal() - next.getHpFromNearbyAllies().ordinal());
				int diffEnemy = (state.getHpFromNearbyEnemies().ordinal() - next.getHpFromNearbyEnemies().ordinal());
				return (diffAllies - diffEnemy) * DEFAULT_REWARD;
			}
		} else if (action instanceof Explore) {
			return isFar ? DEFAULT_REWARD : -DEFAULT_REWARD / 10;
		} else if (action instanceof Flee) {
			int diffAllies = (state.getHpFromNearbyAllies().ordinal() - next.getHpFromNearbyAllies().ordinal());
			int diffEnemy = (state.getHpFromNearbyEnemies().ordinal() - next.getHpFromNearbyEnemies().ordinal());
			return (diffEnemy - diffAllies) * DEFAULT_REWARD;
		}
		return -DEFAULT_REWARD;
	}
}
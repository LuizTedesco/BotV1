package scrl.model;

public class RewardFunction {
	public static double getValue(final UnitState pState, final Actions pAction) {
		System.out.println("vetValueForGiverAction:  "+ pAction);
		if(pAction == Actions.ATTACK)
			return 10;
		else if(pAction == Actions.EXPLORE)
			return 1;
		else if(pAction == Actions.FLEE)
			return -0.5;
		return -0.5;
	}
}

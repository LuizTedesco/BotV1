package scrl.model;

public class RewardFunction {
	public static double getValue(final UnitState pState, final Actions pAction) {
		// como pegar o retorno para o atck?
		if(pAction == Actions.ATTACK)
			return 10.8;
		else if(pAction == Actions.EXPLORE)
			return 1;
		else if(pAction == Actions.FLEE)
			return -0.1;
		return -0.1;
	}
}

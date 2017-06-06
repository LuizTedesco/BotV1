package scrl.algorithm;

import scrl.model.SCMDP;
import scrl.model.State;
import scrl.model.actions.Action;

public class QLearning extends AbstractLearning {

	private static final long serialVersionUID = 1L;

	public QLearning(SCMDP model) {
		super(model);
	}

	protected double computeQ(State current, State next, Action action, double reward) {
		double oldQ = q.get(current).get(action); // Q(s,a)
		double bracedValue = reward + (GAMA * getMax(next)) - oldQ;
		return oldQ + ALPHA * bracedValue;
	}

	@Override
	protected void updateQTable(State current, State next, Action action, double reward, QTable q2) {
		// TODO Auto-generated method stub
	}
	
}

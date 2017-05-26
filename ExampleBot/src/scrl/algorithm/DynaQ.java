package scrl.algorithm;

import scrl.model.SCMDP;
import scrl.model.State;
import scrl.model.actions.Action;

public class DynaQ extends AbstractLearning {

	private static final long serialVersionUID = 1L;

	public DynaQ(SCMDP model) {
		super(model);
	}

	protected double computeQ(State current, State next, Action action, double reward) {
		double oldQ = q.get(current).get(action); // Q(s,a)
		double bracedValue = reward + (GAMA * getMax(next)) - oldQ;
		return oldQ + ALPHA * bracedValue;
	}

}

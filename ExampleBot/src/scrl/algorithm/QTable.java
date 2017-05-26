package scrl.algorithm;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import scrl.model.SCMDP;
import scrl.model.State;
import scrl.model.actions.Action;

public class QTable extends ConcurrentHashMap<State, Map<Action, Double>> {

	private static final long serialVersionUID = 3826717973754083254L;

	private Random rand = new Random();
	private double epsilon = 0.1;

	// TABELA ORIGINAL
	public QTable(Collection<State> states, Collection<Action> Action) {
		super(states.size());
		for (State state : states) {
			Map<Action, Double> actionValues = new ConcurrentHashMap<Action, Double>();
			for (Action action : Action)
				actionValues.put(action, 0.);
			put(state, actionValues);
		}
	}

	public Action getMaxAction(State pState) {
		Map<Action, Double> map = this.get(pState);
		double max = Double.NEGATIVE_INFINITY;
		Action ret = null;
		for (Action act : map.keySet()) {
			if (map.get(act) > max) {
				max = map.get(act);
				ret = act;
			}
		}
		return ret;
	}

	public Action chooseNextAction(State state) {
		Action action = null;
		double rnd = rand.nextDouble();
		if (rnd < epsilon) {
			List<Action> actions = (List<Action>) SCMDP.getValidActions();
			action = actions.get(rand.nextInt(actions.size()));
		} else {
			action = getMaxAction(state);
		}
		return action;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	public double getEpsilon() {
		return epsilon;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (State state : this.keySet()) {
			builder.append("   ");
			// builder.append(state.toString());
			builder.append(state.toString2());
			builder.append(this.get(state));
			builder.append("\n");
		}
		return builder.toString();
	}
}

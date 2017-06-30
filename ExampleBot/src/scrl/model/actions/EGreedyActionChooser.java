package scrl.model.actions;

import java.util.List;
import java.util.Random;

import scrl.model.QTable;
import scrl.model.SCMDP;
import scrl.model.State;

public class EGreedyActionChooser implements ActionChooser {
	private Random rand = new Random();
	private double epsilon = 0.2;
	private QTable qTable;

	public EGreedyActionChooser(QTable qTable) {
		this.qTable = qTable;
	}

	@Override
	public Action getAction(State state) {
		Action action = null;
		double rnd = rand.nextDouble();
		if (rnd < epsilon) {
			List<Action> actions = (List<Action>) SCMDP.getValidActions();
			action = actions.get(rand.nextInt(actions.size()));
		} else {
			action = qTable.getMaxAction(state);
		}
		return action;
	}
}

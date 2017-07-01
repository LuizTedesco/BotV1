package scrl.rl.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


import scrl.model.Model;
import scrl.model.SCMDP;
import scrl.model.State;
import scrl.model.actions.Action;
import scrl.tests.Main;

public class DynaQ extends QLearning {

	private static final long serialVersionUID = 1L;
	public static final int HALLUCINATION = 24;

	Table<State, Action, Model> modelo = HashBasedTable.create();
	Random generator = new Random();

	public DynaQ(SCMDP model) {
		super(model);
	}

	@Override
	protected double computeQ(State state, State next, Action action, double reward) {
		modelo.put(state, action, new Model(next, reward));

		return super.computeQ(state, next, action, reward);
	}

	protected void updateQTable(State state, State next, Action action, double newQValue) {
		super.updateQTable(state, next, action, newQValue);

		Set<State> statesSet = Main.statesCounter.keySet();
		List<State> statesList = new ArrayList<>(statesSet);
		List<Action> validActions = (List<Action>) SCMDP.getValidActions();

		if (Main.match > 0) {
			int j = 0;
			// Halucinate
			for (int i = 0; i < HALLUCINATION; i++) {
				// https://stackoverflow.com/questions/929554/is-there-a-way-to-get-the-value-of-a-hashmap-randomly-in-java
				j = generator.nextInt(statesList.size());

				State randomObservedState = statesList.get(j);
				List<Action> usedActions = getUsedActions(validActions, q.get(randomObservedState));

				int var = generator.nextInt(usedActions.size());
				Action randomUsedAction = usedActions.get(var);

				Model model = modelo.get(randomObservedState, randomUsedAction);
				State nextH = model.getNextState();
				double rewardH = model.getReward();

				double newQValueH = super.computeQ(randomObservedState, nextH, randomUsedAction, rewardH);
				super.updateQTable(randomObservedState, nextH, randomUsedAction, newQValueH);
			}
		}
	}

	private ArrayList<Action> getUsedActions(List<Action> validActions, Map<Action, Double> myStateActionValues) {
		ArrayList<Action> usedActions = new ArrayList<>();

		for (Action possibleAction : validActions) {
			Set<Action> aux = myStateActionValues.keySet();
			for (Action action2 : aux) {
				if (action2.getClass().getSimpleName().equals(possibleAction.getClass().getSimpleName())) {
					if (myStateActionValues.get(action2) != 0.0) {
						// usedActions.add(possibleAction);
						usedActions.add(action2);
					}
				}
			}
		}
		return usedActions;
	}
}

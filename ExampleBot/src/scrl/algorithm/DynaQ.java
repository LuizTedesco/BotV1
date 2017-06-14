package scrl.algorithm;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import scrl.model.SCMDP;
import scrl.model.State;
import scrl.model.actions.Action;
import scrl.tests.Main;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


public class DynaQ extends AbstractLearning {

	private static final long serialVersionUID = 1L;
	public static final int HALLUCINATION = 24;

	Table<State, Action, Model> modelo = HashBasedTable.create();
	Model model;

	public State nextState;
	public Double rewardFromModel = 0.0;



	public DynaQ(SCMDP model) {
		super(model);
	}

	protected void updateQTable(State current, State next, Action action, double reward, QTable q2) {
		double oldQ = q.get(current).get(action); // Q(s,a)
		double bracedValue = reward + (GAMA * getMax(next)) - oldQ;
		double newQValue = oldQ + ALPHA * bracedValue;

		Map<Action, Double> computedActionValue = q.get(current);
		computedActionValue.put(action, newQValue);
		q.put(current, computedActionValue);


		model = new Model(next,reward);
		modelo.put(current, action, model);

		
		//ArrayList<State> states = (ArrayList<State>) Main.statesCounter.keySet(); 
		Set<State> statesSet = Main.statesCounter.keySet();
		List<State> statesList = new ArrayList<>(statesSet);


		@SuppressWarnings("unchecked")
		List<Action> validActions = (List<Action>) SCMDP.getValidActions();


		Random generator = new Random();
		int j = 0; 
		
		// Halucinate
		for(int i = 0; i < HALLUCINATION; i++)
		{
			//https://stackoverflow.com/questions/929554/is-there-a-way-to-get-the-value-of-a-hashmap-randomly-in-java
			j = generator.nextInt(statesList.size());

			State randomObservedState = statesList.get(j);
			Map<Action, Double> myStateActionValues = q.get(randomObservedState);
			ArrayList<Action> usedActions = new ArrayList<>();

			for (Action possibleAction : validActions)
			{
				Set<Action> aux = myStateActionValues.keySet();
				for (Action action2 : aux) {
					if(action2.getClass().getSimpleName().equals(possibleAction.getClass().getSimpleName()))
					{
						if(myStateActionValues.get(action2)!= 0.0)
						{
//							usedActions.add(possibleAction);
							usedActions.add(action2);
						}
					}
				}
			}
			int var = generator.nextInt(usedActions.size());
			Action randomUsedAction = usedActions.get(var);

			
			model = modelo.get(randomObservedState, randomUsedAction);
			nextState = model.getNextState();
			rewardFromModel = model.getReward();
			
			oldQ = q.get(randomObservedState).get(randomUsedAction);
			bracedValue = reward + (GAMA * getMax(next)) - oldQ;
			newQValue = oldQ + ALPHA * bracedValue;

			computedActionValue = q.get(current);
			computedActionValue.put(action, newQValue);
			q.put(current, computedActionValue);
		}

	}
}

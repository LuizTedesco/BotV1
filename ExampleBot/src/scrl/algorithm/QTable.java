package scrl.algorithm;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import scrl.model.Actions;
import scrl.model.UnitState;
import scrl.tests.TestBotSC1;

public class QTable extends ConcurrentHashMap<UnitState, Map<Actions, Double>>{
	//public class QTable extends HashMap<UnitState, Map<Actions, Double>> {	

	private static final long serialVersionUID = 3826717973754083254L;
	private Random rand = new Random();
	private double epsilon = 0.2;

	public QTable(Collection<UnitState> states, Collection<Actions> actions) {
		super(states.size());
		for (UnitState state : states) {
			//Map<Actions, Double> actionValues = new HashMap<>();
			Map<Actions, Double> actionValues = new ConcurrentHashMap<Actions, Double>();
			for (Actions action : actions)
				actionValues.put(action, 0.);
			put(state, actionValues);
		}
	}

	public Actions getMaxAction(UnitState pState) {
		Map<Actions, Double> map = this.get(pState);
		System.out.println("Current state map: " + map);
		TestBotSC1.log("Current state map: " + map);
		double max = Double.NEGATIVE_INFINITY;
		Actions ret = null;
			for (Actions act : map.keySet()) {
				if (map.get(act) > max) {
					max = map.get(act);
					ret = act;
				}
			}
//			TestBotSC1.log("ret: " + ret);
			System.out.println("Ret: " + ret);
		return ret;
	}

	// UNUSED
	public Actions choose(UnitState state) {
		Actions action = null;
		double rnd = rand.nextDouble();
		if (rnd < epsilon) {
			// select random
			action = Actions.values()[rand.nextInt(Actions.values().length)];
		} else {
			action = getMaxAction(state);
		}
		return action;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

}

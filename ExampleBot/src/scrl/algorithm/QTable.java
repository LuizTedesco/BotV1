package scrl.algorithm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import scrl.model.Actions;
import scrl.model.UnitState;

public class QTable extends ConcurrentHashMap<UnitState, Map<Actions, Double>>{
	//public class QTable extends HashMap<UnitState, Map<Actions, Double>> {	

	private static final long serialVersionUID = 3826717973754083254L;
	private Random rand = new Random();
	private double epsilon = 0.1;

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
		//System.out.println(pState.toString());
		//System.out.println("pState");
		//System.out.println(pState);
		
		System.out.println("vai pegar o map para p estate");
		Map<Actions, Double> map = this.get(pState);
		System.out.println(map);
		
		double max = Double.NEGATIVE_INFINITY;
		Actions ret = null;
// aqui		
			for (Actions act : map.keySet()) {
				if (map.get(act) > max) {
					max = map.get(act);
					ret = act;
				}
			}

		return ret;
	}

	public Actions choose(UnitState state) {
		Actions action = null;
		double rnd = rand.nextDouble();
		if (rnd < epsilon) {
			// select random
			action = Actions.values()[rand.nextInt(Actions.values().length)];
		} else {
			// select max action
			action = getMaxAction(state);
		}

		return action;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

}

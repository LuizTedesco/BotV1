package scrl.algorithm;

import java.io.IOException;
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
		//System.out.println("getMaxAction");
		Map<Actions, Double> map = this.get(pState);
		//System.out.println("pegou o map");
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função getNextAction");
			TestBotSC1.log(" Pstate " +pState.toString()); // ERROR
			TestBotSC1.log(" Map " +map.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("max");
		double max = Double.NEGATIVE_INFINITY;
		Actions ret = null;
			for (Actions act : map.keySet()) {
				if (map.get(act) > max) {
					max = map.get(act);
					ret = act;
				}
			}
			//System.out.println("no for");
		return ret;
	}

	public Actions choose(UnitState state) {
		Actions action = null;
		double rnd = rand.nextDouble();
		if (rnd < epsilon) {
			// select random
			try {
				TestBotSC1.log(Thread.currentThread().getId()+" Randomic Action");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

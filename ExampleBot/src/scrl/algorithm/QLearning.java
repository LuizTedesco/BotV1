package scrl.algorithm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import scrl.model.Actions;
import scrl.model.SCMDP;
import scrl.model.UnitState;

public class QLearning implements Serializable{
	private static final long serialVersionUID = -6943736143750359469L;
	private static final double GAMA = 0.8;
	private static final double ALPHA = 0.9;

	
	
	protected Map<UnitState, Map<Actions, Double>> q;
	private SCMDP model;

	private Collection<UnitState> states;
	//private Map<String, UnitState> states;
	private Collection<Actions> actions;

	public QLearning(SCMDP model) {
		this.setModel(model);
		this.states = model.getStates();
		//this.states = model.getStatesMap();
		this.actions = model.getActions();
		q = new HashMap<>(states.size());
		
		for (UnitState state : states) {
			Map<Actions, Double> actionValues = new HashMap<>();
			for (Actions action : actions)
				actionValues.put(action, (double) 0);
		q.put(state, actionValues);
		}
		
		/*for(Entry<String, UnitState> entry: states.entrySet())
		{
			//entry.getKey()
			//entry.getValue()
			Map<Actions, Double> actionValues = new HashMap<>();
			for (Actions action : actions) 
				actionValues.put(action, (double) 0);
		q.put(entry.getValue(), actionValues);
		}*/
	}
	
	/*@Override
	public int hashCode(){
		final int prime = 13;
		int result = 1;
		return result;
	}*/
	
	public Map<UnitState, Map<Actions, Double>> getQ() {
		return q;
	}
	
	public void updateQ(UnitState state, Actions action) {
		double reward = scrl.model.RewardFunction.getValue(state, action);
		//double reward = model.getRewardFunction().getValue(state, action);
		double newQValue= computeQ(state, action, reward);
		Map<Actions, Double> computedActionValue = new HashMap<>();
		computedActionValue.put(action, newQValue);
		q.put(state, computedActionValue);
		//q[state.hashCode()][actions.indexOf(action)] = computeQ(state, action, reward);
	}

	private double computeQ(UnitState state, Actions action, double reward) {
		// get current q value
		double cq = q.get(state).get(action);
		//double cq = q[index(state)][action.hashCode()];
		// compute the right side of the equation
		double value = reward + (GAMA * getMax(state)) - cq;
		// compute new q value
		double newq = cq + ALPHA * value;

		return newq;
	}

	protected double getMax(UnitState pState) {
		double max = 0;
		// search for the Q v for each state
		for (Actions action : actions) {
			double value = q.get(pState).get(action);
			if (value > max) {
				max = value;
			}
		}

		return max;
	}

	@SuppressWarnings("unchecked")
	public void deserialize() {
		//System.out.println("Deserialize");
		try {
			FileInputStream fis = new FileInputStream("marineTable.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			q = (Map<UnitState, Map<Actions, Double>>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			//System.out.println("File nao abriu");
			e.printStackTrace();
		}
	}

	public void serialize() {
		//System.out.println("Serialize");
		try {
			//System.out.println(" pra ver se entra aqui no Try");
			// qtable = new FileOutputStream("marineTable.txt");
			FileOutputStream fos = new FileOutputStream("marineTable.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(q);
			fos.close();
		} catch (IOException e) {
			//System.out.println("Nao conseguiu escrever no arquivo na hora de fechar");
			e.printStackTrace();
		}
	}


	public SCMDP getModel() {
		return model;
	}


	public void setModel(SCMDP model) {
		this.model = model;
	}

}

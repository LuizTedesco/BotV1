package scrl.algorithm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import scrl.model.Actions;
import scrl.model.SCMDP;
import scrl.model.UnitState;

public class QLearning implements Serializable {
	private static final long serialVersionUID = -6943736143750359469L;
	private static final double GAMA = 0.8;
	private static final double ALPHA = 0.9;

	protected QTable q;
	private SCMDP model;

	private Collection<UnitState> states;
	private Collection<Actions> actions;

	public QLearning(SCMDP model) {
		this.setModel(model);
		this.states = model.getStates();
		this.actions = model.getActions();
		q = new QTable(states, actions);
	}

	public void updateQ(UnitState state, Actions action) {
		System.out.println("updateQ");
		double reward = scrl.model.RewardFunction.getValue(state, action);
		// double reward = model.getRewardFunction().getValue(state, action);
		
		double newQValue = computeQ(state, action, reward);
		
		System.out.println("newCCHashMap");
		//Map<Actions, Double> computedActionValue = new HashMap<>();
		
		
		Map<Actions, Double> computedActionValue = new ConcurrentHashMap<>();
		computedActionValue = q.get(state);
		for (Actions actions2 : actions) {
			if(actions2 == action)
				computedActionValue.put(action, newQValue);
		}
		
		System.out.println("addTheNewHashMapOn Q");
		q.put(state, computedActionValue);
		
		// q[state.hashCode()][actions.indexOf(action)] = computeQ(state,
		// action, reward);
		System.out.println("ACCTUALY DID THAT");
		
	}

	private double computeQ(UnitState state, Actions action, double reward) {
		System.out.println("compute Q");
		// get current q value
		
		System.out.println("vai pegar cq");
		double cq = q.get(state).get(action);
		System.out.println("pegou cq:  "+ cq);
		// double cq = q[index(state)][action.hashCode()];
		// compute the right side of the equation
		System.out.println("vai calcular VALUE");
		double value = reward + (GAMA * getMax(state)) - cq;
		// compute new q value
		double newq = cq + ALPHA * value;

		System.out.println("computou newQ:  "+ newq);
		return newq;
	}

	protected double getMax(UnitState pState){
		double max = 0;
		// search for the Q v for each state
		System.out.println("vai entrar no for do GetMAX QLEARNING CLASS");
		for (Actions action : actions) {
			System.out.println("action "+action);
			System.out.println("estado "+pState);
			System.out.println("q.get stado " +q.get(pState));
			System.out.println("q.get stado VALUES" +q.get(pState).values());
			
			
			
			// tá aqui nessa linha o problema
			
			System.out.println("q");
			System.out.println(q);
			System.out.println("imprimiu q inteiro");

			System.out.println(q.size());
			System.out.println(q.values());
			double value = q.get(pState).get(action);
			System.out.println("value " +value);
			if (value > max) {
				max = value;
			}
			System.out.println("vai pra prox iteração do FOR");
		}
		System.out.println("saiu do for");

		return max;
	}

	public void deserialize() {
		// System.out.println("Deserialize");
		try {
			FileInputStream fis = new FileInputStream("marineTable.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			q = (QTable) ois.readObject();
			ois.close();
		} catch (Exception e) {
			// System.out.println("File nao abriu");
			e.printStackTrace();
		}
	}

	public void serialize() {
		// System.out.println("Serialize");
		try {
			// System.out.println(" pra ver se entra aqui no Try");
			// qtable = new FileOutputStream("marineTable.txt");
			FileOutputStream fos = new FileOutputStream("marineTable.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(q);
			fos.close();
		} catch (IOException e) {
			// System.out.println("Nao conseguiu escrever no arquivo na hora de
			// fechar");
			e.printStackTrace();
		}
	}
	
	public QTable getQTable() {
		return q;
	}

	public SCMDP getModel() {
		return model;
	}

	public void setModel(SCMDP model) {
		this.model = model;
	}
	
}

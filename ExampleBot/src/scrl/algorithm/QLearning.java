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
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;
import scrl.tests.TestBotSC1;

public class QLearning implements Serializable {
	private static final long serialVersionUID = -6943736143750359469L;
	private static final double GAMA = 0.9;
	private static final double ALPHA = 0.1;
	//private static final double ALPHA = 0.2;

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

	public void updateQ(UnitState state, UnitState next, Actions action) {
		
		double reward = scrl.model.RewardFunction.getValue(state,next, action);
		
		double newQValue = computeQ(state, next, action, reward);
		Map<Actions, Double> computedActionValue = new ConcurrentHashMap<>();
		computedActionValue = q.get(state);
		for (Actions actions2 : actions) {
			if(actions2 == action)
				computedActionValue.put(action, newQValue);
		}
		q.put(state, computedActionValue);
		/*TestBotSC1.log("computedActionValue: "+ computedActionValue);*/
	}

	private double computeQ(UnitState current, UnitState next, Actions action, double reward) {
		double oldQ = q.get(current).get(action); // Q(s,a)
		double bracedValue = reward + (GAMA * getMax(next)) - oldQ;
		return oldQ + ALPHA*bracedValue;
	}


	protected double getMax(UnitState pState){
		double max = Double.NEGATIVE_INFINITY;
		/*TestBotSC1.log("estado novo: "+pState.toStringDebugStateReward());*/
		for (Actions action : actions) {
			double value = q.get(pState).get(action);
			if (value > max) {
				max = value;
			}
		}
		return max;
	}
	
	public void deserialize() {
		System.out.println("Deserialize");
		try {
			FileInputStream fis = new FileInputStream("marineTable.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			q = (QTable) ois.readObject();
			/*System.out.println("Tabela Q");
			System.out.println(q.toString());*/
			ois.close();
		} catch (Exception e) {
			// System.out.println("File nao abriu");
			e.printStackTrace();
		}
	}

	public void serialize() {
		System.out.println("Serialize");
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
	
	public void getFakePerfectQTable() {

	}
	

	
}

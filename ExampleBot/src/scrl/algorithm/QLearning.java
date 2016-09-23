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
import scrl.tests.TestBotSC1;

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

	public void updateQ(UnitState state, UnitState next, Actions action) {
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função updateQ");
			TestBotSC1.log("Print state: " + state.toString());
			TestBotSC1.log("Print nextState: "+next.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double reward = scrl.model.RewardFunction.getValue(state,next, action);
		double newQValue = computeQ(state, action, reward);
		Map<Actions, Double> computedActionValue = new ConcurrentHashMap<>();
		computedActionValue = q.get(state);
		for (Actions actions2 : actions) {
			if(actions2 == action)
				computedActionValue.put(action, newQValue);
		}
		q.put(state, computedActionValue);
	}

	private double computeQ(UnitState state, Actions action, double reward) {
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função computeQ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double cq = q.get(state).get(action);
		//System.out.println("pegou cq:  "+ cq);
	//	System.out.println("vai calcular VALUE");
		double value = reward + (GAMA * getMax(state)) - cq;
		double newq = cq + ALPHA * value;
//		System.out.println("computou newQ:  "+ newq);
		try {
			TestBotSC1.log("cq:" + cq);
			TestBotSC1.log("value:" + value);
			TestBotSC1.log("newQ:" + newq);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newq;
	}

	protected double getMax(UnitState pState){
		double max = 0;		
		for (Actions action : actions) {
			
			try {
				TestBotSC1.log("action "+action);
				TestBotSC1.log("estado "+pState);
				TestBotSC1.log("q.get stado " +q.get(pState));
				TestBotSC1.log("q.get stado VALUES" +q.get(pState).values());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			double value = q.get(pState).get(action);
			if (value > max) {
				max = value;
			}
			
		}
		

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

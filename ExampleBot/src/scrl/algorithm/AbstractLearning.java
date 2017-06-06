package scrl.algorithm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import scrl.model.RewardFunction;
import scrl.model.SCMDP;
import scrl.model.State;
import scrl.model.actions.Action;

public abstract class AbstractLearning implements Serializable {
	private static final long serialVersionUID = 1L;
	protected static final double GAMA = 0.9;
	protected static final double ALPHA = 0.1;

	protected QTable q;
	protected SCMDP model;

	public AbstractLearning(SCMDP model) {
		this.model = model;
		q = new QTable(model.getStates(), model.getActions());
	}

	public void updateQ(State state, State next, Action action) {
		double reward = RewardFunction.getValue(state, next, action);
		updateQTable(state, next, action, reward, q);

		//		double newQValue = computeQ(state, next, action, reward);
		//		Map<Action, Double> computedActionValue = q.get(state);
		//		computedActionValue.put(action, newQValue);
		//		q.put(state, computedActionValue);
		
		
	}

	protected abstract void updateQTable(State current, State next, Action action, double reward, QTable q2);

	protected double getMax(State pState) {
		double max = Double.NEGATIVE_INFINITY;
		for (Action action : model.getActions()) {
			double value = q.get(pState).get(action.toString());
			if (value > max) {
				max = value;
			}
		}
		return max;
	}

	public QTable getQTable() {
		return q;
	}

	public SCMDP getModel() {
		return model;
	}
	
	public void deserialize() {
		try {
			FileInputStream fis = new FileInputStream("marineTable.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			q = (QTable) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void serialize() {
		System.out.println("Serialize");
		try {
			FileOutputStream fos = new FileOutputStream("marineTable.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(q);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

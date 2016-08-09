package scrl.algorithm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jalt.model.action.Action;
import org.jalt.model.state.State;

import scrl.model.Actions;
import scrl.model.SCMDP;
import scrl.model.UnitState;

public class QLearning {
	private static final double GAMA = 0.8;
	private static final double ALPHA = 0.9;

	// q[][0] = atck
	// q[][1] = explore
	// q[][2] = flee
	protected Map<UnitState, Map<Action, Double>> q;
	private SCMDP model;

	private Collection<State> states;
	private Collection<Action> actions;

	public QLearning(SCMDP model) {
		this.model = model;
		this.states = model.getStates();
		this.actions = model.getActions();
		// creatStates2();
		// QTable<QTableItem> builder = new QTable<>(states,actions);
		q = new HashMap<>(states.size());
		for (UnitState state : states) {
			for (Action action : actions) {
				Map<Action, Double> actionValues = new HashMap<>();
				actionValues.put(action, 0);
				q.put(state, actionValues);
			}
		}
	}

	public void updateQ(State state, Actions action) {
		double reward = model.getRewardFunction().getValue(state, action);
		q[state.hashCode()][actions.indexOf(action)] = computeQ(state, action, reward);
	}

	private double computeQ(State state, Action action, double reward) {
		// get current q value
		double cq = q[index(state)][action.hashCode()];
		// compute the right side of the equation
		double value = reward + (GAMA * getMax(state)) - cq;
		// compute new q value
		double newq = cq + ALPHA * value;

		return newq;
	}

	protected double getMax(State pState) {
		double max = 0;
		// search for the Q v for each state
		for (Action action : actions) {
			double value = q[index(pState)][actions.indexOf(action)];
			if (value > max) {
				max = value;
			}
		}

		return max;
	}

	public void read() {
		try {
			// FileInputStream fin = new FileInputStream("marineTable.txt");
			FileInputStream fin = new FileInputStream("Table.txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			q = (double[][]) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.out.println("File nao abriu");
			e.printStackTrace();
		}
	}

	public void write() {
		FileOutputStream qtable;
		System.out.println(" pra ver se entra aqui");
		try {
			System.out.println(" pra ver se entra aqui no Try");
			// qtable = new FileOutputStream("marineTable.txt");
			qtable = new FileOutputStream("Table.txt");
			ObjectOutputStream oos = new ObjectOutputStream(qtable);
			oos.writeObject(q);
			oos.close();
		} catch (IOException e) {
			System.out.println("Nao conseguiu escrever no arquivo na hora de fechar");
			e.printStackTrace();
		}
	}

}

package scrl.rl;

import java.io.File;
import java.io.Serializable;

import scrl.algorithm.QLearning;
import scrl.model.SCMDP;
import scrl.model.State;
import scrl.model.actions.Action;
import scrl.tests.Main;
import scrl.utils.Log;

public class SCRL implements Serializable {

	private static final long serialVersionUID = 7537131060045100702L;

	private SCMDP model;
	private QLearning learning;

	public SCRL() {
		model = new SCMDP();
		learning = new QLearning(model);
	}

	public void init(int matchNumber) {
		File f = new File("marineTable.ser");
		if (f.exists())
			learning.deserialize();

		double epsilon = (matchNumber / (Main.MAX_GAMES * 1d));
		learning.getQTable().setEpsilon(epsilon);

		Log.log("Epsilon: " + epsilon);
	}

	public void updateState(Action action, State curState, State newState) {
		learning.updateQ(curState, newState, action);
	}

	public Action getNextAction(State pState) {
		return learning.getQTable().chooseNextAction(pState);
	}

	public void end() {
		learning.serialize();
	}

}

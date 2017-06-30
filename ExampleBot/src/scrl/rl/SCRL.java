package scrl.rl;

import java.io.File;
import java.io.Serializable;

import scrl.model.SCMDP;
import scrl.model.State;
import scrl.model.actions.Action;
import scrl.model.actions.EGreedyActionChooser;
import scrl.rl.algorithm.AbstractLearning;
import scrl.rl.algorithm.DynaQ;
import scrl.rl.algorithm.QLearning;

public class SCRL implements Serializable {

	private static final long serialVersionUID = 7537131060045100702L;

	private SCMDP model;
	private AbstractLearning learning;
	private EGreedyActionChooser actionChooser;

	public SCRL() {
		model = new SCMDP();
		learning = new QLearning(model);
		// learning = new DynaQ(model);
		actionChooser = new EGreedyActionChooser(learning.getQTable());
	}

	public void init(int matchNumber) {
		File f = new File("marineTable.ser");
		if (f.exists())
			learning.deserialize();
	}

	public void updateState(Action action, State curState, State newState) {
		learning.updateQ(curState, newState, action);
	}

	public Action getNextAction(State pState) {
		return actionChooser.getAction(pState);
	}

	public void end() {
		learning.serialize();
	}

	public SCMDP getModel() {
		return model;
	}

	public void setModel(SCMDP model) {
		this.model = model;
	}

	public AbstractLearning getLearning() {
		return learning;
	}

	public void setLearning(DynaQ learning) {
		this.learning = learning;
	}

}

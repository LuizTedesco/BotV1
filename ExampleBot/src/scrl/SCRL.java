package scrl;

import java.io.Serializable;

import scrl.algorithm.QLearning;
import scrl.algorithm.QTable;
import scrl.model.Actions;
import scrl.model.SCMDP;
import scrl.model.UnitState;

public class SCRL implements Serializable {

	private static final long serialVersionUID = 7537131060045100702L;

	private SCMDP model;
	private QLearning learning;
	private UnitState current; // eu to usando esse cara?

	public SCRL() {
		model = new SCMDP();
		learning = new QLearning(model);
	}

	public void init(int matchNumber) {
		if(matchNumber!=0)
			learning.deserialize();
		//TODO remover Epsilon dinamico
		//learning.getQTable().setEpsilon(1 - (matchNumber / (TestBotSC1.MAX_GAMES * 1d)));
	}

	public void updateState(Actions action, UnitState state) {
		System.out.println("updateState");
		learning.updateQ(state, action);
		setCurrent(state);
	}

	public Actions getNextAction(UnitState pState) {
		System.out.println("get next action");
		
		QTable table = learning.getQTable();
		//System.out.println(table);
		//System.out.println(pState);
		return table.getMaxAction(pState);
	}

	public UnitState getCurrent() {
		return current;
	}

	public void setCurrent(UnitState current) {
		this.current = current;
	}

	public void end(boolean isWinner) {
		learning.serialize();
	}
}

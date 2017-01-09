package scrl;

import java.io.File;
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

	public SCRL() {
		model = new SCMDP();
		learning = new QLearning(model);
	}

	public void init(int matchNumber) {
		System.out.println("SCRL init, match: " + matchNumber);
		File f = new File("marineTable.ser");
		if(f.exists())
			learning.deserialize();
		//if(matchNumber!=0)
			//learning.deserialize();
		//TODO remover Epsilon dinamico
		//learning.getQTable().setEpsilon(1 - (matchNumber / (TestBotSC1.MAX_GAMES * 1d)));
	}

	public void updateState(Actions action, UnitState curState, UnitState newState) {
//		TestBotSC1.log("Entrou na função updateState");
		learning.updateQ(curState,newState, action);
	}

	public Actions getNextAction(UnitState pState) {
		QTable table = learning.getQTable();
//		TestBotSC1.log("Entrou na função getNextAction");
		return table.getMaxAction(pState);
	}

	public void end() {
		learning.serialize();
	}
}

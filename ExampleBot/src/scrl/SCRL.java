package scrl;

import java.io.File;
import java.io.Serializable;

import scrl.algorithm.QLearning;
import scrl.algorithm.QTable;
import scrl.model.Actions;
import scrl.model.SCMDP;
import scrl.model.UnitState;
import scrl.tests.TestBotSC1;

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
		if(f.exists())
			learning.deserialize();
		learning.getQTable().setEpsilon((matchNumber / (TestBotSC1.MAX_GAMES * 1d)));
		TestBotSC1.log("Epsilon: "+ learning.getQTable().getEpsilon());
//		System.out.println("Epsilon: "+ learning.getQTable().getEpsilon());
	}
	
	public void initTeste(int match) {
		learning.getFakePerfectQTable();
	}

	public void updateState(Actions action, UnitState curState, UnitState newState) {
		learning.updateQ(curState,newState, action);
	}

	public Actions getNextAction(UnitState pState) {
		QTable table = learning.getQTable();
		return table.chooseNextAction(pState);
	}

	public void end() {
		learning.serialize();
	}

}

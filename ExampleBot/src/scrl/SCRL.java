package scrl;

import java.io.File;
import java.io.IOException;
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
	private UnitState current; // eu to usando esse cara?

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
		TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função updateState");
		setCurrent(curState);
		
		learning.updateQ(curState,newState, action);
		
	}

	public Actions getNextAction(UnitState pState) {
		//System.out.println("getNextAction");
		QTable table = learning.getQTable();
		TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função getNextAction");
		return table.getMaxAction(pState);
	}

	public UnitState getCurrent() {
		return current;
	}

	public void setCurrent(UnitState current) {
		this.current = current;
	}

	public void end() {
		learning.serialize();
	}
}

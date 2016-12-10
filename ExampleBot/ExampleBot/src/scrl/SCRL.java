package scrl;

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
		if(matchNumber!=0)
			learning.deserialize();
		//TODO remover Epsilon dinamico
		//learning.getQTable().setEpsilon(1 - (matchNumber / (TestBotSC1.MAX_GAMES * 1d)));
	}

	public void updateState(Actions action, UnitState curState, UnitState newState) {
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função updateState");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setCurrent(curState);
		
		//System.out.println("updateState function");
		//System.out.println("cur: "+curState);
		//System.out.println("new: "+newState);
		
		learning.updateQ(curState,newState, action);
		
	}

	public Actions getNextAction(UnitState pState) {
		//System.out.println("getNextAction");
		QTable table = learning.getQTable();
		try {
			TestBotSC1.log(Thread.currentThread().getId()+" Entrou na função getNextAction");
			//TestBotSC1.log(Thread.currentThread().getId()+" Qtable: "+ table);
			//TestBotSC1.log(Thread.currentThread().getId()+" state: "+ pState);
			//TestBotSC1.log(Thread.currentThread().getId()+" BU");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

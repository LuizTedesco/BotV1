package scrl;

import java.io.Serializable;
import java.util.Map;

import scrl.algorithm.QLearning;
import scrl.model.Actions;
import scrl.model.SCMDP;
import scrl.model.UnitState;

public class SCRL implements Serializable {

	private static final long serialVersionUID = 7537131060045100702L;

	private SCMDP model;
	private QLearning learning;
	private UnitState current;

	public SCRL() {
		model = new SCMDP();
		learning = new QLearning(model);
	}

	public void init(int matchNumber) {
		learning.deserialize();
	}

	public void updateState(Actions action, UnitState state) {
		learning.updateQ(state, action);
		setCurrent(state);
	}

	public Actions getNextAction(UnitState pState) {
		if (pState == null)
			return null;
		
		Map<UnitState, Map<Actions, Double>> table = learning.getQ();
		System.out.println(table);
		
		//String hashVal = pState.getHp().toString() + pState.getHpFromNearbyEnemies().toString() + pState.getNumberOfEnemyUnitsThatCanBeAttacked().toString() + pState.getNumberOfEnemyUnitsThatCanAttackMe().toString();
		
		
		//		if(table.get(hashVal) == null) //////// pState nao t� hasheado, entao, como achar ele na tabela?
		//	System.out.println("table.pstate == NULO AQUi");
		//else{
		//System.out.println("table.pstate == NAO NULO AQUi");
		//			System.out.println(table.get(hashVal));
		//}
		
		double value0 = learning.getQ().get(pState).get(Actions.ATTACK); // atck
		//double value0 = learning.getQ().get(pState).get(Actions.ATTACK); // atck
		double value1 = learning.getQ().get(pState).get(Actions.EXPLORE); // atck
		double value2 = learning.getQ().get(pState).get(Actions.FLEE); // atck
		double max = Math.max(value0, Math.max(value1, value2));
		if (max == value1)
			return Actions.EXPLORE;
		else if (max == value2)
			return Actions.FLEE;
		else if (max == value0)
			return Actions.ATTACK;
	return Actions.EXPLORE;
	
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

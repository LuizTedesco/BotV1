package scrl;

import java.io.Serializable;

import scrl.algorithm.QLearning;
import scrl.model.Actions;
import scrl.model.SCMDP;
import scrl.model.UnitState;

//public class SCBot {
//public static void main(String[] args){
//new Rn().Run();
//}
//}

//class Rn{
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
		if (matchNumber != 1) {
			learning.read();
		}
	}

	public void updateState(Actions action, UnitState state) {
		learning.updateQ(state, action);
		current = pState;
	}

	public Actions getNextAction(UnitState pState) {
		if (pState == null)
			return null;
		// nao tem index, tem q procurar em STATES

		int stateIndex = 0;
		for (UnitState stt : model.getStates()) {
			if (stt.getHp() == pState.getHp() && stt.getHpFromNearbyEnemies() == pState.getHpFromNearbyEnemies()
					&& stt.getNumberOfEnemyUnitsThatCanAttackMe() == pState.getNumberOfEnemyUnitsThatCanAttackMe()
					&& stt.getNumberOfEnemyUnitsThatCanBeAttacked() == pState.getNumberOfEnemyUnitsThatCanBeAttacked()) {
				stateIndex = stt.getIndex();
				System.out.println("********************************");
				System.out.println("index:" + stateIndex);
				System.out.println("qatck" + q[stateIndex][0]);
				System.out.println("qexplore:" + q[stateIndex][1]);
				System.out.println("qflee:" + q[stateIndex][2]);
				System.out.println();

				double value0 = q[stateIndex][0]; // atck
				double value1 = q[stateIndex][1]; // explore
				double value2 = q[stateIndex][2]; // flee
				double max = Math.max(value0, Math.max(value1, value2));
				if (max == value1)
					return Actions.EXPLORE;
				else if (max == value2)
					return Actions.FLEE;
				else if (max == value0)
					return Actions.ATTACK;
			}

		}
		return Actions.EXPLORE;
	}

	public void end(Boolean isWinner) {
	}
}

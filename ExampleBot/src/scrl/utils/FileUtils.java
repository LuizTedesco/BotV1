package scrl.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scrl.algorithm.QTable;
import scrl.model.State;
import scrl.model.StateAction;
import scrl.model.actions.Action;
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;

public abstract class FileUtils {

	public static void printOrganizedStateList() {
		List<State> stado = new ArrayList<>();
		for (RangeHP mediumHpFromNearbyEnemies : RangeHP.values()) {
			for (RangeUnits numberOfEnemiesUnitsNearby : RangeUnits.values()) {
				for (RangeHP hpFromNearbyAllies : RangeHP.values()) {
					for (RangeUnits numberOfAlliesUnitsNearby : RangeUnits.values()) {
						State newUnit = new State(mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby,
								hpFromNearbyAllies, numberOfAlliesUnitsNearby);
						stado.add(newUnit);
					}
				}
			}
		}
		PrintWriter qwriter;
		try {
			qwriter = new PrintWriter("states.txt", "UTF-8");
			for (State unitState : stado) {
				qwriter.println(unitState.toString2());
			}
			qwriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void qTableToFile() {
		QTable qT;
		try {
			FileInputStream fis = new FileInputStream("marineTable.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			qT = (QTable) ois.readObject();
			try {
				PrintWriter qwriter = new PrintWriter("qTable.txt", "UTF-8");
				qwriter.println(qT.toString());
				qwriter.close();
			} catch (IOException e) {
				// do something
			}

			ois.close();
		} catch (Exception e) {
			// System.out.println("File nao abriu");
			e.printStackTrace();
		}
	}

	public static void policyToFile() {
		List<StateAction> policyDataList = new ArrayList<>();
		QTable qT;
		try {
			FileInputStream fis = new FileInputStream("marineTable.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			qT = (QTable) ois.readObject();
			Map<Action, Double> map;
			for (State state : qT.keySet()) {
				double max = Double.NEGATIVE_INFINITY;
				map = qT.get(state);
				Action bestAction = null;
				for (Action act : map.keySet()) {
					if (map.get(act) > max) {
						max = map.get(act);
						bestAction = act;
					}
				}
				StateAction policyData = new StateAction(state, bestAction);
				policyDataList.add(policyData);
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			PrintWriter qwriter = new PrintWriter("policy.txt", "UTF-8");
			qwriter.println(policyDataList.toString());
			qwriter.close();
		} catch (IOException e) {
			// do something
		}
	}
}

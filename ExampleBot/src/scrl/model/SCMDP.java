package scrl.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import static scrl.model.Actions.*;
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;

public class SCMDP {
	List<Actions> actions = Arrays.asList(ATTACK, EXPLORE, FLEE);
	Set<UnitState> states;
	Map<String, UnitState> statesMap;

	public SCMDP() {
		states = creatStates();
		statesMap = creatStatesMap();
	}

//	Map<String, String> map = new HashMap<String, String>();

	private final Set<UnitState> creatStates() {
		final Set<UnitState> sts = new HashSet<UnitState>();
		for (RangeHP hp : RangeHP.values()) {
			for (RangeHP hpFromNearbyEnemies : RangeHP.values()) {
				for (RangeUnits numberOfEnemyUnitsThatCanBeAttacked : RangeUnits.values()) {
					for (RangeUnits numberOfEnemyUnitsThatCanAttackMe : RangeUnits.values()) {
						sts.add(new UnitState(hp, hpFromNearbyEnemies, numberOfEnemyUnitsThatCanBeAttacked, numberOfEnemyUnitsThatCanAttackMe));
					}
				}
			}
		}
		//System.out.println(sts.size());
		return sts;
	}
	
	
	private final Map<String, UnitState> creatStatesMap() {
		final Map<String,UnitState> stsDif = new HashMap<String,UnitState>();
		for (RangeHP hp : RangeHP.values()) {
			for (RangeHP hpFromNearbyEnemies : RangeHP.values()) {
				for (RangeUnits numberOfEnemyUnitsThatCanBeAttacked : RangeUnits.values()) {
					for (RangeUnits numberOfEnemyUnitsThatCanAttackMe : RangeUnits.values()) {
						stsDif.put(hp.toString()+hpFromNearbyEnemies.toString()+numberOfEnemyUnitsThatCanBeAttacked.toString()+numberOfEnemyUnitsThatCanAttackMe.toString(), new UnitState(hp, hpFromNearbyEnemies, numberOfEnemyUnitsThatCanBeAttacked, numberOfEnemyUnitsThatCanAttackMe));
					}
				}
			}
		}
		//System.out.println(sts.size());
		return stsDif;
	}
	
	public Map<String, UnitState> getStatesMap() {
		return statesMap;
	}

	public Set<UnitState> getStates() {
		return states;
	}

	public List<Actions> getActions() {
		return actions;
	}
}

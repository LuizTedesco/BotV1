package scrl.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static scrl.model.Actions.*;
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;

public class SCMDP {
	List<Actions> actions = Arrays.asList(ATTACK, EXPLORE, FLEE);
	Set<UnitState> states;

	public SCMDP() {
		states = creatStates();
	}

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
		return sts;
	}

	public Set<UnitState> getStates() {
		return states;
	}

	public List<Actions> getActions() {
		return actions;
	}
}

package scrl.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static scrl.model.Actions.*;

import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;

public class SCMDP {
	List<Actions> actions = Arrays.asList(EXPLORE, FLEE, ATTACK);
	Set<UnitState> states;

	public SCMDP() {
		states = creatStates();
	}

	private final Set<UnitState> creatStates() {
		final Set<UnitState> sts = new HashSet<UnitState>();
			for (RangeHP mediumHpFromNearbyEnemies : RangeHP.values()) {
				for (RangeUnits numberOfEnemiesUnitsNearby : RangeUnits.values() ) {
					for (RangeHP hpFromNearbyAllies : RangeHP.values() ) {
						for (RangeUnits numberOfAlliesUnitsNearby : RangeUnits.values() ) {
								UnitState newUnit = new UnitState(mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, hpFromNearbyAllies, numberOfAlliesUnitsNearby);
								sts.add(newUnit);
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

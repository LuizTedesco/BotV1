package scrl.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrl.model.actions.Action;
import scrl.model.actions.Attack;
import scrl.model.actions.Explore;
import scrl.model.actions.Flee;
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;

public class SCMDP {
	List<Action> actions;
	Set<State> states;

	@SuppressWarnings("unchecked")
	public SCMDP() {
		actions = (List<Action>) getValidActions();
		states = creatStates();
	}

	private final Set<State> creatStates() {
		final Set<State> sts = new HashSet<State>();
		for (RangeHP mediumHpFromNearbyEnemies : RangeHP.values()) {
			for (RangeUnits numberOfEnemiesUnitsNearby : RangeUnits.values()) {
				for (RangeHP hpFromNearbyAllies : RangeHP.values()) {
					for (RangeUnits numberOfAlliesUnitsNearby : RangeUnits.values()) {
						State newUnit = new State(mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby,
								hpFromNearbyAllies, numberOfAlliesUnitsNearby);
						sts.add(newUnit);
					}
				}
			}
		}
		return sts;
	}

	public static final List<? extends Action> getValidActions() {
		return Arrays.asList(new Explore(), new Flee(), new Attack());
	}

	public Set<State> getStates() {
		return states;
	}

	public List<Action> getActions() {
		return actions;
	}
}

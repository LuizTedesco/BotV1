package scrl.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static scrl.model.Actions.*;

import scrl.model.range.RangeDistance;
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;

public class SCMDP {
	List<Actions> actions = Arrays.asList(EXPLORE, FLEE, ATTACK);
	Set<UnitState> states;

	public SCMDP() {
		states = creatStates();
	}

	// Map<String, String> map = new HashMap<String, String>();

	private final Set<UnitState> creatStates() {
		final Set<UnitState> sts = new HashSet<UnitState>();
		for (RangeHP hp : RangeHP.values()) {
			for (RangeHP mediumHpFromNearbyEnemies : RangeHP.values()) {
				for (RangeUnits numberOfEnemiesUnitsNearby : RangeUnits.values() ) {
					for (RangeHP hpFromNearbyAllies : RangeHP.values() ) {
						for (RangeUnits numberOfAlliesUnitsNearby : RangeUnits.values() ) {
							for (RangeDistance distanceToClosestEnemyUnit : RangeDistance.values() ) {
//								UnitState newUnit = new UnitState(hp, mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, hpFromNearbyAllies, numberOfAlliesUnitsNearby, distanceToClosestEnemyUnit);
								UnitState newUnit = new UnitState(mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, hpFromNearbyAllies, numberOfAlliesUnitsNearby);
								sts.add(newUnit);
							}
						}
					}
				}
			}
		}
		return sts;
	}
	
	
	/*private final Set<UnitState> creatStates() {
		final Set<UnitState> sts = new HashSet<UnitState>();
		for (RangeHP hp : RangeHP.values()) {
			for (RangeHP hpFromNearbyEnemies : RangeHP.values()) {
				for (RangeUnits numberOfEnemyUnitsThatCanBeAttacked : RangeUnits.values()) {
					for (RangeUnits numberOfEnemyUnitsThatCanAttackMe : RangeUnits.values()) {
						UnitState newUnit = new UnitState(hp, hpFromNearbyEnemies, numberOfEnemyUnitsThatCanBeAttacked, numberOfEnemyUnitsThatCanAttackMe);
						//System.out.println(newUnit);
						sts.add(newUnit);
					}
				}
			}
		}
		return sts;
	}*/

	public Set<UnitState> getStates() {
		return states;
	}

	public List<Actions> getActions() {
		return actions;
	}
}

package scrl.model;

import scrl.model.actions.Action;
import scrl.model.actions.Attack;
import scrl.model.actions.Explore;
import scrl.model.actions.Flee;
import scrl.model.range.RangeUnits;
import scrl.utils.Log;

public class RewardFunction {
	public static double getValue(final State pState, State next, final Action pAction) {
		Log.log("                            ");
		if (pAction instanceof Attack) {
			if (next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: 100");
				return 100;
			} else if (next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: 90");
				return 90;
			} else if (next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: 80");
				return 80;
			} else if (next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: 70");
				return 70;
			} else if (next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: 60");
				return 60;
			} else if (next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: 50");
				return 50;
			} else if (pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO) {
				Log.log("Attack: -200");
				return -200;
			} else if (next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: -100");
				return -100;
			} else if (next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: -90");
				return -90;
			} else if (next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: -80");
				return -80;
			} else if (next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: -70");
				return -70;
			} else if (next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: -60");
				return -60;
			} else if (next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: -50");
				return -50;
			} else if (next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&& next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&& next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&& next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())) {
				Log.log("Attack: 50");
				return 50;
			} else {
				Log.log("Attack: -150");
				System.out.println("-150");
				return -150;
			}
		} else if (pAction instanceof Explore) {
			if (pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO) {
				Log.log("Explore: 100");
				return 100;
			} else {
				Log.log("Explore: -100");
				return -100;
			}
		} else if (pAction instanceof Flee) {
			Log.log("Flee");
			if (pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
					&& pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())) {
				Log.log("Flee: 100");
				return 100;
			} else if (pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&& pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())) {
				Log.log("Flee: 90");
				return 90;
			} else if (pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
					&& pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())) {
				Log.log("Flee: 80");
				return 80;
			} else if (pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&& pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())) {
				Log.log("Flee: 70");
				return 70;
			} else if (pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&& pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())) {
				Log.log("Flee: -100");
				return -100;
			} else if (pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&& pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())) {
				Log.log("Flee: -90");
				return -90;
			} else if (pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&& pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())) {
				Log.log("Flee: -80");
				return -80;
			} else if (pState.getHpFromNearbyAllies().isEqual(next.getHpFromNearbyAllies())
					&& pState.getNumberOfEnemiesUnitsNearby().isEqual(RangeUnits.SMALL)) {
				Log.log("Flee: -70");
				return -70;
			} else if (pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO) {
				Log.log("Flee: -100");
				return -100;
			} else {
				Log.log("Flee: -200");
				return -200;
			}
		} else {
			Log.log("NONE-1000");
			return -1000;
		}
	}
}
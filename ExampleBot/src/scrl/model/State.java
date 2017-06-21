package scrl.model;

import java.io.Serializable;

import scrl.model.range.RangeDistance;
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;

public class State implements Serializable {
	private static final long serialVersionUID = 7588180712283449263L;
	private RangeHP hpFromNearbyEnemies;
	private RangeUnits numberOfEnemiesUnitsNearby;
	private RangeHP hpFromNearbyAllies;
	private RangeUnits numberOfAlliesUnitsNearby;
	private RangeDistance distanceFromClosestEnemy;

	public State(RangeHP mediumHpFromNearbyEnemies, RangeUnits numberOfEnemiesUnitsNearby,
			RangeHP mediumHpFromNearbyAllies, RangeUnits numberOfAlliesUnitsNearby, RangeDistance dist) {
		this.hpFromNearbyEnemies = mediumHpFromNearbyEnemies;
		this.numberOfEnemiesUnitsNearby = numberOfEnemiesUnitsNearby;
		this.hpFromNearbyAllies = mediumHpFromNearbyAllies;
		this.numberOfAlliesUnitsNearby = numberOfAlliesUnitsNearby;
		this.distanceFromClosestEnemy = dist;
	}

	public State(double mediumHpFromNearbyEnemies, int numberOfEnemiesUnitsNearby, double mediumHpFromNearbyAllies,
			int numberOfAlliesUnitsNearby, int minDistanceFromEnemy) {
		this.hpFromNearbyEnemies = RangeHP.get(mediumHpFromNearbyEnemies);
		this.numberOfEnemiesUnitsNearby = RangeUnits.get(numberOfEnemiesUnitsNearby);
		this.hpFromNearbyAllies = RangeHP.get(mediumHpFromNearbyAllies);
		this.numberOfAlliesUnitsNearby = RangeUnits.get(numberOfAlliesUnitsNearby);
		this.distanceFromClosestEnemy = RangeDistance.get(minDistanceFromEnemy);
	}

	public String toStringDebugStateReward() {
		StringBuilder builder = new StringBuilder();
		builder.append(hpFromNearbyAllies).append(" ").append(numberOfAlliesUnitsNearby).append(" ")
				.append(hpFromNearbyEnemies).append(" ").append(numberOfEnemiesUnitsNearby);
		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HpEnemies: ").append(hpFromNearbyEnemies).append(" N Enemies: ")
				.append(numberOfEnemiesUnitsNearby).append(" HpAllies: ").append(hpFromNearbyAllies)
				.append(" N Allies: ").append(numberOfAlliesUnitsNearby).append(" dist to closest: ")
				.append(distanceFromClosestEnemy);
		return builder.toString();
	}

	public Object toString2() {
		StringBuilder builder = new StringBuilder();
		builder.append(hpFromNearbyEnemies).append(",").append(numberOfEnemiesUnitsNearby).append(",")
				.append(hpFromNearbyAllies).append(",").append(numberOfAlliesUnitsNearby).append(",")
				.append(distanceFromClosestEnemy).append(": ");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distanceFromClosestEnemy == null) ? 0 : distanceFromClosestEnemy.hashCode());
		result = prime * result + ((hpFromNearbyAllies == null) ? 0 : hpFromNearbyAllies.hashCode());
		result = prime * result + ((hpFromNearbyEnemies == null) ? 0 : hpFromNearbyEnemies.hashCode());
		result = prime * result + ((numberOfAlliesUnitsNearby == null) ? 0 : numberOfAlliesUnitsNearby.hashCode());
		result = prime * result + ((numberOfEnemiesUnitsNearby == null) ? 0 : numberOfEnemiesUnitsNearby.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (distanceFromClosestEnemy != other.distanceFromClosestEnemy)
			return false;
		if (hpFromNearbyAllies != other.hpFromNearbyAllies)
			return false;
		if (hpFromNearbyEnemies != other.hpFromNearbyEnemies)
			return false;
		if (numberOfAlliesUnitsNearby != other.numberOfAlliesUnitsNearby)
			return false;
		if (numberOfEnemiesUnitsNearby != other.numberOfEnemiesUnitsNearby)
			return false;
		return true;
	}

	public RangeHP getHpFromNearbyEnemies() {
		return hpFromNearbyEnemies;
	}

	public RangeUnits getNumberOfEnemiesUnitsNearby() {
		return numberOfEnemiesUnitsNearby;
	}

	public RangeHP getHpFromNearbyAllies() {
		return hpFromNearbyAllies;
	}

	public RangeUnits getNumberOfAlliesUnitsNearby() {
		return numberOfAlliesUnitsNearby;
	}

	public RangeDistance getDistanceFromClosestEnemy() {
		return distanceFromClosestEnemy;
	}

	public void setDistanceFromClosestEnemy(RangeDistance distanceFromClosestEnemy) {
		this.distanceFromClosestEnemy = distanceFromClosestEnemy;
	}
}

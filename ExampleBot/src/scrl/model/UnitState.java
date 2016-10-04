package scrl.model;


import java.io.Serializable;
import java.util.Objects;

import scrl.model.range.RangeDistance;
import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;


public class UnitState implements Serializable{
	private static final long serialVersionUID = 7588180712283449263L;
	private RangeHP hp;
	private RangeHP mediumHpFromNearbyEnemies;
	private RangeUnits numberOfEnemiesUnitsNearby;
	private RangeHP hpFromNearbyAllies;
	private RangeUnits numberOfAlliesUnitsNearby;
	private RangeDistance distanceToClosestEnemyUnit;

	public UnitState(RangeHP hp, RangeHP mediumHpFromNearbyEnemies, RangeUnits numberOfEnemiesUnitsNearby,
					 RangeHP hpFromNearbyAllies, RangeUnits numberOfAlliesUnitsNearby,RangeDistance distanceToClosestEnemyUnit) {
		this.hp = hp;
		this.mediumHpFromNearbyEnemies = mediumHpFromNearbyEnemies;
		this.numberOfEnemiesUnitsNearby = numberOfEnemiesUnitsNearby;
		this.hpFromNearbyAllies = hpFromNearbyAllies;
		this.numberOfAlliesUnitsNearby = numberOfAlliesUnitsNearby;
		this.distanceToClosestEnemyUnit = distanceToClosestEnemyUnit;
	}

	public UnitState(double hp2, double mediumHpFromNearbyEnemies, int numberOfEnemiesUnitsNearby, double hpFromNearbyAllies, 
			int numberOfAlliesUnitsNearby, int distanceToClosestEnemyUnit) {
		this.hp = RangeHP.get(hp2);
		this.mediumHpFromNearbyEnemies = RangeHP.get(mediumHpFromNearbyEnemies);
		this.numberOfEnemiesUnitsNearby = RangeUnits.get(numberOfEnemiesUnitsNearby);
		this.hpFromNearbyAllies = RangeHP.get(hpFromNearbyAllies);
		this.numberOfAlliesUnitsNearby = RangeUnits.get(numberOfAlliesUnitsNearby);
		this.distanceToClosestEnemyUnit = RangeDistance.get(distanceToClosestEnemyUnit);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
				builder.append(hp).append(mediumHpFromNearbyEnemies).append(numberOfEnemiesUnitsNearby)
				.append(hpFromNearbyAllies).append(numberOfAlliesUnitsNearby).append(distanceToClosestEnemyUnit);
				return builder.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if(o == this) return true;
		if(!(o instanceof UnitState)) return false;
		
		UnitState unit = (UnitState) o;
		return hp == unit.hp &&
				Objects.equals(mediumHpFromNearbyEnemies, unit.mediumHpFromNearbyEnemies) &&
				Objects.equals(numberOfEnemiesUnitsNearby, unit.numberOfEnemiesUnitsNearby) &&
				Objects.equals(hpFromNearbyAllies, unit.hpFromNearbyAllies) &&
				Objects.equals(numberOfAlliesUnitsNearby, unit.numberOfAlliesUnitsNearby) &&
				Objects.equals(distanceToClosestEnemyUnit, unit.distanceToClosestEnemyUnit);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(hp,mediumHpFromNearbyEnemies,numberOfEnemiesUnitsNearby,hpFromNearbyAllies,
							numberOfAlliesUnitsNearby,distanceToClosestEnemyUnit);
	}
		
	
	public RangeHP getHp() {
		return hp;
	}

	public RangeHP getMediumHpFromNearbyEnemies() {
		return mediumHpFromNearbyEnemies;
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

	public RangeDistance getDistanceToClosestEnemyUnit() {
		return distanceToClosestEnemyUnit;
	}

}
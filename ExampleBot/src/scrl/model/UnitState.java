package scrl.model;


import java.io.Serializable;
import java.util.Objects;

import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;


public class UnitState implements Serializable{
	private static final long serialVersionUID = 7588180712283449263L;
	private RangeHP hp; // low medium high
	private RangeHP hpFromNearbyEnemies; // low medium high
	private RangeUnits numberOfEnemyUnitsThatCanBeAttacked; // zero small medium
	// large
	private RangeUnits numberOfEnemyUnitsThatCanAttackMe; // zero small medium
															// large

	public UnitState(RangeHP hp, RangeHP hpFromNearbyEnemies, RangeUnits numberOfEnemyUnitsThatCanBeAttacked, RangeUnits numberOfEnemyUnitsThatCanAttackMe) {
		this.hp = hp;
		this.hpFromNearbyEnemies = hpFromNearbyEnemies;
		this.numberOfEnemyUnitsThatCanBeAttacked = numberOfEnemyUnitsThatCanBeAttacked;
		this.numberOfEnemyUnitsThatCanAttackMe = numberOfEnemyUnitsThatCanAttackMe;
	}

	public UnitState(double hp2, double hpFromNearbyEnemies2, int numberOfEnemyUnitsThatCanBeAttacked2, int numberOfEnemyUnitsThatCanAttackMe2) {
		this.hp = RangeHP.get(hp2);
		this.hpFromNearbyEnemies = RangeHP.get(hpFromNearbyEnemies2);
		this.numberOfEnemyUnitsThatCanBeAttacked = RangeUnits.get(numberOfEnemyUnitsThatCanBeAttacked2);
		this.numberOfEnemyUnitsThatCanAttackMe = RangeUnits.get(numberOfEnemyUnitsThatCanAttackMe2);
	}

	public boolean in(double hp2, double hpFromNearbyEnemies2, int numberOfEnemyUnitsThatCanBeAttacked2, int numberOfEnemyUnitsThatCanAttackMe2) {
		return hp.in(hp2) && hpFromNearbyEnemies.in(hpFromNearbyEnemies2) && numberOfEnemyUnitsThatCanBeAttacked.in(numberOfEnemyUnitsThatCanBeAttacked2)
				&& numberOfEnemyUnitsThatCanAttackMe.in(numberOfEnemyUnitsThatCanAttackMe2);
	}

	public RangeHP getHp() {
		return hp;
	}

	public RangeHP getHpFromNearbyEnemies() {
		return hpFromNearbyEnemies;
	}

	public RangeUnits getNumberOfEnemyUnitsThatCanBeAttacked() {
		return numberOfEnemyUnitsThatCanBeAttacked;
	}

	public RangeUnits getNumberOfEnemyUnitsThatCanAttackMe() {
		return numberOfEnemyUnitsThatCanAttackMe;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
				builder.append(hp).append(hpFromNearbyEnemies).append(numberOfEnemyUnitsThatCanBeAttacked).append(numberOfEnemyUnitsThatCanAttackMe);
				return builder.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if(o == this) return true;
		if(!(o instanceof UnitState)) return false;
		
		UnitState unit = (UnitState) o;
		return hp == unit.hp &&
				Objects.equals(hpFromNearbyEnemies, unit.hpFromNearbyEnemies) &&
				Objects.equals(numberOfEnemyUnitsThatCanBeAttacked, unit.numberOfEnemyUnitsThatCanBeAttacked) &&
				Objects.equals(numberOfEnemyUnitsThatCanAttackMe, unit.numberOfEnemyUnitsThatCanAttackMe);
	}
	
	
	@Override
	public int hashCode()
	{
		return Objects.hash(hp,hpFromNearbyEnemies,numberOfEnemyUnitsThatCanBeAttacked,numberOfEnemyUnitsThatCanAttackMe);
	}
	
	
	
}

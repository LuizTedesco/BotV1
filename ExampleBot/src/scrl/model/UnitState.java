package scrl.model;


import java.io.Serializable;
import java.util.Objects;

import scrl.model.range.RangeHP;
import scrl.model.range.RangeUnits;


public class UnitState implements Serializable{
	private static final long serialVersionUID = 7588180712283449263L;
//	private RangeHP hp;
	private RangeHP mediumHpFromNearbyEnemies;
	private RangeUnits numberOfEnemiesUnitsNearby;
	private RangeHP mediumHpFromNearbyAllies;
	private RangeUnits numberOfAlliesUnitsNearby;
//	private RangeDistance distanceToClosestEnemyUnit;

//	public UnitState(RangeHP hp, RangeHP mediumHpFromNearbyEnemies, RangeUnits numberOfEnemiesUnitsNearby,
//					 RangeHP mediumHpFromNearbyAllies, RangeUnits numberOfAlliesUnitsNearby,RangeDistance distanceToClosestEnemyUnit) {
	public UnitState(RangeHP mediumHpFromNearbyEnemies, RangeUnits numberOfEnemiesUnitsNearby,
			 RangeHP mediumHpFromNearbyAllies, RangeUnits numberOfAlliesUnitsNearby) {	
//		this.hp = hp;
		this.mediumHpFromNearbyEnemies = mediumHpFromNearbyEnemies;
		this.numberOfEnemiesUnitsNearby = numberOfEnemiesUnitsNearby;
		this.mediumHpFromNearbyAllies = mediumHpFromNearbyAllies;
		this.numberOfAlliesUnitsNearby = numberOfAlliesUnitsNearby;
//		this.distanceToClosestEnemyUnit = distanceToClosestEnemyUnit;
	}

//	public UnitState(double hp2, double mediumHpFromNearbyEnemies, int numberOfEnemiesUnitsNearby, double mediumHpFromNearbyAllies, 
//			int numberOfAlliesUnitsNearby, int distanceToClosestEnemyUnit) {
	public UnitState(double mediumHpFromNearbyEnemies, int numberOfEnemiesUnitsNearby, double mediumHpFromNearbyAllies, 
			int numberOfAlliesUnitsNearby) {
//		this.hp = RangeHP.get(hp2);
		this.mediumHpFromNearbyEnemies = RangeHP.get(mediumHpFromNearbyEnemies);
		this.numberOfEnemiesUnitsNearby = RangeUnits.get(numberOfEnemiesUnitsNearby);
		this.mediumHpFromNearbyAllies = RangeHP.get(mediumHpFromNearbyAllies);
		this.numberOfAlliesUnitsNearby = RangeUnits.get(numberOfAlliesUnitsNearby);
//		this.distanceToClosestEnemyUnit = RangeDistance.get(distanceToClosestEnemyUnit);
		
		//System.out.println(Thread.currentThread().getId() + "  " + this.hp  + "  " + this.mediumHpFromNearbyEnemies  + "  " + this.numberOfEnemiesUnitsNearby  + "  " + 
			//	this.mediumHpFromNearbyAllies  + "  " + this.numberOfAlliesUnitsNearby  + "  " + this.distanceToClosestEnemyUnit);
	}


	public String toStringDebugStateReward() {
		StringBuilder builder = new StringBuilder();
				builder.append(mediumHpFromNearbyAllies).append(" ").append(numberOfAlliesUnitsNearby).append(" ")
				.append(mediumHpFromNearbyEnemies).append(" ").append(numberOfEnemiesUnitsNearby);
				return builder.toString();
	}

	@Override
	public String toString() {
//		StringBuilder builder = new StringBuilder();
//				builder.append(hp).append(mediumHpFromNearbyEnemies).append(numberOfEnemiesUnitsNearby)
//				.append(mediumHpFromNearbyAllies).append(numberOfAlliesUnitsNearby).append(distanceToClosestEnemyUnit);
//				return builder.toString();
				

		StringBuilder builder = new StringBuilder();
			builder.append("HpEnemies: ").append(mediumHpFromNearbyEnemies).append(" N Enemies: ").append(numberOfEnemiesUnitsNearby)
			.append(" HpAllies: ").append(mediumHpFromNearbyAllies).append(" N Allies: ").append(numberOfAlliesUnitsNearby);
			return builder.toString();
	}
	
	public Object toString2() {
		StringBuilder builder = new StringBuilder();
		builder.append(mediumHpFromNearbyEnemies).append(",").append(numberOfEnemiesUnitsNearby)
		.append(",").append(mediumHpFromNearbyAllies).append(",").append(numberOfAlliesUnitsNearby).append(",");
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if(o == this) return true;
		if(!(o instanceof UnitState)) return false;
		
		UnitState unit = (UnitState) o;
//		return hp == unit.hp &&
//				Objects.equals(mediumHpFromNearbyEnemies, unit.mediumHpFromNearbyEnemies) &&
//				Objects.equals(numberOfEnemiesUnitsNearby, unit.numberOfEnemiesUnitsNearby) &&
//				Objects.equals(mediumHpFromNearbyAllies, unit.mediumHpFromNearbyAllies) &&
//				Objects.equals(numberOfAlliesUnitsNearby, unit.numberOfAlliesUnitsNearby) &&
//				Objects.equals(distanceToClosestEnemyUnit, unit.distanceToClosestEnemyUnit);
		
		return 
				Objects.equals(mediumHpFromNearbyEnemies, unit.mediumHpFromNearbyEnemies) &&
				Objects.equals(numberOfEnemiesUnitsNearby, unit.numberOfEnemiesUnitsNearby) &&
				Objects.equals(mediumHpFromNearbyAllies, unit.mediumHpFromNearbyAllies) &&
				Objects.equals(numberOfAlliesUnitsNearby, unit.numberOfAlliesUnitsNearby);
	}
	
	@Override
	public int hashCode()
	{
//		return Objects.hash(hp,mediumHpFromNearbyEnemies,numberOfEnemiesUnitsNearby,mediumHpFromNearbyAllies,
//							numberOfAlliesUnitsNearby,distanceToClosestEnemyUnit);
		return Objects.hash(mediumHpFromNearbyEnemies,numberOfEnemiesUnitsNearby,mediumHpFromNearbyAllies,
				numberOfAlliesUnitsNearby);
	}
		
	
//	public RangeHP getHp() {
//		return hp;
//	}

	public RangeHP getMediumHpFromNearbyEnemies() {
		return mediumHpFromNearbyEnemies;
	}

	public RangeUnits getNumberOfEnemiesUnitsNearby() {
		return numberOfEnemiesUnitsNearby;
	}

	public RangeHP getHpFromNearbyAllies() {
		return mediumHpFromNearbyAllies;
	}

	public RangeUnits getNumberOfAlliesUnitsNearby() {
		return numberOfAlliesUnitsNearby;
	}

//	public RangeDistance getDistanceToClosestEnemyUnit() {
//		return distanceToClosestEnemyUnit;
//	}
	
	
	public UnitState(String HpEnem, String NumEenm, String HpAlly, String NumAly) {
		
		
/*		if(HpEnem.equalsIgnoreCase(RangeHP.HIGH.toString()) )
		{
			System.out.println("equalsIgnoreCase");
		}*/

		if(HpEnem.equalsIgnoreCase(RangeHP.HIGH.toString()))
		{
			if(NumEenm.equalsIgnoreCase(RangeUnits.LARGE.toString()))
			{
				if(HpAlly.equalsIgnoreCase(RangeHP.HIGH.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly.equalsIgnoreCase(RangeHP.MEDIUM.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly.equalsIgnoreCase(RangeHP.LOW.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}else if(NumEenm.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
			{
				if(HpAlly.equalsIgnoreCase(RangeHP.HIGH.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly.equalsIgnoreCase(RangeHP.MEDIUM.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly.equalsIgnoreCase(RangeHP.LOW.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}else if(NumEenm.equalsIgnoreCase(RangeUnits.SMALL.toString()))
			{
				if(HpAlly.equalsIgnoreCase(RangeHP.HIGH.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly.equalsIgnoreCase(RangeHP.MEDIUM.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly.equalsIgnoreCase(RangeHP.LOW.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}
			else if(NumEenm.equalsIgnoreCase(RangeUnits.ZERO.toString()))
			{
				if(HpAlly.equalsIgnoreCase(RangeHP.HIGH.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly.equalsIgnoreCase(RangeHP.MEDIUM.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly.equalsIgnoreCase(RangeHP.LOW.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly.equalsIgnoreCase(RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.HIGH;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}
			// ######################
		}else if(HpEnem.equalsIgnoreCase(RangeHP.MEDIUM.toString()))
		{
			if(NumEenm.equalsIgnoreCase(RangeUnits.LARGE.toString()))
			{
				if(HpAlly.equalsIgnoreCase(RangeHP.HIGH.toString()))
				{
					if(NumAly.equalsIgnoreCase(RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.MEDIUM.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
					
				}if(HpAlly .equalsIgnoreCase( RangeHP.LOW.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}else if(NumEenm .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
			{
				if(HpAlly .equalsIgnoreCase( RangeHP.HIGH.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.MEDIUM.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.LOW.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}else if(NumEenm .equalsIgnoreCase( RangeUnits.SMALL.toString()))
			{
				if(HpAlly .equalsIgnoreCase( RangeHP.HIGH.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.MEDIUM.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
					
				}if(HpAlly .equalsIgnoreCase( RangeHP.LOW.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}
			else if(NumEenm .equalsIgnoreCase( RangeUnits.ZERO.toString()))
			{
				if(HpAlly .equalsIgnoreCase( RangeHP.HIGH.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.MEDIUM.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
					
				}if(HpAlly .equalsIgnoreCase( RangeHP.LOW.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.MEDIUM;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}
			// ######################
		}else if(HpEnem .equalsIgnoreCase( RangeHP.LOW.toString()))
		{
			if(NumEenm .equalsIgnoreCase( RangeUnits.LARGE.toString()))
			{
				if(HpAlly .equalsIgnoreCase( RangeHP.HIGH.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.MEDIUM.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
					
				}if(HpAlly .equalsIgnoreCase( RangeHP.LOW.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.LARGE;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}else if(NumEenm .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
			{
				if(HpAlly .equalsIgnoreCase( RangeHP.HIGH.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.MEDIUM.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
					
				}if(HpAlly .equalsIgnoreCase( RangeHP.LOW.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.MEDIUM;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}else if(NumEenm .equalsIgnoreCase( RangeUnits.SMALL.toString()))
			{
				if(HpAlly .equalsIgnoreCase( RangeHP.HIGH.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.MEDIUM.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.LOW.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.SMALL;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}
			else if(NumEenm .equalsIgnoreCase( RangeUnits.ZERO.toString()))
			{
				if(HpAlly .equalsIgnoreCase( RangeHP.HIGH.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.HIGH;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.MEDIUM.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.MEDIUM;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}if(HpAlly .equalsIgnoreCase( RangeHP.LOW.toString()))
				{
					if(NumAly .equalsIgnoreCase( RangeUnits.LARGE.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.LARGE;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.MEDIUM.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.MEDIUM;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.SMALL.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.SMALL;
					}else if(NumAly .equalsIgnoreCase( RangeUnits.ZERO.toString()))
					{
						this.mediumHpFromNearbyEnemies = RangeHP.LOW;
						this.numberOfEnemiesUnitsNearby = RangeUnits.ZERO;
						this.mediumHpFromNearbyAllies = RangeHP.LOW;
						this.numberOfAlliesUnitsNearby = RangeUnits.ZERO;
					}
				}
				// ****
			}
			// ######################
		}
		/*System.out.println(this.mediumHpFromNearbyEnemies );
		System.out.println(this.numberOfEnemiesUnitsNearby);
		System.out.println(this.mediumHpFromNearbyAllies);
		System.out.println(this.numberOfAlliesUnitsNearby);
		System.out.println("##################################################################");*/
	}
	

}

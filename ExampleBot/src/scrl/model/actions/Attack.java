package scrl.model.actions;

import java.util.ArrayList;
import java.util.Objects;

import bwapi.Game;
import bwapi.Unit;
import scrl.model.range.RangeDistance;

public class Attack extends Action implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public void execute(Game game, Unit unit) {
		int aux2;
		int closest = 99999;
		int lowestLifeClose = 99999;
		int lowestLifeMedium = 99999;
		int lowestLifeDar = 99999;
		int lowestUnitIdClose = 0;
		int lowestUnitIdMedium = 0;
		int lowestUnitIdFar = 0;
		java.util.List<Unit> closeList = new ArrayList<Unit>();
		java.util.List<Unit> mediumList = new ArrayList<Unit>();
		java.util.List<Unit> farList = new ArrayList<Unit>();
		
		for (Unit enemyUnit : unit.getUnitsInRadius(3*RangeDistance.MARINE_ATTACK_RANGE)) {
			aux2 = enemyUnit.getHitPoints();
			
			if(unit.getDistance(enemyUnit) <= 2*RangeDistance.MARINE_ATTACK_RANGE){
				if (aux2 < lowestLifeMedium) {
					lowestLifeMedium = aux2;
					lowestUnitIdMedium = enemyUnit.getID();}
				mediumList.add(enemyUnit);
			}else if(unit.getDistance(enemyUnit) <=RangeDistance.MARINE_ATTACK_RANGE){
				if (aux2 < lowestLifeClose) {
					lowestLifeClose = aux2;
					lowestUnitIdClose = enemyUnit.getID();}
				closeList.add(enemyUnit);
			}
			if (aux2 < lowestLifeDar) {
				lowestLifeDar = aux2;
				lowestUnitIdFar = enemyUnit.getID();}
			farList.add(enemyUnit);
		}
		
		if(!closeList.isEmpty())
		{
			for (Unit enemyUnit : closeList) {
				if (lowestUnitIdClose == enemyUnit.getID()) {
					unit.attack(enemyUnit.getPosition());
					break;
				}
			}
			
		}else if(!mediumList.isEmpty())
		{
			for (Unit enemyUnit : closeList) {
				if (lowestUnitIdMedium == enemyUnit.getID()) {
					unit.attack(enemyUnit.getPosition());
					break;
				}
			}
			
		}else if(!farList.isEmpty())
		{
			for (Unit enemyUnit : closeList) {
				if (lowestUnitIdFar == enemyUnit.getID()) {
					unit.attack(enemyUnit.getPosition());
					break;
				}
			}
		}
	}
	@Override
	public boolean equals(Object other) {
		if(other == this) return true;
		if(!(other instanceof Attack)) return false;
		return 
				Objects.equals(this.getClass().getSimpleName(), other.getClass().getSimpleName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getClass().getSimpleName());
	}
}

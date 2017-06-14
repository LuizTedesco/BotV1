package scrl.model.actions;

import java.util.Objects;

import bwapi.Game;
import bwapi.Unit;

public class Attack extends Action implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public void execute(Game game, Unit unit) {
		int closest = 99999;
		int lowestLife = 99999;
		int lowestUnitId = 0;

		for (Unit enemyUnit : game.enemy().getUnits()) {
			int aux = unit.getDistance(enemyUnit);
			if (aux < closest) {
				closest = aux;
			}
			int aux2 = enemyUnit.getHitPoints();
			if (aux2 < lowestLife) {
				lowestLife = aux2;
				lowestUnitId = enemyUnit.getID();
			}
		}
		for (Unit enemyUnit : game.enemy().getUnits()) {
			if (lowestUnitId == enemyUnit.getID()) {
				unit.attack(enemyUnit.getPosition());
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

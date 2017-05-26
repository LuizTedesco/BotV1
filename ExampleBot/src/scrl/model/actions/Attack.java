package scrl.model.actions;

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
}

package scrl.model.actions;

import java.util.Objects;
import java.util.Random;

import bwapi.Game;
import bwapi.Position;
import bwapi.Unit;
import scrl.model.range.RangeDistance;

public class Flee extends Action implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Random generator = new Random();

	public void execute(Game game, Unit unit) {
		Position safePlace = getSaferPlace(game, unit);
		if (safePlace.isValid() && unit.exists()) {
			unit.move(safePlace);
		}
	}

	private Position getSaferPlace(Game game, Unit unit) {
		int myUnitX = unit.getPosition().getX();
		int myUnitY = unit.getPosition().getY();
		int numberofEnemiesOnUpperRight = 0;
		int numberofEnemiesOnLowerRight = 0;
		int numberofEnemiesOnUpperLeft = 0;
		int numberofEnemiesOnLowerLeft = 0;
		int enemyX = 0;
		int enemyY = 0;
		double dist = 0.0;
		int numberOfEnemyUnits = game.enemy().getUnits().size();

		for (Unit enemyUnit : unit.getUnitsInRadius(3 * RangeDistance.MARINE_ATTACK_RANGE)) {
			enemyX = enemyUnit.getPosition().getX();
			enemyY = enemyUnit.getPosition().getY();
			dist += unit.getDistance(enemyUnit);
			if (enemyX > myUnitX) {
				if (enemyY > myUnitY) {
					numberofEnemiesOnUpperRight++;
				} else {
					numberofEnemiesOnLowerRight++;
				}
			} else {
				if (enemyY > myUnitY) {
					numberofEnemiesOnUpperLeft++;
				} else {
					numberofEnemiesOnLowerLeft++;
				}
			}
		}

		int low = -50;
		int high = 50;
		int aux1 = generator.nextInt(high - low) + low;
		int aux2 = generator.nextInt(high - low) + low;

		Position safePlace = new bwapi.Position(myUnitX + aux1, myUnitY + aux2);
		if (numberofEnemiesOnUpperRight > numberofEnemiesOnUpperLeft
				|| numberofEnemiesOnLowerRight > numberofEnemiesOnLowerLeft) {
			// esquerda
			if (numberofEnemiesOnLowerLeft > numberofEnemiesOnUpperLeft
					|| numberofEnemiesOnLowerRight > numberofEnemiesOnUpperRight) {
				// cima
				safePlace = new bwapi.Position(myUnitX - (int) (dist / numberOfEnemyUnits),
						myUnitY + (int) (dist / numberOfEnemyUnits));
			} else if (numberofEnemiesOnUpperLeft > numberofEnemiesOnLowerLeft
					|| numberofEnemiesOnUpperRight > numberofEnemiesOnLowerRight) {
				// baixo
				safePlace = new bwapi.Position(myUnitX - (int) (dist / numberOfEnemyUnits),
						myUnitY - (int) (dist / numberOfEnemyUnits));
			}
		} else if (numberofEnemiesOnUpperLeft > numberofEnemiesOnUpperRight
				|| numberofEnemiesOnLowerLeft > numberofEnemiesOnLowerRight) {
			// direita
			if (numberofEnemiesOnLowerLeft > numberofEnemiesOnUpperLeft
					|| numberofEnemiesOnLowerRight > numberofEnemiesOnUpperRight) {
				// cima
				safePlace = new bwapi.Position(myUnitX + (int) (dist / numberOfEnemyUnits),
						myUnitY + (int) (dist / numberOfEnemyUnits));
			} else if (numberofEnemiesOnUpperLeft > numberofEnemiesOnLowerLeft
					|| numberofEnemiesOnUpperRight > numberofEnemiesOnLowerRight) {
				// baixo
				safePlace = new bwapi.Position(myUnitX + (int) (dist / numberOfEnemyUnits),
						myUnitY - (int) (dist / numberOfEnemyUnits));
			}
		}
		return safePlace;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (!(other instanceof Flee))
			return false;
		return Objects.equals(this.getClass().getSimpleName(), other.getClass().getSimpleName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getClass().getSimpleName());
	}
}

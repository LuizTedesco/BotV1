package scrl.model.actions;

import java.util.Random;

import bwapi.Game;
import bwapi.Position;
import bwapi.Unit;

public class Flee extends Action implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Random generator = new Random();
	private static final int SAFER_PLACE_RADIUS = 320;

	public void execute(Game game, Unit unit) {
		Position safePlace = getSaferPlace(game, unit);
		if (safePlace.isValid() && unit.exists()) {
			unit.move(safePlace);
		} else {
			System.out.println("SafePlace Is Not Valid, DO NOTHING");
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

		for (Unit enemyUnit : unit.getUnitsInRadius(SAFER_PLACE_RADIUS)) {
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
			System.out.println("Esquerda");
			// esquerda
			if (numberofEnemiesOnLowerLeft > numberofEnemiesOnUpperLeft
					|| numberofEnemiesOnLowerRight > numberofEnemiesOnUpperRight) {
				// cima
				System.out.println("Cima");
				safePlace = new bwapi.Position(myUnitX - (int) (dist / numberOfEnemyUnits),
						myUnitY + (int) (dist / numberOfEnemyUnits));
			} else if (numberofEnemiesOnUpperLeft > numberofEnemiesOnLowerLeft
					|| numberofEnemiesOnUpperRight > numberofEnemiesOnLowerRight) {
				// baixo
				System.out.println("Baixo");
				safePlace = new bwapi.Position(myUnitX - (int) (dist / numberOfEnemyUnits),
						myUnitY - (int) (dist / numberOfEnemyUnits));
			}
		} else if (numberofEnemiesOnUpperLeft > numberofEnemiesOnUpperRight
				|| numberofEnemiesOnLowerLeft > numberofEnemiesOnLowerRight) {
			System.out.println("Direita");
			// direita
			if (numberofEnemiesOnLowerLeft > numberofEnemiesOnUpperLeft
					|| numberofEnemiesOnLowerRight > numberofEnemiesOnUpperRight) {
				// cima
				System.out.println("Cima");
				safePlace = new bwapi.Position(myUnitX + (int) (dist / numberOfEnemyUnits),
						myUnitY + (int) (dist / numberOfEnemyUnits));
			} else if (numberofEnemiesOnUpperLeft > numberofEnemiesOnLowerLeft
					|| numberofEnemiesOnUpperRight > numberofEnemiesOnLowerRight) {
				// baixo
				System.out.println("Baixo");
				safePlace = new bwapi.Position(myUnitX + (int) (dist / numberOfEnemyUnits),
						myUnitY - (int) (dist / numberOfEnemyUnits));
			}
		}
		// System.out.println("return do getSaferPlace");
		return safePlace;
	}
}

package scrl.model.actions;

import java.util.Objects;
import java.util.Random;

import bwapi.Color;
import bwapi.Game;
import bwapi.Position;
import bwapi.Unit;
import scrl.model.range.RangeDistance;

public class Explore extends Action implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Random generator = new Random();

	@Override
	public void execute(Game game, Unit unit) {
		Position exploreLocation;
		do {
			int low = -2 * RangeDistance.MARINE_ATTACK_RANGE;
			int high = 2 * RangeDistance.MARINE_ATTACK_RANGE;
			int aux1 = generator.nextInt(high - low) + low;
			int aux2 = generator.nextInt(high - low) + low;
			game.drawCircleMap(unit.getPosition().getX() + (aux1), unit.getPosition().getY() + (aux2), 15, Color.Blue);
			game.drawTextMap(unit.getPosition().getX(), unit.getPosition().getY(), "EXPLORE");

			exploreLocation = new bwapi.Position(unit.getPosition().getX() + (aux1),
					unit.getPosition().getY() + (aux2));
			if (exploreLocation.isValid()) {
				unit.move(exploreLocation, false);
			}
		} while (!exploreLocation.isValid());
	}

	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (!(other instanceof Explore))
			return false;
		return Objects.equals(this.getClass().getSimpleName(), other.getClass().getSimpleName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getClass().getSimpleName());
	}
}

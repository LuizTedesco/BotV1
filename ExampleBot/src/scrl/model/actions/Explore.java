package scrl.model.actions;

import java.util.List;
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
		int low;
		int high;
		int aux1;
		int aux2;
		boolean flag = true;
		for(int cont = 0; cont<11 && flag; cont++)
		{
			low = -2 * RangeDistance.MARINE_ATTACK_RANGE;
			high = 2 * RangeDistance.MARINE_ATTACK_RANGE;
			aux1 = generator.nextInt(high - low) + low;
			aux2 = generator.nextInt(high - low) + low;
			exploreLocation = new bwapi.Position(unit.getPosition().getX() + (aux1),
					unit.getPosition().getY() + (aux2));
			if (exploreLocation.isValid() && willUnitsBeKeptClose(game, unit, exploreLocation)) {
				unit.move(exploreLocation, false);
				flag = false;
			}
		}
	}
	
	Boolean willUnitsBeKeptClose(Game game, Unit unit, Position exploreLocation){
		 List<Unit> myUnits = unit.getUnitsInRadius(2*RangeDistance.MARINE_ATTACK_RANGE);
		 game.drawCircleMap(unit.getPosition().getX(), unit.getPosition().getY(), 2*RangeDistance.MARINE_ATTACK_RANGE, Color.Blue);
		 for (Unit unit2 : myUnits) {
			if(unit2.getID()!=unit.getID())
			{
				if(unit2.getDistance(exploreLocation) > RangeDistance.MARINE_ATTACK_RANGE){return false;}
				else{return true;}
			}
		}
		return true; // no nearbyAlliedUnits
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

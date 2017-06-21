package scrl.model.actions;

import bwapi.Game;
import bwapi.Unit;

public abstract class Action {

	public abstract void execute(Game game, Unit unit);

	public abstract boolean equals(Object other);

	public abstract int hashCode();

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}

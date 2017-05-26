package scrl.model.actions;

import bwapi.Game;
import bwapi.Unit;

public abstract class Action {

	public abstract void execute(Game game, Unit unit);
}

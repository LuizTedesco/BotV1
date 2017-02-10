package scrl.model;

public class SecondaryDataStructure {
	public Actions choosenAction;
	public Double givenOrderFrame;
	public UnitState currentState;
	
	public SecondaryDataStructure(Actions actionToPerform, double frame, UnitState curState) {
		choosenAction = actionToPerform;
		givenOrderFrame = frame;
		currentState = curState;
	}
}

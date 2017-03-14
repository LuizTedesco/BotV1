package scrl.model;

public class SecondaryDataStructure {
	public Actions choosenAction;
	public Double givenOrderFrame;
	public UnitState currentState;
	public Boolean rewardedAction;
	
	public SecondaryDataStructure(Actions actionToPerform, double frame, UnitState curState) {
		choosenAction = actionToPerform;
		givenOrderFrame = frame;
		currentState = curState;
	}
	
	public SecondaryDataStructure(Actions actionToPerform, double frame, UnitState curState, Boolean bool) {
		choosenAction = actionToPerform;
		givenOrderFrame = frame;
		currentState = curState;
		rewardedAction = bool;
	}
}

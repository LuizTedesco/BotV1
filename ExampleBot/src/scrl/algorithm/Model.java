package scrl.algorithm;

import scrl.model.State;

public class Model {
	
	private State nextState;
	private Double reward;
	
	
	public Model(State nextState, Double reward) {
		super();
		this.nextState = nextState;
		this.reward = reward;
	}
	
	public State getNextState() {
		return nextState;
	}
	public void setNextState(State nextState) {
		this.nextState = nextState;
	}
	public Double getReward() {
		return reward;
	}
	public void setReward(Double reward) {
		this.reward = reward;
	}
	
	
		
}

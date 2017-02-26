package scrl.model;

public class PolicyDataStructure {
	public UnitState state;
	public Actions action;
	public PolicyDataStructure(UnitState state, Actions action) {
		super();
		this.state = state;
		this.action = action;
	}
	
	@Override
	  public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append(state).append(" = ").append(action).append("\n");
	    return builder.toString();
	  }
	
}

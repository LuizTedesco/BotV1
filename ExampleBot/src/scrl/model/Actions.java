package scrl.model;
public enum Actions {
	EXPLORE("explore", 0),FLEE("flee", 1),ATTACK("attack", 2);

	private final int value;
	private final String name;

	private Actions(final String newName, final int newValue) {
		value = newValue;
		name = newName;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

}

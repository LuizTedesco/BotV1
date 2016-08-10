package scrl.model;
public enum Actions {
	ATTACK("attack", 0), EXPLORE("explore", 1),FLEE("flee", 2);

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

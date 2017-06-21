package scrl.model.range;

public enum RangeHP implements Range {
	LOW(1, 10), MEDIUM_LOW(10, 20), MEDIUM_HIGH(20, 30), HIGH(30, 40);
	double min;
	double max;

	private RangeHP(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public static RangeHP get(double value) {
		for (RangeHP hp : values()) {
			if (hp.in(value))
				return hp;
		}
		return LOW;
	}

	public boolean isLower(RangeHP next) {
		return ordinal() < next.ordinal();
	}

	public boolean isHigher(RangeHP other) {
		return ordinal() > other.ordinal();
	}

	public boolean in(double v) {
		return min < v && v <= max;
	}

}

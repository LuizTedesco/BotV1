package scrl.model.range;

public enum RangeUnits implements Range {
	SMALL(1, 2), MEDIUM(3, 5), LARGE(6, 200);
	double min;
	double max;

	RangeUnits(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public static RangeUnits get(int value) {
		if (value <= 2)
			return SMALL;
		else if (value <= 5)
			return MEDIUM;
		else
			return LARGE;
	}

	public boolean isLower(RangeUnits next) {
		return ordinal() < next.ordinal();
	}

	public boolean isEqual(RangeUnits next) {
		return ordinal() == next.ordinal();
	}

	public boolean isHigher(RangeUnits other) {
		return ordinal() > other.ordinal();
	}

	public boolean in(double v) {
		return min <= v && v < max;
	}

}
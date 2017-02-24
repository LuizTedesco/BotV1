package scrl.model.range;

public enum RangeUnits implements Range {
	ZERO(0, 0, 0), SMALL(1, 1, 2), MEDIUM(2, 3, 5), LARGE(3, 6, 200);
	double min;
	double max;
	private int index;

	RangeUnits(int index, double min, double max) {
		this.index = index;
		this.min = min;
		this.max = max;
	}

	public static RangeUnits get(int value) {
		if (value == 0)
			return ZERO;
		else if (value <= 2)
			return SMALL;
		else if (value <= 5)
			return MEDIUM;
		else
			return LARGE;
	}

	public boolean isLower(RangeUnits next) {
		return index < next.index;
	}
	
	public boolean isEqual(RangeUnits next) {
		return index == next.index;
	}
	
	public boolean isHigher(RangeUnits other) {
		return index > other.index;
	}
	
	public boolean in(double v) {
		return min <= v && v < max;
	}
	
	
}
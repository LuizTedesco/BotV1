package scrl.model.range;

public enum RangeHP implements Range {
	LOW(0, 0, 33), MEDIUM_LOW(1, 34, 66), MEDIUM_HIGH(2, 67, 100);
	double min;
	double max;
	private int index;

	private RangeHP(int index, double min, double max) {
		this.index = index;
		this.min = min;
		this.max = max;
	}

	public static RangeHP get(double value) {
		if (value > 0 && value <= 25)
			return LOW;
		else if (value <= 50)
			return MEDIUM_LOW;
		else if (value <= 75)
			return MEDIUM_HIGH;
		return null;
	}

	public boolean isLower(RangeHP next) {
		return index < next.index;
	}

	public boolean in(double v) {
		return min <= v && v < max;
	}
}

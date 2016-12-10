package scrl.model.range;

public enum RangeHP implements Range {
	LOW(0, 0, 10), MEDIUM_LOW(1, 11, 26), MEDIUM_HIGH(2, 27, 40);
	double min;
	double max;
	private int index;

	private RangeHP(int index, double min, double max) {
		this.index = index;
		this.min = min;
		this.max = max;
	}

	public static RangeHP get(double value) {
		if (value <= 10)
			return LOW;
		else if (value <= 26)
			return MEDIUM_LOW;
		else
		//else if (value <= 100)
			return MEDIUM_HIGH;
		//return null;
	}

	public boolean isLower(RangeHP next) {
		return index < next.index;
	}

	public boolean in(double v) {
		return min <= v && v < max;
	}
}

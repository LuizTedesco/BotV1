package scrl.model.range;

public enum RangeUnits implements Range {
	ZERO(0, 0), SMALL(1, 2), MEDIUM(2, 3), LARGE(3, 50); // e o caso q se tenha
															// mais?
	double min;
	double max;

	RangeUnits(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public static RangeUnits get(int value) {
		if (value == 0)
			return ZERO;
		else if (value <= 2)
			return SMALL;
		else if (value <= 3)
			return MEDIUM;
		else if (value <= 50)
			return LARGE;
		return null;
	}

	public boolean in(double v) {
		return min <= v && v < max;
	}

	//@Override
	//	public String toString() {
	//return "[min=" + min + ", max=" + max + "]";
	//}
}
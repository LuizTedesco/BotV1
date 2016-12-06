package scrl.model.range;

public enum RangeUnits implements Range {
	ZERO(0, 0), SMALL(1, 1), MEDIUM(2, 4), LARGE(5, 200); // e o caso q se tenha
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
		else if (value <= 1)
			return SMALL;
		else if (value <= 4)
			return MEDIUM;
		else
		//else if (value <= 200)
			return LARGE;
		//return null;
	}

	public boolean in(double v) {
		return min <= v && v < max;
	}

	//@Override
	//	public String toString() {
	//return "[min=" + min + ", max=" + max + "]";
	//}
}
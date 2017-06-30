package scrl.model.range;

public enum RangeHP  {
	LOW(0, 100), MEDIUM_LOW(100, 200), MEDIUM_HIGH(200, 300), HIGH(300, 400);
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

	public boolean in(double v) {
		return v > min && v <= max;
	}

}

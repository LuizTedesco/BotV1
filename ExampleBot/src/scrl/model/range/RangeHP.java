package scrl.model.range;

import scrl.tests.TestBotSC1;

public enum RangeHP implements Range {
//	LOW(0, 0, 10), MEDIUM_LOW(1, 11, 26), MEDIUM_HIGH(2, 27, 40);
//	LOW(0, 0, 10), MEDIUM_LOW(1, 11, 20), MEDIUM_HIGH(2, 21, 30), HIGH(3, 31, 40);
	LOW(0, 0, 8), MEDIUM_LOW(1, 9, 16), MEDIUM_HIGH(2, 17, 24), HIGH(3, 25, 32), FULL(4, 33, 40);
	double min;
	double max;
	private int index;

	private RangeHP(int index, double min, double max) {
		this.index = index;
		this.min = min;
		this.max = max;
	}

	public static RangeHP get(double value) {
		if (value <= 8)
			return LOW;
		else if (value <= 16)
			return MEDIUM_LOW;
		else if (value <= 24)
			return MEDIUM_HIGH;
		else if (value <= 32)
			return HIGH;
		else
			return FULL;
	}

	public boolean isLower(RangeHP next) {
		TestBotSC1.log("isLowerThan: ");
		TestBotSC1.log("index: " +index);
		TestBotSC1.log("next.index: " + next.index);
		return index < next.index;
	}
	
	public boolean isEqual(RangeHP next) {
		TestBotSC1.log("isEqual: ");
		TestBotSC1.log("index: " +index);
		TestBotSC1.log("next.index: " + next.index);
		return index == next.index;
	}
	
	public boolean isHigher(RangeHP other) {
		TestBotSC1.log("isHigherThan: ");
		TestBotSC1.log("index: " +index);
		TestBotSC1.log("other.index: " + other.index);
		return index > other.index;
	}

	public boolean in(double v) {
		TestBotSC1.log("in: ");
		return min <= v && v < max;
	}

}

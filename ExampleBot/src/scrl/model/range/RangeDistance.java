package scrl.model.range;

public enum RangeDistance {
	CLOSE, MEDIUM, FAR;

	public static RangeDistance get(int distanceToClosestEnemyUnit) {
		if(distanceToClosestEnemyUnit <= 125)
			return RangeDistance.CLOSE;
		else if(distanceToClosestEnemyUnit <= 200)
			return RangeDistance.MEDIUM;
		else
			return RangeDistance.FAR;
	}
}

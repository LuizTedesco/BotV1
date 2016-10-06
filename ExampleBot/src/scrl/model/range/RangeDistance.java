package scrl.model.range;

public enum RangeDistance {
	FAR, MEDIUM, CLOSE,  ;

	public static RangeDistance get(int distanceToClosestEnemyUnit) {
		if(distanceToClosestEnemyUnit <= 15)
			return RangeDistance.CLOSE;
		else if(distanceToClosestEnemyUnit <= 30)
			return RangeDistance.MEDIUM;
		else
			return RangeDistance.FAR;
	}
}

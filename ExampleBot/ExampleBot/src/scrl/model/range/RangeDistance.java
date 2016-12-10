package scrl.model.range;

public enum RangeDistance {
	FAR, MEDIUM, CLOSE,  ;

	public static RangeDistance get(int distanceToClosestEnemyUnit) {
		if(distanceToClosestEnemyUnit <= 20)
			return RangeDistance.CLOSE;
		else if(distanceToClosestEnemyUnit <= 40)
			return RangeDistance.MEDIUM;
		else
			return RangeDistance.FAR;
	}
}

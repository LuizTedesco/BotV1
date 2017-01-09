package scrl.model.range;

public enum RangeDistance {
	TOOCLOSE, CLOSE , NOTSOCLOSE, MEDIUM, FAR;

	public static RangeDistance get(int distanceToClosestEnemyUnit) {
		if(distanceToClosestEnemyUnit <= 50)
			return RangeDistance.TOOCLOSE;
		else if(distanceToClosestEnemyUnit <= 100)
			return RangeDistance.CLOSE;
		else if(distanceToClosestEnemyUnit <= 150)
			return RangeDistance.NOTSOCLOSE;
		else if(distanceToClosestEnemyUnit <= 200)
			return RangeDistance.MEDIUM;
		else
			return RangeDistance.FAR;
	}
}

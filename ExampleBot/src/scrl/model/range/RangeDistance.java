package scrl.model.range;

public enum RangeDistance {
	CLOSE, MEDIUM, FAR;
	public static final int MARINE_ATTACK_RANGE = 128;

	// http://wiki.teamliquid.net/starcraft/Distance#Range
	// 128 (32*4)
	public static RangeDistance get(int distanceToClosestEnemyUnit) {
		if (distanceToClosestEnemyUnit <= MARINE_ATTACK_RANGE)
			return CLOSE;
		else if (distanceToClosestEnemyUnit <= 2 * MARINE_ATTACK_RANGE)
			return MEDIUM;
		else
			return FAR;
	}
}

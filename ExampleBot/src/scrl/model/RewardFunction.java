package scrl.model;

import scrl.model.range.RangeUnits;

public class RewardFunction {
	public static double getValue(final UnitState pState, UnitState next, final Actions pAction) {
		
		if (pAction == Actions.ATTACK) {
			if(
					// 1
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("100");
				return 100;
			}
			else if(
					// 2
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("95");
				return 95;
			}
			else if(
					// 3
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("90");
				return 90;
			}
			else if(
					// 4
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("80");
				return 80;
			}
			else if(
					// 5
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("75");
				return 75;
			}
			else if(
					// 6
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("70");
				return 70;
			}
			else if(
					// 7
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("-100");
				return -100;
			}
			else if(
					// 8
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("-95");
				return -95;
			}
			else if(
					// 9
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("-90");
				return -90;
			}
			else if(
					// 10
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("-85");
				return -85;
			}
			else if(
					// 11
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("-80");
				return -80;
			}
			else if(
					// 12
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("-70");
				return -70;
			}
			else return 0;
		} else if (pAction == Actions.EXPLORE){
			if(
					// 1
					pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO){
				System.out.println("100");
				return 100;
			} // mais casos?
			else {
				System.out.println("-30");
				return -30;
			}
		}
		else if (pAction == Actions.FLEE) {
			if(
					// 1
					pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("100");
				return 100;
			}
			else if(
					// 2
					pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("90");
				return 90;
			}
			else if(
					// 3
					pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("80");
				return 80;
			}
			else if(
					// 4
					pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("70");
				return 70;
			}
			else if( 
					// 5
					pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("-100");
				return -100;
			}
			else if( 
					// 6
					pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("-90");
				return -90;
			}
			else if(
					// 7
					pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("-80");
				return -80;
			}
			else{
				System.out.println("10");
				return 10;
			}
		}
		System.out.println("-0.1");
		return -0.1;
	}
}

package scrl.model;

import scrl.model.range.RangeUnits;
import scrl.tests.TestBotSC1;

public class RewardFunction {
	public static double getValue(final UnitState pState, UnitState next, final Actions pAction) {
		TestBotSC1.log("                            ");
		if (pAction == Actions.ATTACK) {
			if(
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: 100");
				return 100;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: 90");
				return 90;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: 80");
				return 80;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: 70");
				return 70;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: 60");
				return 60;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: 50");
				return 50;
			}
			else if(
					pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO					
					){
				TestBotSC1.log("Attack: -200");
				return -200;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: -100");
				return -100;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: -90");
				return -90;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: -80");
				return -80;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: -70");
				return -70;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: -60");
				return -60;
			}
			else if(
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
				TestBotSC1.log("Attack: -50");
				return -50;
			}
			else if(
					// 6.1 Punir ou recompensar?
					next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("6.1 Punir ou recompensar?");
				System.out.println("50");
				TestBotSC1.log("Attack: 50");
				return 50;
			}
			else 
			{
				TestBotSC1.log("Attack: -150");
				System.out.println("-150");
				return -150;
			}
		}
		else if (pAction == Actions.EXPLORE){
			if(pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO){
				TestBotSC1.log("Explore: 100");
				return 100;
			}
			else {
				TestBotSC1.log("Explore: -100");
				return -100;
			}
		}
		else if (pAction == Actions.FLEE) {
			TestBotSC1.log("Flee");
			if(
					pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					){
				TestBotSC1.log("Flee: 100");
				return 100;
			}
			else if(
					pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					){
				TestBotSC1.log("Flee: 90");
				return 90;
			}
			else if(
					pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					){
				TestBotSC1.log("Flee: 80");
				return 80;
			}
			else if(
					pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					){
				TestBotSC1.log("Flee: 70");	
				return 70;
			}
			else if(
					pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
					){
				TestBotSC1.log("Flee: -100");
				return -100;
			}
			else if(
					pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					){
				TestBotSC1.log("Flee: -90");
				return -90;
			}
			else if(
					pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
					){
				TestBotSC1.log("Flee: -80");
				return -80;
			}
			else if(
					pState.getHpFromNearbyAllies().isEqual(next.getHpFromNearbyAllies())
					&&
					pState.getNumberOfEnemiesUnitsNearby().isEqual(RangeUnits.SMALL)
					){
				TestBotSC1.log("Flee: -70");
				return -70;					
			}else if(
					pState.getNumberOfEnemiesUnitsNearby()==RangeUnits.ZERO)
				{
						TestBotSC1.log("Flee: -100");
						return -100;					
			}
			else{
				TestBotSC1.log("Flee: -200");
				return -200;
			}
		}
		else
		{
			TestBotSC1.log("NONE-1000");
			return -1000;
		}
	}
}
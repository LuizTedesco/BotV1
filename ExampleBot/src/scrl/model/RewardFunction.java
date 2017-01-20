package scrl.model;

import scrl.model.range.RangeUnits;
import scrl.tests.TestBotSC1;

public class RewardFunction {
	public static double getValue(final UnitState pState, UnitState next, final Actions pAction) {
		TestBotSC1.log("getValue RewardFunction");
		if (pAction == Actions.ATTACK) {
			TestBotSC1.log("Attack RW Function");
			if(
					next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					&&
					next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
					&&
					next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
				System.out.println("1.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());
				TestBotSC1.log("1.1 Attack, RW: 100");

				System.out.println("100");
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
				System.out.println("2.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());
				TestBotSC1.log("2.1 Attack, RW: 90");
				System.out.println("90");
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
				System.out.println("3.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("3.1 Attack, RW: 80");
				System.out.println("80");
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
				System.out.println("4.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("4.1 Attack, RW: 70");
				System.out.println("70");
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
				System.out.println("5.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("5.1 Attack, RW: 60");
				System.out.println("60");
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
				System.out.println("6.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("6.1 Attack, RW: 50");
				System.out.println("50");
				return 50;
			}
			else if(
					pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO					
					){
				System.out.println("7.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("7.1 Attack, RW: -200");
				System.out.println("-200");
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
				System.out.println("8.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("8.1 Attack, RW: -100");
				System.out.println("-100");
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
				System.out.println("9.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("9.1 Attack, RW: -90");
				System.out.println("-90");
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
				System.out.println("10.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("10.1 Attack, RW: -80");
				System.out.println("-80");
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
				System.out.println("11.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("11.1 Attack, RW: -70");
				System.out.println("-70");
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
				System.out.println("12.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("12.1 Attack, RW: -60");
				System.out.println("-60");
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
				System.out.println("13.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("13.1 Attack, RW: -50");
				System.out.println("-50");
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
				System.out.println("14");
				System.out.println("6.1 Punir ou recompensar?");
				TestBotSC1.log("Punir ou recompensar?");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("14.1 Attack, RW: 0");
				System.out.println("0");
				return 0;
			}
			else 
			{
				System.out.println("15");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());
				TestBotSC1.log("15.1 ELSE do Attack, RW: -150");
				System.out.println("-150");
				return -150;
			}
		}
		else if (pAction == Actions.EXPLORE){
			if(pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO){
				System.out.println("1.2");
				TestBotSC1.log("NumberOfEnemiesUnitsNearby: "+ pState.getNumberOfEnemiesUnitsNearby());
				TestBotSC1.log("1.2 Explore, RW: 100");
				System.out.println("100");
				return 100;
			}
			else {
				System.out.println("2.2");
				TestBotSC1.log("2.2 ELSE do Explore, RW: -100");
				System.out.println("-100");
				return -100;
			}
		}
		else if (pAction == Actions.FLEE) {
			if(
					pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("1.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());
				
				TestBotSC1.log("1.1 Flee, RW: 100");
				System.out.println("Flee 100");
				return 100;
			}
			else if(
					pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("2.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("2.1 Flee, RW: 90");
				System.out.println("Flee 90");
				return 90;
			}
			else if(
					pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("3.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("3.1 Flee, RW: 80");
				System.out.println("Flee 80");
				return 80;
			}
			else if(
					pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("4.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("4.1 Flee, RW: 70");
				System.out.println("Flee 70");
				return 70;
			}
			else if(
					pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("5.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				//					TestBotSC1.log("Novo Estado:");
				//					TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				//					TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				//					TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				//					TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("5.1 Flee, RW: -100");
				System.out.println("Flee -100");
				return -100;
			}
			else if(
					pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("6.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				//					TestBotSC1.log("Novo Estado:");
				//					TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				//					TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				//					TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				//					TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("6.1 Flee, RW: -90");
				System.out.println("Flee -90");
				return -90;
			}
			else if(
					pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
					&&
					pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
					){
				System.out.println("7.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				//					TestBotSC1.log("Novo Estado:");
				//					TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				//					TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				//					TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				//					TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				//					TestBotSC1.log("Flee, RW: -80");
				//					System.out.println("Flee -80");
				//					return -80;

				TestBotSC1.log("7.1 Flee, RW: -80");
				System.out.println("Flee -80");
				return -80;
			}
			else if(
					pState.getHpFromNearbyAllies().isEqual(next.getHpFromNearbyAllies())
					&&
					pState.getNumberOfEnemiesUnitsNearby().isEqual(RangeUnits.SMALL)
					){
				System.out.println("7.1.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("7.1.1 Flee, RW: -70");
				System.out.println("Flee -70");
				return -70;					
			}

			else{
				System.out.println("8.1");
				TestBotSC1.log("Estado Velho:");
				TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

				TestBotSC1.log("Novo Estado:");
				TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
				TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
				TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
				TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
				TestBotSC1.log("8.1 ELSE do Flee, RW: -200");
				System.out.println("Flee -200");
				return -200;
			}
		}
		else
		{
			System.out.println("ELSE GERAL ANO CAI AQUI POR FAVOR");
			TestBotSC1.log("Estado Velho:");
			TestBotSC1.log("Vida Aliados: "+ pState.getHpFromNearbyAllies().toString());
			TestBotSC1.log("Num Aliados: "+ pState.getNumberOfAlliesUnitsNearby().toString());
			TestBotSC1.log("Vida Inimigos: "+ pState.getMediumHpFromNearbyEnemies().toString());
			TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());

			TestBotSC1.log("Novo Estado:");
			TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
			TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
			TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
			TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
			TestBotSC1.log("1 ELSE GERAl, RW: -1000");
			System.out.println("Else -1000");
			return -1000;
		}
	}
}
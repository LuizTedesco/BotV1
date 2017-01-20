package scrl.model;

import scrl.model.range.RangeUnits;
import scrl.tests.TestBotSC1;

public class RewardFunction {
	public static double getValue(final UnitState pState, UnitState next, final Actions pAction) {
		TestBotSC1.log("getValue RewardFunction");
		if (pAction == Actions.ATTACK) {
			TestBotSC1.log("Attack RW Function");
			if(pState.getNumberOfAlliesUnitsNearby().isEqual(RangeUnits.SMALL))
			{
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
					TestBotSC1.log("2.1 Attack, RW: 95");
					System.out.println("95");
					return 95;
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
					TestBotSC1.log("3.1 Attack, RW: 90");
					System.out.println("90");
					return 90;
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
					TestBotSC1.log("4.1 Attack, RW: 80");
					System.out.println("80");
					return 80;
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
					TestBotSC1.log("5.1 Attack, RW: 75");
					System.out.println("75");
					return 75;
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
					TestBotSC1.log("6.1 Attack, RW: 70");
					System.out.println("70");
					return 70;
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
					TestBotSC1.log("14.1 Attack, RW: 50");
					System.out.println("50");
					return 50;
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
			else if(pState.getNumberOfAlliesUnitsNearby().isEqual(RangeUnits.MEDIUM))
			{
				if(
						next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("1.2");
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
					TestBotSC1.log("1.2 Attack, RW: 250");
					
					System.out.println("250");
					return 250;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("2.2");
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
					TestBotSC1.log("2.2 Attack, RW: 230");
					System.out.println("230");
					return 230;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("3.2");
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
					TestBotSC1.log("3.2 Attack, RW: 215");
					System.out.println("215");
					return 215;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("4.2");
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
					TestBotSC1.log("4.2 Attack, RW: 200");
					System.out.println("200");
					return 200;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("5.2");
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
					TestBotSC1.log("5.2 Attack, RW: 180");
					System.out.println("180");
					return 180;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("6.2");
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
					TestBotSC1.log("6.2 Attack, RW: 150");
					System.out.println("150");
					return 150;
				}
				else if(
						pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO					
						){
					System.out.println("7.2");
					TestBotSC1.log("Estado Velho:");
					TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());
					
					TestBotSC1.log("Novo Estado:");
					TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());
					
					TestBotSC1.log("7.2 Attack, RW: -300");
					System.out.println("-300");
					return -300;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("8.2");
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
					TestBotSC1.log("8.2 Attack, RW: -200");
					System.out.println("-200");
					return -200;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("9.2");
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
					TestBotSC1.log("9.2 Attack, RW: -180");
					System.out.println("-180");
					return -180;
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
					System.out.println("10.2");
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
					TestBotSC1.log("10.2 Attack, RW: -160");
					System.out.println("-160");
					return -160;
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
					System.out.println("11.2");
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
					TestBotSC1.log("11.2 Attack, RW: -140");
					System.out.println("-140");
					return -140;
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
					System.out.println("12.2");
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
					TestBotSC1.log("12.2 Attack, RW: -120");
					System.out.println("-120");
					return -120;
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
					System.out.println("13.2");
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
					TestBotSC1.log("13.2 Attack, RW: -100");
					System.out.println("-100");
					return -100;
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
					System.out.println("14.2");
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
					TestBotSC1.log("14.2 Attack, RW: 100");
					System.out.println("100");
					return 100;
				}
				else 
					{
						System.out.println("15.2");
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
						TestBotSC1.log("15.2 ELSE do Attack, RW: -400");
						System.out.println("-400");
						return -400;
					}
				
			}
			else if(pState.getNumberOfAlliesUnitsNearby().isEqual(RangeUnits.LARGE))
			{
				if(
						next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("1.3");
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
					TestBotSC1.log("1.3 Attack, RW: 500");
					
					System.out.println("500");
					return 500;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("2.3");
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
					TestBotSC1.log("2.3 Attack, RW: 470");
					System.out.println("470");
					return 470;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("3.3");
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
					TestBotSC1.log("3 Attack, RW: 450");
					System.out.println("450");
					return 450;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("4.3");
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
					TestBotSC1.log("4.3 Attack, RW: 430");
					System.out.println("430");
					return 430;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("5.3");
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
					TestBotSC1.log("5.3 Attack, RW: 415");
					System.out.println("415");
					return 415;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("6.3");
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
					TestBotSC1.log("6.3 Attack, RW: 400");
					System.out.println("400");
					return 400;
				}
				else if(
						pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO					
						){
					System.out.println("7.3");
					TestBotSC1.log("Estado Velho:");
					TestBotSC1.log("Num Inimigos: "+ pState.getNumberOfEnemiesUnitsNearby().toString());
					
					TestBotSC1.log("Novo Estado:");
					TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());
					
					TestBotSC1.log("7.3 Attack, RW: -400");
					System.out.println("-400");
					return -400;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isLower(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("8.3");
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
					TestBotSC1.log("8.3 Attack, RW: -300");
					System.out.println("-300");
					return -300;
				}
				else if(
						next.getMediumHpFromNearbyEnemies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						next.getNumberOfEnemiesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						&&
						next.getHpFromNearbyAllies().isEqual(pState.getHpFromNearbyAllies())
						&&
						next.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfAlliesUnitsNearby())){
					System.out.println("9.3");
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
					TestBotSC1.log("9.3 Attack, RW: -280");
					System.out.println("-280");
					return -280;
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
					System.out.println("10.3");
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
					TestBotSC1.log("10.3 Attack, RW: -250");
					System.out.println("-250");
					return -250;
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
					System.out.println("11.3");
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
					TestBotSC1.log("11.3 Attack, RW: -230");
					System.out.println("-230");
					return -230;
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
					System.out.println("12.3");
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
					TestBotSC1.log("12.3 Attack, RW: -200");
					System.out.println("-200");
					return -200;
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
					System.out.println("13.3");
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
					TestBotSC1.log("13.3 Attack, RW: -180");
					System.out.println("-180");
					return -180;
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
					System.out.println("14.3");
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
					TestBotSC1.log("14.3 Attack, RW: 150");
					System.out.println("150");
					return 150;
				}
				else 
					{
						System.out.println("15.3");
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
						TestBotSC1.log("15.3 ELSE do Attack, RW: -350");
						System.out.println("-350");
						return -350;
					}
			}
			
		} else if (pAction == Actions.EXPLORE){
			if(pState.getNumberOfAlliesUnitsNearby().isEqual(RangeUnits.SMALL))
			{
				if(pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO){
					System.out.println("1.1");
					TestBotSC1.log("NumberOfEnemiesUnitsNearby: "+ pState.getNumberOfEnemiesUnitsNearby());
					TestBotSC1.log("1.1 Explore, RW: 400");
					System.out.println("400");
					return 400;
				} // mais casos?
				else {
					System.out.println("2.1");
					TestBotSC1.log("2 ELSE do Explore, RW: -400");
					System.out.println("-400");
					return -400;
				}
			}
			else if(pState.getNumberOfAlliesUnitsNearby().isEqual(RangeUnits.MEDIUM))
			{
				if(pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO){
					System.out.println("1.2");
					TestBotSC1.log("NumberOfEnemiesUnitsNearby: "+ pState.getNumberOfEnemiesUnitsNearby());
					TestBotSC1.log("1.2 Explore, RW: 300");
					System.out.println("300");
					return 300;
				} // mais casos?
				else {
					System.out.println("2.2");
					TestBotSC1.log("2.2 ELSE do Explore, RW: -300");
					System.out.println("-300");
					return -300;
				}
			}
			else if(pState.getNumberOfAlliesUnitsNearby().isEqual(RangeUnits.LARGE))
			{
				if(pState.getNumberOfEnemiesUnitsNearby() == RangeUnits.ZERO){
					System.out.println("1.3");
					TestBotSC1.log("NumberOfEnemiesUnitsNearby: "+ pState.getNumberOfEnemiesUnitsNearby());
					TestBotSC1.log("1.3 Explore, RW: 100");
					System.out.println("100");
					return 100;
				} // mais casos?
				else {
					System.out.println("2.3");
					TestBotSC1.log("2.3 ELSE do Explore, RW: -100");
					System.out.println("-100");
					return -100;
				}
			}
		}
		
		else if (pAction == Actions.FLEE) {
			
			if(pState.getNumberOfAlliesUnitsNearby().isEqual(RangeUnits.SMALL))
			{
//				############################################3
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
					
//					TestBotSC1.log("Novo Estado:");
//					TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
//					TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
//					TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
//					TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
					TestBotSC1.log("1.1 Flee, RW: 500");
					System.out.println("Flee 500");
					return 500;
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
					
//					TestBotSC1.log("Novo Estado:");
//					TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
//					TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
//					TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
//					TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
					TestBotSC1.log("2.1 Flee, RW: 470");
					System.out.println("Flee 470");
					return 470;
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
					
//					TestBotSC1.log("Novo Estado:");
//					TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
//					TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
//					TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
//					TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
					TestBotSC1.log("3.1 Flee, RW: 450");
					System.out.println("Flee 450");
					return 450;
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
					
//					TestBotSC1.log("Novo Estado:");
//					TestBotSC1.log("Vida Aliados: "+ next.getHpFromNearbyAllies().toString());
//					TestBotSC1.log("Num Aliados: "+ next.getNumberOfAlliesUnitsNearby().toString());
//					TestBotSC1.log("Vida Inimigos: "+ next.getMediumHpFromNearbyEnemies().toString());
//					TestBotSC1.log("Num Inimigos: "+ next.getNumberOfEnemiesUnitsNearby().toString());				
					TestBotSC1.log("4.1 Flee, RW: 400");
					System.out.println("Flee 400");
					return 400;
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
					TestBotSC1.log("5.1 Flee, RW: -400");
					System.out.println("Flee -400");
					return -400;
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
					TestBotSC1.log("6.1 Flee, RW: -450");
					System.out.println("Flee -450");
					return -450;
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
					
					TestBotSC1.log("7.1 Flee, RW: -500");
					System.out.println("Flee -500");
					return -500;
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
					TestBotSC1.log("7.1.1 Flee, RW: -100");
					System.out.println("Flee -100");
					return -100;					
				}
//				what should i do here?
//				Estado Velho:
//					Vida Aliados: FULL
//					Num Aliados: SMALL
//					Vida Inimigos: FULL
//					Num Inimigos: SMALL
//					Novo Estado:
//					Vida Aliados: FULL
//					Num Aliados: SMALL
//					Vida Inimigos: FULL
//					Num Inimigos: SMALL
//					8 ELSE do Flee, RW: -150
//					estado novo: FULL SMALL FULL SMALL
					
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
					TestBotSC1.log("8.1 ELSE do Flee, RW: -600");
					System.out.println("Flee -600");
					return -600;
				}
//				##############################################3
				
			}
			else if(pState.getNumberOfAlliesUnitsNearby().isEqual(RangeUnits.MEDIUM))
			{
//				############################################3
				if(
						pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("1.2");
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
					TestBotSC1.log("1.2 Flee, RW: 300");
					System.out.println("Flee 300");
					return 300;
				}
				else if(
						pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("2.2");
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
					TestBotSC1.log("2.2 Flee, RW: 250");
					System.out.println("Flee 250");
					return 250;
				}
				else if(
						pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("3.2");
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
					TestBotSC1.log("3.2 Flee, RW: 300");
					System.out.println("Flee 300");
					return 300;
				}
				else if(
						pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("4.2");
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
					TestBotSC1.log("4.2 Flee, RW: 300");
					System.out.println("Flee 300");
					return 300;
				}
				else if(
						pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("5.2");
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
					TestBotSC1.log("5.2 Flee, RW: -600");
					System.out.println("Flee -600");
					return -600;
				}
				else if(
						pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("6.2");
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
					TestBotSC1.log("6.2 Flee, RW: -515");
					System.out.println("Flee -515");
					return -515;
				}
				else if(
						pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("7.2");
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
					
					TestBotSC1.log("7.2 Flee, RW: -600");
					System.out.println("Flee -600");
					return -600;
				}
				else if(
						pState.getHpFromNearbyAllies().isEqual(next.getHpFromNearbyAllies())
						&&
						pState.getNumberOfEnemiesUnitsNearby().isEqual(RangeUnits.SMALL)
						){
					System.out.println("7.2.1");
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
					TestBotSC1.log("7.2.1 Flee, RW: -300");
					System.out.println("Flee -300");
					return -300;					
				}
//				what should i do here?
//				Estado Velho:
//					Vida Aliados: FULL
//					Num Aliados: SMALL
//					Vida Inimigos: FULL
//					Num Inimigos: SMALL
//					Novo Estado:
//					Vida Aliados: FULL
//					Num Aliados: SMALL
//					Vida Inimigos: FULL
//					Num Inimigos: SMALL
//					8 ELSE do Flee, RW: -150
//					estado novo: FULL SMALL FULL SMALL
					
				else{
					System.out.println("8.2");
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
					TestBotSC1.log("8.2 ELSE do Flee, RW: -700");
					System.out.println("Flee -700");
					return -700;
				}
//				##############################################3
				
			}
			else if(pState.getNumberOfAlliesUnitsNearby().isEqual(RangeUnits.LARGE))
			{
//				############################################3
				if(
						pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("1.3");
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
					TestBotSC1.log("1.3 Flee, RW: 150");
					System.out.println("Flee 150");
					return 150;
				}
				else if(
						pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("2.3");
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
					TestBotSC1.log("2.3 Flee, RW: 120");
					System.out.println("Flee 120");
					return 120;
				}
				else if(
						pState.getHpFromNearbyAllies().isLower(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("3.3");
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
					TestBotSC1.log("3.3 Flee, RW: 150");
					System.out.println("Flee 150");
					return 150;
				}
				else if(
						pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isLower(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("4.3");
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
					TestBotSC1.log("4.3 Flee, RW: 300");
					System.out.println("Flee 300");
					return 300;
				}
				else if(
						pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("5.3");
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
					TestBotSC1.log("5.3 Flee, RW: -900");
					System.out.println("Flee -900");
					return -900;
				}
				else if(
						pState.getHpFromNearbyAllies().isHigher(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isEqual(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("6.3");
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
					TestBotSC1.log("6.3 Flee, RW: -600");
					System.out.println("Flee -600");
					return -600;
				}
				else if(
						pState.getHpFromNearbyAllies().isEqual(pState.getMediumHpFromNearbyEnemies())
						&&
						pState.getNumberOfAlliesUnitsNearby().isHigher(pState.getNumberOfEnemiesUnitsNearby())
						){
					System.out.println("7.3");
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
					
					TestBotSC1.log("7.3 Flee, RW: -650");
					System.out.println("Flee -650");
					return -650;
				}
				else if(
						pState.getHpFromNearbyAllies().isEqual(next.getHpFromNearbyAllies())
						&&
						pState.getNumberOfEnemiesUnitsNearby().isEqual(RangeUnits.SMALL)
						){
					System.out.println("7.3.1");
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
					TestBotSC1.log("7.3.1 Flee, RW: -400");
					System.out.println("Flee -400");
					return -400;					
				}
//				what should i do here?
//				Estado Velho:
//					Vida Aliados: FULL
//					Num Aliados: SMALL
//					Vida Inimigos: FULL
//					Num Inimigos: SMALL
//					Novo Estado:
//					Vida Aliados: FULL
//					Num Aliados: SMALL
//					Vida Inimigos: FULL
//					Num Inimigos: SMALL
//					8 ELSE do Flee, RW: -150
//					estado novo: FULL SMALL FULL SMALL
					
				else{
					System.out.println("8.3");
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
					TestBotSC1.log("8.3 ELSE do Flee, RW: -999");
					System.out.println("Flee -999");
					return -999;
				}
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
		return -5000;
	}
}
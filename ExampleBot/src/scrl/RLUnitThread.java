package scrl;

import java.util.List;

import bwapi.Color;
import bwapi.Game;
import bwapi.Player;
import bwapi.Unit;
import scrl.model.Actions;
import scrl.model.UnitState;
import scrl.tests.TestBotSC1;

public class RLUnitThread implements Runnable {

	private Game game;
	private Unit me;
	private SCRL rl;
	private TestBotSC1 bot;
	private Player self;
	private Player enemy;

	public RLUnitThread(Game game, SCRL rl, Unit me, Player self, TestBotSC1 bot, Player enemy) {
		this.me = me;
		this.self = self;
		this.rl = rl;
		this.bot = bot;
		this.setGame(game);
		this.setEnemy(enemy);
	}

	@Override
	public void run() {
		String lastAction = me.getLastCommand().getUnitCommandType().toString();
		UnitState curState = getCurrentState();

		Actions actionToPerform = rl.getNextAction(curState);
		TestBotSC1.log(Thread.currentThread().getId()+ " RLUnitThread "+ actionToPerform.toString());
		
		if(lastAction == "Move" || lastAction == "Attack_Move" || lastAction == "Attack_Unit"){
			bot.executeActionStop(me);
//			me.stop(false);
			System.out.println("if do run");
			bot.executeAction(actionToPerform, me);
			UnitState newState = getCurrentState();
			rl.updateState(actionToPerform, curState,newState);
			TestBotSC1.log("Thread Id: "+Thread.currentThread().getId()+" Finished the Run");
			bot.avaiableUnitsList.add(me);
			System.out.println("UnitId: "+ me.getID()+"Foi RE---adicionado a avaiableUnitsList. EM IF");
			System.out.println("avaiableUnitsList.size() After Re-ADD: "+bot.avaiableUnitsList.size());
		}else{
			System.out.println("else do Run");
			bot.executeAction(actionToPerform, me);
			UnitState newState = getCurrentState();
			rl.updateState(actionToPerform, curState,newState);
			TestBotSC1.log("Thread Id: "+Thread.currentThread().getId()+" Finished the Run");
			bot.avaiableUnitsList.add(me);
			System.out.println("UnitId: "+ me.getID()+"  Foi RE---adicionado a avaiableUnitsList. EM ELSE");
			System.out.println("avaiableUnitsList.size() After Re-ADD: "+bot.avaiableUnitsList.size());
		}
			
//		unit.isStartingAttack()
//						unit.isAttackFrame()
//						unit.isAttacking()
		
//		if(game.getFrameCount() - me.getLastCommandFrame() >= 5 || me.isAttackFrame() ){
//		System.out.println("me.getLastCommand().toString()");
		// None, Move, Attack_Move ou Attack_Unit
//		System.out.println(me.getLastCommand().getUnitCommandType().toString());
		
		
		//{
			//System.out.println("no if");
			// dont act, therefore, dont update Q
		//}else
		//{
			//System.out.println("no Else");
//			bot.executeAction(actionToPerform, me);
//			UnitState newState = getCurrentState();
//				
//			rl.updateState(actionToPerform, curState,newState);
//			TestBotSC1.log("Thread Id: "+Thread.currentThread().getId()+" Finished the Run");
	}
	
	private UnitState getCurrentState() {
		double contHpEnemyLife = 0.d;
		double contHpAlliesLife = 0.d;
		double mediumHpFromNearbyEnemies = 0.d;
		int numberOfEnemiesUnitsNearby = 0;
		double mediumHpFromNearbyAllies = 0.d;
		int numberOfAlliesUnitsNearby = 0;
		int distanceToClosestEnemyUnit = 400000;	
		
		List<Unit> units = me.getUnitsInRadius(270);
		game.drawCircleMap(me.getPosition().getX(),me.getPosition().getY(),270,Color.Green);
		for (Unit unit : units) {
			
			if(unit.getPlayer().isAlly(self))
			{
				contHpAlliesLife += unit.getHitPoints();
				numberOfAlliesUnitsNearby++;
			}else if(unit.getPlayer().isEnemy(self))
			{
				contHpEnemyLife += unit.getHitPoints();
				numberOfEnemiesUnitsNearby++;
				distanceToClosestEnemyUnit = me.getDistance(unit) < distanceToClosestEnemyUnit ? me.getDistance(unit) : distanceToClosestEnemyUnit;
			}else
			{
				System.out.println("Cai no else em alguma vez?");
			}
		}
		
		if(numberOfEnemiesUnitsNearby != 0)
			mediumHpFromNearbyEnemies = contHpEnemyLife / numberOfEnemiesUnitsNearby;
		if(numberOfAlliesUnitsNearby != 0)
			mediumHpFromNearbyAllies = contHpAlliesLife / numberOfAlliesUnitsNearby;
		
//		UnitState curState = new UnitState(me.getHitPoints(), mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, 
//											mediumHpFromNearbyAllies, numberOfAlliesUnitsNearby, distanceToClosestEnemyUnit);
		UnitState curState = new UnitState(mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, 
				mediumHpFromNearbyAllies, numberOfAlliesUnitsNearby);
		
		return curState;
	}

	public Player getEnemy() {
		return enemy;
	}

	public void setEnemy(Player enemy) {
		this.enemy = enemy;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
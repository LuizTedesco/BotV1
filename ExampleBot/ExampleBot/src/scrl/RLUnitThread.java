package scrl;

import java.io.IOException;
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

		//System.out.println("Thread Id: "+Thread.currentThread().getId()+" RUN");
		UnitState curState = getCurrentState();

		Actions actionToPerform = rl.getNextAction(curState);
		try {
			TestBotSC1.log(Thread.currentThread().getId()+ " RLUnitThread "+ actionToPerform.toString());
			
			
			bot.executeAction(actionToPerform, me);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		UnitState newState = getCurrentState();
			
	
		rl.updateState(actionToPerform, curState,newState);
		try {
			TestBotSC1.log("Thread Id: "+Thread.currentThread().getId()+" Finished the Run");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private UnitState getCurrentState() {
		//try {
		//	TestBotSC1.log(Thread.currentThread().getId() +" getCurrentState da thread ");
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		double contHpEnemyLife = 0.d;
		double contHpAlliesLife = 0.d;
		double mediumHpFromNearbyEnemies = 0.d;
		int numberOfEnemiesUnitsNearby = 0;
		double mediumHpFromNearbyAllies = 0.d;
		int numberOfAlliesUnitsNearby = 0;
		int distanceToClosestEnemyUnit = 400000;	
		
		List<Unit> units = me.getUnitsInRadius(230);
		game.drawCircleMap(me.getPosition().getX(),me.getPosition().getY(),200,Color.Green);
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
		
		UnitState curState = new UnitState(me.getHitPoints(), mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, 
											mediumHpFromNearbyAllies, numberOfAlliesUnitsNearby, distanceToClosestEnemyUnit);
		
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
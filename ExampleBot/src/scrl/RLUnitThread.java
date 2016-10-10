package scrl;

import java.io.IOException;
import java.util.List;

import bwapi.Game;
import bwapi.Player;
import bwapi.Unit;
import scrl.model.Actions;
import scrl.model.UnitState;
import scrl.tests.TestBotSC1;


public class RLUnitThread extends Thread {

	private Game game;
	private Unit me;
	private SCRL rl;
	private TestBotSC1 bot;
	private Player self;
	private Player enemy;

	public RLUnitThread(Game game, SCRL rl, Unit me, Player self, TestBotSC1 bot, Player enemy) {
	//public RLUnitThread(SCRL rl, Unit me, Player self, TestBotSC1 bot, Player enemy) {
		this.me = me;
		this.self = self;
		this.rl = rl;
		this.bot = bot;
		this.setGame(game);
		this.setEnemy(enemy);
	}

	@Override
	public void run() {
		//if(game.getFrameCount()%24 == 0)
		//{
			//System.out.println(Thread.currentThread().getId()+"  RUN U FOLL");
			UnitState curState = getCurrentState();
			
			//System.out.println(curState);
			Actions actionToPerform = rl.getNextAction(curState);
			
			
			try {
				TestBotSC1.log(actionToPerform.toString());
				bot.executeAction(actionToPerform, me);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			UnitState newState = getCurrentState();
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			rl.updateState(actionToPerform, curState,newState);
		//}
	}

	private UnitState getCurrentState() {
		try {
			TestBotSC1.log(" getCurrentState da thread: "+ Thread.currentThread().getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double contHpEnemyLife = 0.d;
		double contHpAlliesLife = 0.d;
		double mediumHpFromNearbyEnemies = 0.d;
		int numberOfEnemiesUnitsNearby = 0;
		double hpFromNearbyAllies = 0.d;
		int numberOfAlliesUnitsNearby = 0;
		int distanceToClosestEnemyUnit = 1000;	
		
		List<Unit> units = me.getUnitsInRadius(50);
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
			}	
		}
		if(numberOfEnemiesUnitsNearby != 0)
			mediumHpFromNearbyEnemies = contHpEnemyLife / numberOfEnemiesUnitsNearby;
		if(numberOfAlliesUnitsNearby != 0)
			hpFromNearbyAllies = contHpAlliesLife / numberOfAlliesUnitsNearby;

		UnitState curState = new UnitState(me.getHitPoints(), mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, 
											hpFromNearbyAllies, numberOfAlliesUnitsNearby, distanceToClosestEnemyUnit);
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

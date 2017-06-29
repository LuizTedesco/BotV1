package scrl.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;
import bwapi.Unit;
import bwta.BWTA;
import scrl.model.State;
import scrl.model.StateAction;
import scrl.model.actions.Action;
import scrl.model.range.RangeDistance;
import scrl.rl.SCRL;
import scrl.utils.Log;

public class Main extends DefaultBWListener {

	public static final int MAX_GAMES = 50;

	public static int match = 0;

	private Mirror mirror = new Mirror();
	private Game game;
	private Player self;
	private SCRL rl;

	private Map<Unit, StateAction> units_running = new ConcurrentHashMap<>();
	private Map<String, Integer> counters = new HashMap<>();
	public static Map<State, Integer> statesCounter = new HashMap<>();

	public void run() {
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}

	@Override
	public void onStart() {
		game = mirror.getGame();
		self = game.self();

		BWTA.readMap();
		BWTA.analyze();

		game.setGUI(false);
		game.setLocalSpeed(0);

		rl = new SCRL();
		log("match N: " + match);

		rl.init(match);
	}

	@Override
	public void onFrame() {
		for (Unit unit : self.getUnits()) {
			if (isIdle(unit)) {
				// acabou de terminar de executar --> atualizar estado
				if (units_running.containsKey(unit)) {
					State newState = getCurrentState(unit);
					StateAction sd = units_running.remove(unit);
					rl.updateState(sd.action, sd.state, newState);

					log(unit, "finished: " + sd.action);
				}
				// buscar estado corrente e executar acao
				State curState = getCurrentState(unit);
				Integer auxCounter = 0;
				auxCounter = statesCounter.get(curState);

				if (auxCounter == null) {
					statesCounter.put(curState, 1);
				} else {
					statesCounter.put(curState, auxCounter + 1);
				}
				// log(statesCounter);

				Action actionToPerform = rl.getNextAction(curState);

				log(unit, "state: " + curState + " - frame: " + game.getFrameCount());
				executeAction(actionToPerform, unit); // AGIR

				units_running.put(unit, new StateAction(curState, actionToPerform));
			}
		}
	}

	private void log(Unit u, String msg) {
		log("[" + u.getID() + "] " + msg);
	}

	private void log(String msg) {
		Log.log(msg);
	}

	private boolean isIdle(Unit unit) {
		return !unit.isMoving() && !unit.isStartingAttack() && !unit.isAttacking();
	}

	@Override
	public void onEnd(boolean isWinner) {
		rl.end();
		match++;

		if (isWinner) {
			inc("winCounter");
		} else {
			inc("lossCounter");
		}

		if (match == MAX_GAMES) {
			System.out.println(rl.getLearning().getQTable().getPolicy().toString());

			if (Log.DEBUG) {
				for (String counterName : counters.keySet()) {
					log(counterName + ": " + counters.get(counterName));
				}
				Log.getInstance().endGame(statesCounter);
			}
			System.exit(0);
		}
	}

	private void inc(String counterName) {
		if (!counters.containsKey(counterName))
			counters.put(counterName, 0);
		counters.put(counterName, counters.get(counterName) + 1);
	}

	public void executeAction(Action actionToPerform, Unit me) {
		log("[" + me.getID() + "] started: " + actionToPerform);
		inc("actionCounter");
		inc(actionToPerform.getClass().getSimpleName() + "OrderCounter");
		actionToPerform.execute(game, me);
	}

	private State getCurrentState(Unit givenUnit) {
		Unit me = givenUnit;
		double contHpEnemyLife = 0.d;
		double contHpAlliesLife = 0.d;
		double mediumHpFromNearbyEnemies = 0.d;
		int numberOfEnemiesUnitsNearby = 0;
		double mediumHpFromNearbyAllies = 0.d;
		int numberOfAlliesUnitsNearby = 0;
		int distanceToClosestEnemyUnit = 400000;

		List<Unit> units = me.getUnitsInRadius(3 * RangeDistance.MARINE_ATTACK_RANGE);
		for (Unit unit : units) {
			if (unit.getPlayer().isAlly(self)) {
				contHpAlliesLife += unit.getHitPoints();
				numberOfAlliesUnitsNearby++;
			} else if (unit.getPlayer().isEnemy(self)) {
				contHpEnemyLife += unit.getHitPoints();
				numberOfEnemiesUnitsNearby++;
				distanceToClosestEnemyUnit = me.getDistance(unit) < distanceToClosestEnemyUnit ? me.getDistance(unit)
						: distanceToClosestEnemyUnit;
			}
		}

		contHpAlliesLife += me.getHitPoints();
		numberOfAlliesUnitsNearby++;

		if (numberOfEnemiesUnitsNearby != 0)
			mediumHpFromNearbyEnemies = contHpEnemyLife / numberOfEnemiesUnitsNearby;
		if (numberOfAlliesUnitsNearby != 0)
			mediumHpFromNearbyAllies = contHpAlliesLife / numberOfAlliesUnitsNearby;

		State curState = new State(mediumHpFromNearbyEnemies, numberOfEnemiesUnitsNearby, mediumHpFromNearbyAllies,
				numberOfAlliesUnitsNearby, distanceToClosestEnemyUnit);

		return curState;
	}

	public static void main(String[] args) {
		new Main().run();
	}
}
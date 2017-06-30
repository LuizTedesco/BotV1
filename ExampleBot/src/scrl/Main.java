package scrl;

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

//javadocs
//vjurenka.github.io/BWMirror/javadoc/
public class Main extends DefaultBWListener {

	public static final int MAX_GAMES = 5;

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
			if (isAliveAndIdle(unit)) {
				// acabou de terminar de executar --> atualizar estado
				if (units_running.containsKey(unit)) {
					State newState = getCurrentState(unit);
					StateAction sd = units_running.remove(unit);
					rl.updateState(sd.action, sd.state, newState);

					log(unit, "finished: " + sd.action);
				}
				// buscar estado corrente e executar acao
				State curState = getCurrentState(unit);
				int auxCounter = statesCounter.getOrDefault(curState, 1);
				statesCounter.put(curState, auxCounter + 1);
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

	private boolean isAliveAndIdle(Unit unit) {
		return unit.exists() && !unit.isMoving() && !unit.isStartingAttack() && !unit.isAttacking();
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

	private State getCurrentState(Unit me) {
		double hpFromNearbyEnemies = 0.d;
		int numberOfEnemiesUnitsNearby = 0;
		double hpFromNearbyAllies = 0.d;
		int numberOfAlliesUnitsNearby = 0;
		int distanceToClosestEnemyUnit = Integer.MAX_VALUE;

		List<Unit> units = me.getUnitsInRadius(3 * RangeDistance.MARINE_ATTACK_RANGE);
		for (Unit unit : units) {
			if (unit.getPlayer().isAlly(self)) {
				hpFromNearbyAllies += unit.getHitPoints();
				if (unit.exists())
					numberOfAlliesUnitsNearby++;
			} else if (unit.getPlayer().isEnemy(self)) {
				hpFromNearbyEnemies += unit.getHitPoints();
				if (unit.exists())
					numberOfEnemiesUnitsNearby++;
				distanceToClosestEnemyUnit = unit.exists() && me.getDistance(unit) < distanceToClosestEnemyUnit
						? me.getDistance(unit) : distanceToClosestEnemyUnit;
			}
		}

		hpFromNearbyAllies += me.getHitPoints();
		numberOfAlliesUnitsNearby++;

		State curState = new State(hpFromNearbyEnemies, numberOfEnemiesUnitsNearby, hpFromNearbyAllies,
				numberOfAlliesUnitsNearby, distanceToClosestEnemyUnit);

		return curState;
	}

	public static void main(String[] args) {
		new Main().run();
	}
}
package scrl.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bwapi.Color;
import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;
import bwapi.Position;
import bwapi.Unit;
import bwta.BWTA;
import scrl.RLUnitThread;
import scrl.SCRL;
import scrl.model.Actions;

public class TestBotSC1 extends DefaultBWListener{

	public static final int MAX_GAMES = 50;
	private static final boolean DEBUG = true;
	private Mirror mirror = new Mirror();
	private Game game;
	private Player self;
//	private Player enemy;
	private SCRL rl;
	static File outFile = new File("teste1.txt");
	private int initCounter = 1;
	private static int match = 0;
	ExecutorService executor = Executors.newFixedThreadPool(5);
	private int auxCounter = 0;

	public void run() {
		try {
			TestBotSC1.log("fun��o RUN DENTRO DE TESTBOTSC1, Primeiro item a ser chamado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mirror.getModule().setEventListener(this);
		mirror.startGame();
	}
	
	@Override
	public void onStart() {
		try {
			log("Entrou na fun��o onStart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game = mirror.getGame();
		self = game.self();
		
		//enemy = game.enemy();
		//game.setLocalSpeed(20);
		
		BWTA.readMap();
		BWTA.analyze();
		
		init();
	}

	private void init(){
		rl = new SCRL();
		rl.init(match);
		setInitCounter(getInitCounter() + 1);
	}
	

	@Override
	public void onFrame()
	{
		List<Unit> units = self.getUnits();
		for (Unit unit : units) {
			if(unit.isIdle()){
				game.drawCircleMap(unit.getPosition().getX(),unit.getPosition().getY(),15,Color.Orange);
				auxCounter++;
				try {
					log("Vai chamar o new RLUnitThread");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Runnable rlUnit = new RLUnitThread(game, rl, unit, self, this, game.enemy());
				try {
					log("Thread Created for idle Unit: Id: "+ unit.getID()+ " Will execute");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				executor.execute(rlUnit);
				try {
					log("Thread Created for idle Unit: Id: "+ unit.getID()+ " was given execute command");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		try {
			log("AuxCounter : "+auxCounter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	@Override
	public void onEnd(boolean isWinner) {
		rl.end(isWinner);
		match++;
		if (match == MAX_GAMES)
			System.exit(0);
	}
	
	public void executeAction(Actions actionToPerform, Unit me) throws IOException{
//		System.out.println(actionToPerform);
		if (actionToPerform.equals(Actions.ATTACK)) {
			attack(me);
		} else if (actionToPerform.equals(Actions.EXPLORE)) {
			explore(me);
		} else {
			flee(me);
		}
	}

	private void flee(Unit myUnit) throws IOException {
		myUnit.move(getToSafePlace(myUnit,game.enemy()));
	}

	private void explore(Unit myUnit) throws IOException {
		Random generator = new Random();
		int low = -150;
		int high = 150;
		int aux1 = generator.nextInt(high-low)+low;
		int aux2 = generator.nextInt(high-low)+low;
		myUnit.move(new bwapi.Position(myUnit.getPosition().getX() + (aux1), myUnit.getPosition().getY() +(aux2)));
		game.drawCircleMap(myUnit.getPosition().getX() + (aux1), myUnit.getPosition().getY() +(aux2),15,Color.Cyan);
	}

	private  Position getToSafePlace(Unit myUnit, Player enemy) {
		int posX = 0;
		int posy = 0;
		for (Unit enemyUnit : enemy.getUnits()) {
			posX += enemyUnit.getPosition().getX();
			posy += enemyUnit.getPosition().getY();
		}
		return new bwapi.Position(myUnit.getPosition().getX()-posX,myUnit.getPosition().getY()-posy);
		
	}

	private void attack(Unit myUnit) throws IOException {
		for (Unit enemyUnit : game.enemy().getUnits()) {
			if (myUnit.isInWeaponRange(enemyUnit)) {
				myUnit.stop();
				myUnit.attack(enemyUnit.getPosition());
				break;
			}
		}
	}
	
	public static void log(String msg) throws IOException {
		if (DEBUG){
			if(!outFile.isFile())
				outFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile,true));
			writer.append(msg);
			writer.newLine();
			writer.flush();
			writer.close();
		}
	}
	
	public int getInitCounter() {
		return initCounter;
	}

	public void setInitCounter(int initCounter) {
		this.initCounter = initCounter;
	}


	public static void main(String[] args) {
		new TestBotSC1().run();
	}

}
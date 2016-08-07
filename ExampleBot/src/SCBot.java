import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jalt.model.action.Action;
import org.jalt.model.state.State;

//public class SCBot {
	//public static void main(String[] args){
		//new Rn().Run();
	//}
//}
	
//class Rn{
public class SCBot implements Serializable{
	
	private final Set<UnitState> states;
	private final Map<Integer,UnitState> mappedStates = null;
	
	final Action ATTACK = new Action(Actions.ATTACK.getName());
	final Action FLEE = new Action(Actions.FLEE.getName());
	final Action EXPLORE = new Action(Actions.EXPLORE.getName());
	private final List<Action> actions = Arrays.asList(ATTACK,EXPLORE,FLEE);
	
	
	protected double[][] q;
	// 		q[][0] = atck
	//		q[][1] = explore
	//		q[][2] = flee
	
	private static final double GAMA = 0.8;
	private double ALPHA = 0.9;
	
	private	double hp; 
	private	double hpFromNearbyEnemies;  
	private int numberOfEnemyUnitsThatCanBeAttacked;
	private int numberOfEnemyUnitsThatCanAttackMe; 
	
	
	//public Rn()
	public SCBot()
	{
		states = creatStates();
		//creatStates2();
		//QTable<QTableItem> builder = new QTable<>(states,actions);
		q = new double[states.size()][3];
		for (int i = 0; i < states.size(); i++) {
			q[i][0] = 0;
			q[i][1] = 0;
			q[i][2] = 0;
		}
	}
	
	private void creatStates2() {
			int cont = 0;
			for(Range hp: Arrays.asList(Range.low,Range.mediumLow,Range.mediumHigh,Range.high)){
				for(Range hpFromNearbyEnemies: Arrays.asList(Range.low,Range.mediumLow,Range.mediumHigh,Range.high)){
					for(Range numberOfEnemyUnitsThatCanBeAttacked: Arrays.asList(Range.zero,Range.small,Range.medium,Range.large)){
						for(Range numberOfEnemyUnitsThatCanAttackMe: Arrays.asList(Range.zero,Range.small,Range.medium,Range.large)){
							mappedStates.put(cont, new UnitState(hp, hpFromNearbyEnemies, numberOfEnemyUnitsThatCanBeAttacked, numberOfEnemyUnitsThatCanAttackMe));
							//sts.add(new UnitState(hp, hpFromNearbyEnemies, numberOfEnemyUnitsThatCanBeAttacked, numberOfEnemyUnitsThatCanAttackMe));
							cont++;
						}
					}
				}
			}
		}

	private Set<UnitState> creatStates() {
		final Set<UnitState> sts = new HashSet<UnitState>();
		// hp range
		int cont = 0;
		for(Range hp: Arrays.asList(Range.low,Range.mediumLow,Range.mediumHigh,Range.high)){
			for(Range hpFromNearbyEnemies: Arrays.asList(Range.low,Range.mediumLow,Range.mediumHigh,Range.high)){
				for(Range numberOfEnemyUnitsThatCanBeAttacked: Arrays.asList(Range.zero,Range.small,Range.medium,Range.large)){
					for(Range numberOfEnemyUnitsThatCanAttackMe: Arrays.asList(Range.zero,Range.small,Range.medium,Range.large)){
						sts.add(new UnitState(hp, hpFromNearbyEnemies, numberOfEnemyUnitsThatCanBeAttacked, numberOfEnemyUnitsThatCanAttackMe));
						cont++;
					}
				}
			}
		}
		System.out.println(cont);
		return sts;
	}
	
	public Action step1(int hp,Double hpFromNearbyEnemies,int contnumberOfEnemyUnitsThatCanBeAttacked,int contnumberOfEnemyUnitsThatCanAttackMe) {
		
		//  em state, terei um outro obj, porem com valores iguais, como no OBJ STATE tem o index e o nome, serão diferentes, e aí?
		// implementar um cara pra ver se é igual? e a parte de sim, pega o cara q já tá tabelado?
		//State state  = new UnitState(hp,hpFromNearbyEnemies,contnumberOfEnemyUnitsThatCanBeAttacked,contnumberOfEnemyUnitsThatCanAttackMe);
		
		// esse cara nao tem um index
		UnitState stateQDeveriaSer  = new UnitState(hp,hpFromNearbyEnemies,contnumberOfEnemyUnitsThatCanBeAttacked,contnumberOfEnemyUnitsThatCanAttackMe,0);

		// update STATE
		this.hp = hp;
		this.hpFromNearbyEnemies = hpFromNearbyEnemies;
		this.numberOfEnemyUnitsThatCanBeAttacked = contnumberOfEnemyUnitsThatCanBeAttacked;
		this.numberOfEnemyUnitsThatCanAttackMe = contnumberOfEnemyUnitsThatCanAttackMe;
		
		Action action = getNextAction(stateQDeveriaSer);
		return action;
	}
	
	public void step2(Action actionToPerform, double attckOrExploreOrFleeMark, int hp, double contHpLife, int contnumberOfEnemyUnitsThatCanBeAttacked, int contnumberOfEnemyUnitsThatCanAttackMe) {
		
		///////////////////FEITO EM STEP1
		//updateState(attckOrExploreOrFleeMark);
	
		//reward preDefined?
		//double reward = getReward(); // nao tem esse agora certo? minha recompensa vem
	
		UnitState pState =  new UnitState(hp, contHpLife, contnumberOfEnemyUnitsThatCanBeAttacked, contnumberOfEnemyUnitsThatCanAttackMe,0);
		for (UnitState stt : states) {
			if(stt.getHp()==pState.getHp() && stt.getHpFromNearbyEnemies()==pState.getHpFromNearbyEnemies() && stt.getNumberOfEnemyUnitsThatCanAttackMe()==pState.getNumberOfEnemyUnitsThatCanAttackMe() && stt.getNumberOfEnemyUnitsThatCanBeAttacked()==pState.getNumberOfEnemyUnitsThatCanBeAttacked())
				updateQ(stt,actionToPerform,attckOrExploreOrFleeMark);
			break;
		}
	}
	
	private void updateQ(State state, Action action, double reward) {
		q[index(state)][actions.indexOf(action)] = computeQ(state, action, reward);
	}

	private double computeQ(State state, Action action, double reward) {
		// get current q value
		double cq = q[index(state)][actions.indexOf(action)];
		// compute the right side of the equation
		double value = reward + (GAMA * getMax(state)) - cq;
		// compute new q value
		double newq = cq + ALPHA * value;

		return newq;
	}

	protected double getMax(State pState) {
		double max = 0;
		// search for the Q v for each state
		for (Action action : actions) {
			double value = q[index(pState)][actions.indexOf(action)];
			if (value > max) {
				max = value;
			}
		}

		return max;
	}

	private Action getNextAction(UnitState pState) {
		if(pState == null)
			return null;
		// nao tem index, tem q procurar em STATES
		
		int stateIndex = 0;
		Boolean flag = true;
		for (UnitState stt : states) {
			if(stt.getHp()==pState.getHp() && stt.getHpFromNearbyEnemies()==pState.getHpFromNearbyEnemies() && stt.getNumberOfEnemyUnitsThatCanAttackMe()==pState.getNumberOfEnemyUnitsThatCanAttackMe() && stt.getNumberOfEnemyUnitsThatCanBeAttacked()==pState.getNumberOfEnemyUnitsThatCanBeAttacked()){
				stateIndex = stt.getIndex();
				double value0 = q[stateIndex][0]; // atck
				double value1 = q[stateIndex][1]; // explore
				double value2 = q[stateIndex][2]; // flee
				double max = Math.max(value0, Math.max(value1, value2));
				if(max == value1)
					return EXPLORE;
				else if(max == value2)
					return FLEE;
				else if(max == value0)
					return ATTACK;
			}
				
		}
		return EXPLORE;
	}

	private int index(State state) {
		UnitState us = (UnitState) state;
		return us.getIndex();
	}

	private State getState() {
		for(State state:states){
			UnitState s = (UnitState) state;
			if (s.in(hp, hpFromNearbyEnemies, numberOfEnemyUnitsThatCanBeAttacked, numberOfEnemyUnitsThatCanAttackMe))
				return s;
		}
		return null;
	}
	
	
	void init() {
		hp = 0;
		hpFromNearbyEnemies = 0;
		numberOfEnemyUnitsThatCanBeAttacked = 0;
		numberOfEnemyUnitsThatCanAttackMe = 0;
	}

	void init(double[][] tabela) {
		hp = 0;
		hpFromNearbyEnemies = 0;
		numberOfEnemyUnitsThatCanBeAttacked = 0;
		numberOfEnemyUnitsThatCanAttackMe = 0;
		q = tabela;
	}

	public void loadQTable() {
		try {
			FileInputStream fin = new FileInputStream("inputTable.txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			q = (double[][]) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.out.println("File nao abriu");
			e.printStackTrace();
		}
	}

	public void end() {
		FileOutputStream qtable;
		try {
			qtable = new FileOutputStream("path.out");
			ObjectOutputStream oos = new ObjectOutputStream(qtable);
	    	oos.writeObject(q);
	    	oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}


/////////////------------------------------------------------------------------------------------------------------------------------------- 
class UnitState extends State{
	private static int count;
	private int index;
	private	Range hp; // low medium high
	private	Range hpFromNearbyEnemies; // low medium high 
	private Range numberOfEnemyUnitsThatCanBeAttacked; // zero small medium large
	private Range numberOfEnemyUnitsThatCanAttackMe; // zero small medium large
	
	public UnitState(Range hp, Range hpFromNearbyEnemies, Range numberOfEnemyUnitsThatCanBeAttacked,Range numberOfEnemyUnitsThatCanAttackMe){
		index = count++;
		this.hp = hp;
		this.hpFromNearbyEnemies = hpFromNearbyEnemies;
		this.numberOfEnemyUnitsThatCanBeAttacked = numberOfEnemyUnitsThatCanBeAttacked;
		this.numberOfEnemyUnitsThatCanAttackMe = numberOfEnemyUnitsThatCanAttackMe;
		setName(index+ "");
	}
	
	public UnitState(double hp2, double hpFromNearbyEnemies2, int numberOfEnemyUnitsThatCanBeAttacked2,
			int numberOfEnemyUnitsThatCanAttackMe2) {
		index = count++;
		this.hp = Range.castToRangeHealth(hp2);
		this.hpFromNearbyEnemies = Range.castToRangeHealth(hpFromNearbyEnemies2);
		this.numberOfEnemyUnitsThatCanBeAttacked = Range.castToRangeNumber(numberOfEnemyUnitsThatCanBeAttacked2);
		this.numberOfEnemyUnitsThatCanAttackMe = Range.castToRangeNumber(numberOfEnemyUnitsThatCanAttackMe2);
		setName(index+ "");
	}

	public UnitState(int hp2, Double hpFromNearbyEnemies2, int contnumberOfEnemyUnitsThatCanBeAttacked,
			int contnumberOfEnemyUnitsThatCanAttackMe, int i) {
		this.hp = Range.castToRangeHealth(hp2);
		this.hpFromNearbyEnemies = Range.castToRangeHealth(hpFromNearbyEnemies2);
		this.numberOfEnemyUnitsThatCanBeAttacked = Range.castToRangeNumber(contnumberOfEnemyUnitsThatCanBeAttacked);
		this.numberOfEnemyUnitsThatCanAttackMe = Range.castToRangeNumber(contnumberOfEnemyUnitsThatCanAttackMe);
	}
	
	public boolean in(double hp2, double hpFromNearbyEnemies2, int numberOfEnemyUnitsThatCanBeAttacked2,
			int numberOfEnemyUnitsThatCanAttackMe2) {
		return hp.in(hp2) && hpFromNearbyEnemies.in(hpFromNearbyEnemies2) && numberOfEnemyUnitsThatCanBeAttacked.in(numberOfEnemyUnitsThatCanBeAttacked2) && numberOfEnemyUnitsThatCanAttackMe.in(numberOfEnemyUnitsThatCanAttackMe2);
	}

	@Override
	public String toString() {
		return getName();
	}

	public int getIndex() {
		return index;
	}

	public Range getHp() {
		return hp;
	}

	public Range getHpFromNearbyEnemies() {
		return hpFromNearbyEnemies;
	}

	public Range getNumberOfEnemyUnitsThatCanBeAttacked() {
		return numberOfEnemyUnitsThatCanBeAttacked;
	}

	public Range getNumberOfEnemyUnitsThatCanAttackMe() {
		return numberOfEnemyUnitsThatCanAttackMe;
	}
	
}

/////////////-------------------------------------------------------------------------------------------------------------------------------
class Range {
	double min;
	double max;

	static Range low = new Range(0, 25);
	static Range mediumLow = new Range(25, 50);
	static Range mediumHigh = new Range(50, 75);
	static Range high = new Range(75, 100);

	// numbers		
	static Range zero = new Range(0, 0);
	static Range small = new Range(1, 2);
	static Range medium = new Range(2, 3);
	static Range large = new Range(3,50); // e o caso q se tenha mais?
	
	public Range() {
	}
	
	public Range(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public static Range castToRangeNumber(int value) {
		Range temp = new Range();
		if(value == 0)
			return temp.zero;
		else if(value <= 2)
			return temp.small;
		else if(value <= 3)
			return temp.medium;
		else if(value <= 50)
			return temp.large;
		return null;
	}
	
	public static Range castToRangeHealth(int value) {
		Range temp = new Range();
		if(value >= 0 && value <= 25)
			return temp.low;
		else if(value <= 50)
			return temp.mediumLow;
		else if(value <= 75)
			return temp.mediumHigh;
		else if(value <= 100)
			return temp.high;
		return temp;
	}
	
	
	public static Range castToRangeHealth(double value) {
		Range temp = new Range();
		if(value >= 0 && value <= 25)
			return temp.low;
		else if(value <= 50)
			return temp.mediumLow;
		else if(value <= 75)
			return temp.mediumHigh;
		else if(value <= 100)
			return temp.high;
		return temp;
	}


	public boolean in(double v) {
		return min <= v && v < max;
	}

	
	@Override
	public String toString() {
		return "[min=" + min + ", max=" + max + "]";
	}

}
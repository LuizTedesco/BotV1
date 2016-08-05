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
	private int steps;
	
	
	//public Rn()
	public SCBot()
	{
		states = creatStates();
		creatStates2();
		//QTable<QTableItem> atck = new QTable<>(states,actions);
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
		
		Action action = getNextAction(stateQDeveriaSer);
		return action;
		
		// updateState e a reward, dependem da ação já executada
		// como fazer?
		// preciso desviar para o BOT AGORA
		//updateState(action);
		//double reward = getReward();
		//updateQ(state,action,reward);
		// TODO
	}
	
public void step2(double attckOrExploreOrFleeMark) {
		
		// updateState e a reward, dependem da ação já executada
		// como fazer?
		// preciso desviar para o BOT AGORA
		updateState(attckOrExploreOrFleeMark);
		//double reward = getReward(); // nao tem esse agora certo? minha recompensa vem 
		//updateQ(state,action,reward);
		// TODO
		
	}
	
	private void updateQ(State state, Action action, double reward) {
		q[index(state)][actions.indexOf(action)] = computeQ(state, action, reward);
	}

	private double computeQ(State state, Action action, double reward) {
		// TODO Auto-generated method stub
		return 0;
	}

	private synchronized void updateState(Double value) {
		
	}

	/*public Action Run() {
		State state = getState();
		Action action;
			action = getNextAction(state);
			if(action!= null)
			{
				//updateState() ?
				//double reward = getReward();
				//updateQ(state,action,reward);
				state = getState();
			}
		//actions.add(ATTACK);
		//actions.add(FLEE);
		//actions.add(EXPLORE);
		//MDP mdp = new MDPModel(transitionFunction, rewardFunction, states, actions, agents);
			return action;
	}*/

	private Action getNextAction(UnitState pState) {
		if(pState == null)
			return null;
		// nao tem index, tem q procurar em STATES
		
		
		pState.getHp();
		pState.getHpFromNearbyEnemies();
		pState.getNumberOfEnemyUnitsThatCanBeAttacked();
		pState.getNumberOfEnemyUnitsThatCanAttackMe();

		
		int stateIndex = 0;
		for (UnitState stt : states) {
			
		}
		
		
		
		
		double value0 = q[index(pState)][0];
		double value1 = q[index(pState)][1];
		double value2 = q[index(pState)][2];
		double max = Math.max(value0, Math.max(value1, value2));
		if(max == value0)
			return ATTACK;
		else if(max == value1)
			return EXPLORE;
		else if(max == value2)
			return FLEE;
		else
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

	void init(double[][] tabela) {
		hp = 0;
		hpFromNearbyEnemies = 0;
		numberOfEnemyUnitsThatCanBeAttacked = 0;
		numberOfEnemyUnitsThatCanAttackMe = 0;
		q = tabela;
		
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
		this.hp = Range.castToRange(hp2);
		this.hpFromNearbyEnemies = Range.castToRange(hpFromNearbyEnemies2);
		this.numberOfEnemyUnitsThatCanBeAttacked = Range.castToRange(numberOfEnemyUnitsThatCanBeAttacked2);
		this.numberOfEnemyUnitsThatCanAttackMe = Range.castToRange(numberOfEnemyUnitsThatCanAttackMe2);
		setName(index+ "");
	}

	public UnitState(int hp2, Double hpFromNearbyEnemies2, int contnumberOfEnemyUnitsThatCanBeAttacked,
			int contnumberOfEnemyUnitsThatCanAttackMe, int i) {
		this.hp = Range.castToRange(hp2);
		this.hpFromNearbyEnemies = Range.castToRange(hpFromNearbyEnemies2);
		this.numberOfEnemyUnitsThatCanBeAttacked = Range.castToRange(contnumberOfEnemyUnitsThatCanBeAttacked);
		this.numberOfEnemyUnitsThatCanAttackMe = Range.castToRange(contnumberOfEnemyUnitsThatCanAttackMe);
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
	
	public static Range castToRange(int value) {
		Range temp = new Range();
		if(value == 0)
			return temp.zero;
		else if(value >=1 && value <= 2)
			return temp.small;
		else if(value >2 && value <= 3)
			return temp.medium;
		else if(value >3 && value <= 50)
			return temp.large;
		return null;
	}
	
	public static Range castToRange(double value) {
		Range temp = new Range();
		if(value > 0 && value <= 25)
			return temp.low;
		else if(value >25 && value <= 50)
			return temp.mediumLow;
		else if(value >51 && value <= 75)
			return temp.mediumHigh;
		else if(value >75 && value <= 100)
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
package scrl.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import scrl.model.Actions;
import scrl.model.UnitState;
import scrl.tests.TestBotSC1;

public class QTable extends ConcurrentHashMap<UnitState, Map<Actions, Double>>{

	private static final long serialVersionUID = 3826717973754083254L;
	
	private Random rand = new Random();
	private double epsilon = 0.1;
	/*private double epsilon = 0;*/
	
	// TABELA ORIGINAL
	public QTable(Collection<UnitState> states, Collection<Actions> actions) {
		// TODO
		// oq isso faz? RODAR uma com e uma sem
		super(states.size());		
		for (UnitState state : states) {
			Map<Actions, Double> actionValues = new ConcurrentHashMap<Actions, Double>();
			for (Actions action : actions)
				actionValues.put(action, 0.);
			put(state, actionValues);
		}
	}
	
	// TABELA ideal	
	/*public QTable(Collection<UnitState> states, Collection<Actions> actions) {
		TestBotSC1.log("Criou a tabela ideal");
		
        String csvFile = "desiredQ.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        double ATTACK;
        double FLEE;
        double EXPLORE;
        
        try {

            br = new BufferedReader(new FileReader(csvFile));
            
            while ((line = br.readLine()) != null) {
            	
                // use comma as separator
                String[] lineData = line.split(cvsSplitBy);
                
                UnitState state = new UnitState(lineData[0], lineData[1], lineData[2], lineData[3]);
                
                System.out.println("state.toString()");
                System.out.println(state.toString());
                System.out.println("**************************************************************************");
                Map<Actions, Double> actionValues = new ConcurrentHashMap<Actions, Double>();
                EXPLORE = Double.parseDouble(lineData[6]);
                FLEE = Double.parseDouble(lineData[5]);
                ATTACK = Double.parseDouble(lineData[4]);
                for (Actions action : actions)
                {
                	if(action.equals(Actions.EXPLORE))
                		actionValues.put(action, EXPLORE);
                	else if(action.equals(Actions.FLEE))
                		actionValues.put(action, FLEE);
                	else if(action.equals(Actions.ATTACK))
                		actionValues.put(action, ATTACK);
                }
                
                System.out.println("State");
                System.out.println(state.toString());
                System.out.println("AactionValues");
                System.out.println(actionValues.toString());
				put(state, actionValues);
            }
            System.out.println("Qtable");
            System.out.println(this.toString());
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}*/

	public Actions getMaxAction(UnitState pState) {
		Map<Actions, Double> map = this.get(pState);
		double max = Double.NEGATIVE_INFINITY;
		Actions ret = null;
			for (Actions act : map.keySet()) {
				if (map.get(act) > max) {
					max = map.get(act);
					ret = act;
				}
			}
		return ret;
	}

	public Actions chooseNextAction(UnitState state) {
		Actions action = null;
		double rnd = rand.nextDouble();
		if (rnd < epsilon) {
			// select random
			action = Actions.values()[rand.nextInt(Actions.values().length)];
		} else {
			action = getMaxAction(state);
		}
		return action;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
	
	
	public double getEpsilon() {
		return epsilon;
	}

	@Override
	public String toString() {				
		StringBuilder builder = new StringBuilder();
		for (UnitState state : this.keySet()) {
			builder.append("   ");
			//builder.append(state.toString());
			builder.append(state.toString2());
			builder.append(this.get(state));
			builder.append("\n");
		}
		return builder.toString();
	}

}


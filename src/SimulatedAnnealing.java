import java.io.IOException;

public class SimulatedAnnealing {
	
	private double temp = 100000;
    private double coolingRate = 0.0003;
	private PreferenceTable prefTable;
	
	public  SimulatedAnnealing(PreferenceTable table) throws IOException {
		prefTable = table;
		try {
			prefTable.setupStudents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Algorithm();
	}
	
	public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }

	
	public static CandidateSolution change(CandidateSolution sol, double temp, PreferenceTable table) {
		int prevEnergy = sol.getEnergy();
		CandidateSolution oldSol = sol;
		sol.makeChange();
		int difference = 0;
		
		
		if(sol.getEnergy() < prevEnergy){
			return sol;
		}else {
			if(acceptanceProbability(oldSol.getEnergy(), sol.getEnergy(), temp)  > Math.random()) {
				return sol;
			} else {
				return oldSol;
			}
		}
		
	}
	
	public void Algorithm() throws IOException {
		CandidateSolution currentSolution = new CandidateSolution(prefTable);
		CandidateSolution bestSolution = new CandidateSolution(prefTable);
        System.out.println("Initial solution: " + currentSolution.getEnergy());
        int  bestEnergy = currentSolution.getEnergy();// Set as current best
        PreferenceTable table;
        change(currentSolution, temp, prefTable);
        
        // Loop until system has cooled
        while (temp > 1) {
            // Create new neighbour 
        	table = new PreferenceTable("tabfile.txt");
			table.setupStudents();
        	CandidateSolution newSolution = new CandidateSolution(table);
        	currentSolution = change(newSolution, temp, table);

            // Keep track of the best solution found
            if (currentSolution.getEnergy() < bestEnergy) {
            	bestSolution = currentSolution;
                bestEnergy = currentSolution.getEnergy();
               
            } 
            // Cool system
            //System.out.println(temp);
            temp *= 1-coolingRate;
        }

        System.out.println("Final solution Energy: " + bestEnergy);
        bestSolution.printSolution();
        bestSolution.printPreferences();
	}
	}

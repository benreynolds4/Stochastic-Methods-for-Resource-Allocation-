import java.io.IOException;

public class TestClass {	
		public static void main(String[] args) throws IOException{
			PreferenceTable table = new PreferenceTable("tabfile.txt");
			table.setupStudents();
	
			helperMethods help = new helperMethods();
			// Cooling rate
			double temp = 10000;
	        double coolingRate = 0.0003;
	        table = new PreferenceTable("tabfile.txt");
			table.setupStudents();
			CandidateSolution currentSolution = new CandidateSolution(table);
	        System.out.println("Initial solution: " + currentSolution.getEnergy());
	        int  bestEnergy = currentSolution.getEnergy();// Set as current best
	        
	        // Loop until system has cooled
	        while (temp > 1) {
	            // Create new neighbour tour
	        	table = new PreferenceTable("tabfile.txt");
				table.setupStudents();
	        	CandidateSolution newSolution = new CandidateSolution(table);
	            
	            // Get energy of solutions
	            int currentEnergy = currentSolution.getEnergy();
	            int neighbourEnergy = newSolution.getEnergy();
	            

	            // Decide if we should accept the neighbour
	            if (help.acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
	            	
	            	currentSolution = newSolution;
	            }

	            // Keep track of the best solution found
	            if (currentSolution.getEnergy() < bestEnergy) {
	                bestEnergy = currentSolution.getEnergy();
	            }
	            // Cool system
	            //System.out.println(temp);
	            temp *= 1-coolingRate;
	        }

	        System.out.println("Final solution Energy: " + bestEnergy);
	        //currentSolution.printSolution();
		}
	}
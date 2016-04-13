import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;

public class TestClass {	
		public static void main(String[] args) throws IOException{
			PreferenceTable table = new PreferenceTable("tabfile.txt");
			table.setupStudents();
			System.out.println(table.getAllStudentEntries());
			
			// Test Loki Laufeyson
			System.out.println("\n");
			StudentEntry student = table.getEntryFor("Loki Laufeyson");
			System.out.println(student.getStudentName());
			System.out.println(student.getNumberOfStatedPreferences());
			System.out.println(student.getOrderedPreferences());
			System.out.println(student.hasPreAssignedProject());
			
			
			//Test Uriah Heap
			System.out.println("\n");
			student = table.getEntryFor("Uriah Heap");
			System.out.println(student.getStudentName());
			System.out.println(student.getNumberOfStatedPreferences());
			System.out.println(student.getOrderedPreferences());
			System.out.println(student.hasPreAssignedProject());
			
			// Test Bridget Jones
			System.out.println("\n");
			student = table.getEntryFor("Bridget Jones");
			System.out.println(student.getStudentName());
			System.out.println(student.getNumberOfStatedPreferences());
			System.out.println(student.getOrderedPreferences());
			System.out.println(student.hasPreAssignedProject());
			
			// Random Student Test
			System.out.println("\nRandom Student:");
			System.out.println(table.getRandomStudent().getStudentName());
			
			
			// Test Has Preference
			student = table.getEntryFor("Bridget Jones");
			System.out.println("\nBridget Jones Has Preference: Recommending Movies Using Curated IMDb Lists?");
			System.out.println(student.hasPreference("Recommending Movies Using Curated IMDb Lists"));
			
			// Test Add Preferences:
			
			System.out.println("\n\nWEEK 4 TESTS Prints Amount of Projects Students have before and afteer filling all: ------------------\n");
			Collection<StudentEntry> students = table.getAllStudentEntries().values();
			for(StudentEntry stu : students) {
				System.out.println(stu.getOrderedPreferences().size());
			}
			
			System.out.println("AFTER FILLING ------------------");
			
			table.fillPreferencesOfAll(10);
			students = table.getAllStudentEntries().values();
			for(StudentEntry stu : students) {
				System.out.println(stu.getOrderedPreferences().size());
			}
			
			
		
			System.out.println("\n\nWEEK 5 TESTS Prints Student and there random Assignment: ------------------\n");
			CandidateSolution cs = new CandidateSolution(table);
			cs.printSolution();
	
			CandidateSolution best = new CandidateSolution(table);
			for(int i =0; i<500; i++) {
				CandidateSolution newSolution = new CandidateSolution(table);
				if (newSolution.getEnergy() < best.getEnergy() ) {
					best = newSolution;
				}
			}
			
			System.out.println(best.getEnergy());
			System.out.println(best.getFitness());
			best.printPreferences();
			
			
			
			/*Conors Simulated Annealing
			System.out.println("\n\nSimulated Annealing");
			double temp = 10000;
	        double coolingRate = 0.00001;
	        CandidateSolution Solution = new CandidateSolution(table);
	        int currentSolution = Solution.getEnergy() ;
	        int currentFitness = 0;
	        
	        for(int i =0; i< 30; i++) {
	        	temp = 10000;
	        	while (temp > 1) {
		        	CandidateSolution newSolution = new CandidateSolution(table);
		        	
		        	if (newSolution.getEnergy() < currentSolution){
		        		currentSolution = newSolution.getEnergy() ;
		        		currentFitness = newSolution.getFitness();
		        	}
		        	temp *= 1-coolingRate;
		        }
	        	System.out.println("The best solution is: " + currentSolution) ;
		        System.out.println("Best Fitness: " + currentFitness);
	        } */
			
			System.out.println("Projects List:");
			System.out.println(table.getProjects());
			
			helperMethods help = new helperMethods();
			// Cooling rate
			double temp = 10000;
	        double coolingRate = 0.00003;
	        CandidateSolution currentSolution = new CandidateSolution(table); // Initialize intial solution
	        System.out.println("Initial solution distance: " + currentSolution.getEnergy());
	        int  bestEnergy = currentSolution.getEnergy();// Set as current best
	        
	        // Loop until system has cooled
	        while (temp > 1) {
	            // Create new neighbour tour
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
	            
	            temp *= 1-coolingRate;
	        }

	        System.out.println("Final solution Energy: " + bestEnergy);
			
			
			
			
			
		}
	}


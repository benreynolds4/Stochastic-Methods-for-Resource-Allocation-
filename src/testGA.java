import java.io.IOException;

public class testGA {	
		public static void main(String[] args) throws IOException{
			PreferenceTable table = new PreferenceTable("tabfile.txt");
			table.setupStudents();
			  // Set a candidate solution
	        FitnessCalc.setSolution("1111000000000000000000000000000000000000000000000000000000001111");

	        // Create an initial population
	        GAPopn myPop = new GAPopn(table, 1000, true);
	        
	        // Evolve our population until we reach an optimum solution
	        /*int generationCount = 0;
	        while (generationCount < 1000) {
	            generationCount++;
	            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getEnergy());
	            myPop = GAAlgo.evolvePopulation(myPop);
	        }  */
	        
	        CandidateSolution a = new CandidateSolution(table);
	        table = new PreferenceTable("tabfile.txt");
			table.setupStudents();
			CandidateSolution d = new CandidateSolution(table);
			for(int i=0; i< 1000; i++) {
				d = new CandidateSolution(table);
				a = GAAlgo.swapEach(a, d);
			}
			System.out.println(a.getEnergy());
	        
	        
	        System.out.println("Solution found!");
	      //  System.out.println("Generation: " + generationCount);
	        System.out.println("Genes:");
	        System.out.println(myPop.getFittest());
		}
}
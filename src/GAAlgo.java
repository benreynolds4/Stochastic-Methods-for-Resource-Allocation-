import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class GAAlgo {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;
    static PreferenceTable table;
    /* Public methods */
    
    // Evolve a population
    public static GAPopn evolvePopulation(GAPopn pop) throws IOException {
    	table = new PreferenceTable("tabfile.txt");
    	table.setupStudents();
        GAPopn newPopulation = new GAPopn(table, pop.popSize(), true);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveSolution(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.popSize(); i++) {
        	table = new PreferenceTable("tabfile.txt");
        	table.setupStudents();
            CandidateSolution indiv1 = tournamentSelection(pop);
            CandidateSolution indiv2 = tournamentSelection(pop);
            CandidateSolution newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveSolution(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.popSize(); i++) {
           // mutate(newPopulation.getSolution(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static CandidateSolution crossover(CandidateSolution indiv1, CandidateSolution indiv2)throws NullPointerException 
    {
    	
    	CandidateSolution newSol = new CandidateSolution(table);
        // Loop through genes
    	Hashtable<String, StudentEntry> studentEntries = table.getAllStudentEntries();
	    Enumeration<StudentEntry> e = studentEntries.elements();
	    StudentEntry entry; 
	    while(e.hasMoreElements()) {
	    	entry = e.nextElement();
	    	//System.out.println(entry.getStudentName());
	    	//System.out.println(indiv1.getAssignmentFor(entry));   ERROR HERE
	    	if((indiv1.getAssignmentFor(entry) != null) && (indiv2.getAssignmentFor(entry) != null)){						// ERROR HERE
	    		if (Math.random() <= uniformRate) {
	                newSol.setAssignment(entry, indiv1.getAssignmentFor(entry));
	            } else {
	                newSol.setAssignment(entry, indiv2.getAssignmentFor(entry));
	            }
	    	}
	    } 
	    
        return newSol;
    }

    // Mutate an individual
   
    
    public static CandidateSolution swapEach(CandidateSolution a, CandidateSolution b) throws NullPointerException, IOException{
    	PreferenceTable tab = new PreferenceTable("tabfile.txt");
    	tab.setupStudents();
    	System.out.println(tab.getAllStudentEntries());
    	Hashtable<String, StudentEntry> studentEntries = tab.getAllStudentEntries();
	    Enumeration<StudentEntry> e = studentEntries.elements();
	    StudentEntry entry; 
	    CandidateAssignment assignmentOne;
	    CandidateAssignment assignmentTwo;
	    PreferenceTable tab2 = new PreferenceTable("tabfile.txt");
    	tab2.setupStudents();
	    CandidateSolution c= new CandidateSolution(tab2);
	    while(e.hasMoreElements()) {
	    	entry = e.nextElement();
	    	assignmentOne =a.getAssignmentFor(entry);
	    	assignmentTwo = b.getAssignmentFor(entry);
	    	if(assignmentOne.getAssignmentRank() < assignmentTwo.getAssignmentRank()) {
	    		c.setAssignment(entry, assignmentOne);
	    	} else {
	    		c.setAssignment(entry, assignmentTwo);
	    	}
	    }
	    
	    if(a.getEnergy() > c.getEnergy()) {
	    	return c;
	    } else {
	    	return a;
	    }

    }

    // Select individuals for crossover
    private static CandidateSolution tournamentSelection(GAPopn pop) throws IOException {
        // Create a tournament population
        GAPopn tournament = new GAPopn(table, tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.popSize());
            tournament.saveSolution(i, pop.getSolution(randomId));
        }
        // Get the fittest
        CandidateSolution fittest = tournament.getFittest();
        return fittest;
    }
}
import java.util.Enumeration;
import java.util.Hashtable;
//GA Population

public class GAPopn {
	private CandidateSolution[] population;
	PreferenceTable preftable;
	int size = 0;
	public GAPopn(PreferenceTable table, int inSize, boolean initialise) {
		preftable = table;
		size = inSize;
		population = new CandidateSolution[size];
		if (initialise){
			for(int i=0; i<size; i++){
				CandidateSolution newSolution = new CandidateSolution(preftable);
				newSolution.generateIndividual();
				saveSolution(i, newSolution);
			}
		}
	}
	
	public void saveSolution(int index, CandidateSolution sol) {
        population[index] = sol;
    }
	
	public int popSize() {
        return population.length;
    }
	
	public CandidateSolution getFittest() {
		CandidateSolution sol = new CandidateSolution(preftable);
		CandidateSolution best = new CandidateSolution(preftable); 
		for (int i = 0; i < popSize(); i++) {
			if(population[i].getEnergy() < best.getEnergy()) {
				best = population[i];
			}
		}
		return best;
	}
	
	 public CandidateSolution getSolution(int index) {
	        return population[index];
	    }
	 
	 
	 

}

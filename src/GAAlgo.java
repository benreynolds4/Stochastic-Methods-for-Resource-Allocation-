import java.io.IOException;

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
        GAPopn newPopulation = new GAPopn(table, pop.popSize(), false);

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
            CandidateSolution indiv1 = tournamentSelection(pop);
            CandidateSolution indiv2 = tournamentSelection(pop);
            CandidateSolution newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveSolution(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.popSize(); i++) {
            mutate(newPopulation.getSolution(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static CandidateSolution crossover(CandidateSolution indiv1, CandidateSolution indiv2) {
    	CandidateSolution newSol = new CandidateSolution(table);
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(CandidateSolution indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }

    // Select individuals for crossover
    private static CandidateSolution tournamentSelection(GAPopn pop) {
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
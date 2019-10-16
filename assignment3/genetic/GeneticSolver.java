package assignment3.genetic;

import java.util.ArrayList;


/**
 * Implementation of Genetic algorithm.
 * @param <T> data type of state value.
 */
public abstract class GeneticSolver<T> {
    private Gene<T> gene;

    private int initialNumber;
    private int parentNumber;
    private int crossNumber;
    private int mutationNumber;

    public ArrayList<Meta> meta;
    public static final int MAX_METADATA = 100;

    /**
     * Construct new genetic solver.
     * @param gene Gene<T>, genetic interface.
     * @param initialNumber int, the number of the initial state.
     * @param parentNumber int, the number of the selected state, it should be positive number.
     * @param crossNumber int, the number of the mixed state.
     * @param mutationNumber int, the number of the mutant.
     */
    public GeneticSolver(Gene<T> gene,
                         int initialNumber,
                         int parentNumber,
                         int crossNumber,
                         int mutationNumber) {
        if (parentNumber <= 0) {
            throw new IllegalArgumentException(
                "GeneticSolver.parentNumber should be bigger than zero");
        }
        this.gene = gene;

        this.initialNumber = initialNumber;
        this.parentNumber = parentNumber;
        this.crossNumber = crossNumber;
        this.mutationNumber = mutationNumber;

        this.meta = new ArrayList<Meta>(MAX_METADATA);
    }

    /**
     * Initialize state.
     * @return T[], initial state.
     */
    private T[] initialize() {
        // create array
        T[] state = gene.newArray(initialNumber);
        // assign initial states.
        for (int i = 0; i < initialNumber; ++i) {
            state[i] = gene.initialState();
        }
        return state;
    }

    /**
     * Derive new generation.
     * @param generation T[], previous generation.
     * @return T[], next generation.
     */
    private T[] nextGeneration(T[] generation) {
        // create new array for parent genes
        T[] parent = gene.newArray(parentNumber);
        // select genes for next generation
        for (int i = 0; i < parentNumber; ++i) {
            parent[i] = gene.select(generation);
        }

        // create new array for next generation
        T[] state = gene.newArray(parentNumber + crossNumber + mutationNumber);
        System.arraycopy(parent, 0, state, 0, parentNumber);

        // mix information
        int pos = parentNumber;
        for (int i = 0; i < crossNumber; ++i, ++pos) {
            state[pos] = gene.crossover(parent);
        }

        // random mutation
        for (int i = 0; i < mutationNumber; ++i, ++pos) {
            state[pos] = gene.mutate(parent);
        }

        return state;
    }

    /**
     * Find solution from generation.
     * @param generation T[], states array.
     * @return T[], solution states.
     */
    abstract public T[] findSolution(T[] generation);

    /**
     * Run genetic algorithm to solve problem.
     * @param maxIter int, the number of maxium iterations.
     * @return T[], result array.
     */
    public T[] run(int maxIter) {
        // initialize state
        T[] state = initialize();
        for (int i = 0; i < maxIter; ++i) {
            // derive next generation
            state = nextGeneration(state);
            // find solution
            T[] sol = findSolution(state);
            if (sol != null) {
                writeMetaData(i, true);
                return sol;
            }
        }

        writeMetaData(maxIter, false);
        return null;
    }

    /**
     * Write metadata thread safely.
     * @param ntry int, number of try.
     * @param solved boolean, whether GeneticSolver is solved or not.
     */
    private void writeMetaData(int ntry, boolean solved) {
        Meta metadata = new Meta(ntry, solved);
        synchronized(this) {
            // resize array
            if (meta.size() >= MAX_METADATA) {
                meta.remove(0);
            }
            meta.add(metadata);
        }
    }

    public static class Meta {
        public int ntry;
        public boolean solved;

        /**
         * Construct meta data with given parameters.
         * @param ntry int, number of try.
         * @param solved boolean, whether GeneticSolver is solved or not.
         */
        public Meta(int ntry, boolean solved) {
            this.ntry = ntry;
            this.solved = solved;
        }
    }
}

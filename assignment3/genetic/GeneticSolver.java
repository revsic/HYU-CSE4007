package assignment3.genetic;


public abstract class GeneticSolver<T> {
    private Gene<T> gene;

    private int initialNumber;

    private int parentNumber;

    private int crossNumber;

    private int mutationNumber;

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
    }

    private T[] initialize() {
        T[] state = gene.newArray(initialNumber);
        for (int i = 0; i < initialNumber; ++i) {
            state[i] = gene.initialState();
        }
        return state;
    }

    private T[] nextGeneration(T[] generation) {
        T[] parent = gene.newArray(parentNumber);
        for (int i = 0; i < parentNumber; ++i) {
            parent[i] = gene.select(generation);
        }

        T[] state = gene.newArray(parentNumber + crossNumber + mutationNumber);
        System.arraycopy(parent, 0, state, 0, parentNumber);

        int pos = parentNumber;
        for (int i = 0; i < crossNumber; ++i, ++pos) {
            state[pos] = gene.crossover(parent);
        }

        for (int i = 0; i < mutationNumber; ++i, ++pos) {
            state[pos] = gene.mutate(parent);
        }

        return state;
    }

    abstract public T[] findSolution(T[] generation);

    public T[] run(int maxIter) {
        T[] state = initialize();
        for (int i = 0; i < maxIter; ++i) {
            state = nextGeneration(state);

            T[] sol = findSolution(state);
            if (sol.length > 0) {
                return sol;
            }
        }

        return null;
    }
}

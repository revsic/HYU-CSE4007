package assignment3.genetic;


/**
 * Gene interface for genetic algorithm.
 * @param <T> data type for the individual state.
 */
public interface Gene<T> {
    /**
     * Construct new array with given size.
     * @param size int, size of the array.
     * @return T[], new array.
     */
    public T[] newArray(int size);

    /**
     * Initialize first generation.
     * @return T, new gene.
     */
    public T initialState();

    /**
     * Select gene from previous generation
     * to deliver information to next generation.
     * @return T, selected gene, parent.
     */
    public T select(T[] population);

    /**
     * Mix information from selected genes.
     * @return T, mixed gene.
     */
    public T crossover(T[] parents);

    /**
     * Random mutation for genetic variety.
     * @return T, mutant.
     */
    public T mutate(T[] parents);
}

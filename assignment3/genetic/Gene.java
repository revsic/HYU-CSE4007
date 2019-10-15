package assignment3.genetic;


public interface Gene<T> {
    public T[] newArray(int size);

    public T initialState();

    public T select(T[] family);

    public T crossover(T[] parents);

    public T mutate(T[] parents);
}

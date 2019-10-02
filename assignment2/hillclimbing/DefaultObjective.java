package assignment2.hillclimbing;

import problem.nqueens.NQueens;


public abstract class DefaultObjective implements Policy {
    @Override
    public double objective(NQueens nqueens) {
        return 0;
    }

    public abstract Record next(Record record);
}
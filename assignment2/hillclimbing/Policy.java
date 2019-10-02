package assignment2.hillclimbing;

import problem.nqueens.NQueens;


public interface Policy {
    public double objective(NQueens queens);

    public Record next(Record current);
}

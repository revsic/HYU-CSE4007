package assignment2.hillclimbing;

import problem.nqueens.NQueens;
import problem.nqueens.NQueensObjective;


/**
 * Define default objective.
 */
public abstract class DefaultObjective implements Policy {
    /**
     * Compute default objectives from N-Queens environment.
     * @param queens N-Queens environment.
     * @return objective value.
     */
    @Override
    public double objective(NQueens nQueens) {
        return NQueensObjective.run(nQueens);
    }

    /**
     * Get next records from neighbor sequence.
     * @param current current record.
     * @return next record.
     */
    public abstract Record next(Record record);
}

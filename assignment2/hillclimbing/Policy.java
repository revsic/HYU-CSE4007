package assignment2.hillclimbing;

import problem.nqueens.NQueens;


/**
 * Hill climbing policy.
 */
public interface Policy {
    /**
     * Compute objectives from N-Queens environment.
     * @param queens N-Queens environment.
     * @return objective value.
     */
    public double objective(NQueens queens);

    /**
     * Get next records from neighbor sequence.
     * @param current current record.
     * @return next record.
     */
    public Record next(Record current);
}

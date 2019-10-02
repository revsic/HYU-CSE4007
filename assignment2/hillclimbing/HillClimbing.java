package assignment2.hillclimbing;

import assignment2.hillclimbing.Objective;
import problem.nqueens.NQueens;
import problem.nqueens.Solution;


/**
 * N-Queens problem solver with Hill-Climbing policy.
 */
public class HillClimbing implements Solution {
    private int maxRetry;
    private Objective objective;

    public HillClimbing(int maxRetry, Objective objective) {
        this.maxRetry = maxRetry;
        this.objective = objective;
    }

    /**
     * Name of the solution.
     * @return name of the solution.
     */
    @Override
    public String name() {
        return "Hill Climbing";
    }

    /**
     * Solve N-Queens problem.
     * @param size int, size of the board.
     * @return 2D coordinates array for queens if solution exist, else null.
     */
    @Override
    public int[][] solve(int size) {
        int try = 0;
        Record record = null;
        do {
            record = Record.random(size, objective);
            double current = objective.score(record.nQueens);
            while (true) {
                Record best = record.highestNeighbor();
                double next = objective.score(best.nQueens);
                if (current >= next) {
                    break;
                }

                record = best;
                current = next;
            }

            try += 1;
        } while (record.nQueens.isSolved() || try > maxRetry);

        if (try > maxRetry) {
            return null;
        }
        return record.coords.toArray(new int[2][size]);
    }
}

package assignment2.hillclimbing;

import problem.nqueens.Solution;


/**
 * N-Queens problem solver with Hill-Climbing policy.
 */
public class HillClimbing implements Solution {
    private int maxRetry;
    private Policy policy;

    /**
     * Construct hill climbing method with given policy.
     * @param maxRetry maximum number of retry.
     * @param policy neighbor selection policy.
     */
    public HillClimbing(int maxRetry, Policy policy) {
        this.maxRetry = maxRetry;
        this.policy = policy;
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
        for (int i = 0; i < maxRetry; ++i) {
            Record record = Record.random(size);
            double score = policy.objective(record.nQueens);
            while (true) {
                Record next = policy.next(record);
                double nextScore = policy.objective(next.nQueens);

                if (score >= nextScore) {
                    break;
                }

                record = next;
                score = nextScore;
            }

            if (record.nQueens.isSolved()) {
                return record.coords.toArray(new int[2][size]);
            }
        }

        return null;
    }
}

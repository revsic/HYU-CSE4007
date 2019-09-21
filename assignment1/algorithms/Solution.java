package assignment1.algorithms;


/**
 * N-Queens solutions for application.
 */
public interface Solution {
    /**
     * Solve the N-Queens problem.
     * @param size size of the board.
     * @return 2D coordinates array for placing queens.
     */
    public int[][] solve(int size);
}

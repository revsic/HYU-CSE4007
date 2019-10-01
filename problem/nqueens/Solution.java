package problem.nqueens;

/**
 * N-Queens solutions for application.
 */
public interface Solution {
    /**
     * Name of the solution.
     * @return name of the solution.
     */
    public String name();

    /**
     * Solve the N-Queens problem.
     * @param size size of the board.
     * @return 2D coordinates array for placing queens.
     */
    public int[][] solve(int size);
}

package assignment1.algorithms;

import problem.nqueens.Solution;


/**
 * N-Queens solver with DFS and iterative deepening.
 */
public class DFID implements Solution {
    /**
     * Name of the solution.
     * @return name of the solution.
     */
    @Override
    public String name() {
        return "DFID";
    }

    /**
     * Solve N-Queens problem.
     * @param size size of the board.
     * @return 2D coordinates array for queens if solution exist, else null. 
     */
    @Override
    public int[][] solve(int size) {
        for (int i = 1; i <= size; ++i) {
            // limiting depth
            Solution dfs = new DFS(i);

            // try to solve
            int[][] res = dfs.solve(size);
            // if solution exist
            if (res != null) {
                return res;
            }
        }
        return null;
    }
}
package assignment1.algorithms;


/**
 * N-Queens solver with DFS and iterative deepening.
 */
public class DFID implements Solution {
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
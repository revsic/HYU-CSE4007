package assignment1.algorithms;

import java.util.Stack;


/**
 * N-Queens solution with depth-first search.
 */
public class DFS implements Solution {
    private int maxdepth;

    /**
     * Construct new DFS solution.
     */
    public DFS() {
        this.maxdepth = Integer.MAX_VALUE;
    }

    /**
     * Construct DFS solution with limited depth.
     * @param maxdepth int, max depth in searching phase.
     */
    public DFS(int maxdepth) {
        this.maxdepth = maxdepth;
    }

    /**
     * Solve the N-Queens problem.
     * @param size int, size of the board.
     * @return 2D coordinates array for queens if solution exist, else null.
     */
    @Override
    public int[][] solve(int size) {
        Stack<Record> stack = new Stack<Record>();
        stack.push(Record.empty(size));

        // stack base iteration
        while (!stack.empty()) {
            Record record = stack.pop();
            // depth limited searching
            if (record.coords.size() > maxdepth
                || record.coords.size() == size)
            {
                if (record.nQueens.isSolved()) {
                    return record.coords.toArray(new int[2][size]);
                } else {
                    continue;
                }
            }

            // expanding tree
            int row = record.coords.size();
            for (int i = 0; i < size; ++i) {
                stack.push(record.addCoord(row, i));
            }
        }

        return null;
    }
}

package assignment1.algorithms;

import java.util.ArrayDeque;
import problem.nqueens.Solution;


/**
 * N-Queens problem solver with breadth-first search.
 */
public class BFS implements Solution {
    /**
     * Name of the solution.
     * @return name of the solution.
     */
    @Override
    public String name() {
        return "BFS";
    }

    /**
     * Solve N-Queens problem.
     * @param size int, size of the board.
     * @return 2D coordinates array for queens if solution exist, else null.
     */
    @Override
    public int[][] solve(int size) {
        ArrayDeque<Record> queue = new ArrayDeque<Record>();
        queue.push(Record.empty(size));

        // queue base iteration
        while (queue.size() > 0) {
            Record record = queue.removeFirst();
            if (record.coords.size() == size) {
                if (record.nQueens.isSolved()) {
                    return record.coords.toArray(new int[2][size]);
                } else {
                    continue;
                }
            }

            // expanding tree
            int row = record.coords.size();
            for (int i = 0; i < size; ++i) {
                queue.add(record.addCoord(row, i));
            }
        }

        return null;
    }
}

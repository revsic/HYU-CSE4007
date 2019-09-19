package assignment1.algorithms;

import java.util.ArrayList;
import java.util.ArrayDeque;
import assignment1.problem.NQueens;


public class BFS implements Solution {
    public int[][] solve(int size) {
        ArrayDeque<Record> queue = new ArrayDeque<Record>();
        queue.push(Record.empty(size));

        while (queue.size() > 0) {
            Record record = queue.removeFirst();
            if (record.coords.size() == size) {
                if (record.nQueens.isSolved()) {
                    return record.coords.toArray(new int[2][size]);
                } else {
                    continue;
                }
            }

            int row = record.coords.size();
            for (int i = 0; i < size; ++i) {
                queue.add(record.addCoord(row, i));
            }
        }

        return null;
    }
}

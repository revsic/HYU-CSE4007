package assignment1.algorithms;

import java.util.Stack;
import assignment1.algorithms.Record;


public class DFS implements Solution {
    public int[][] solve(int size) {
        Stack<Record> stack = new Stack<Record>();
        stack.push(Record.empty(size));

        while (!stack.empty()) {
            Record record = stack.pop();
            if (record.coords.size() == size) {
                if (record.nQueens.isSolved()) {
                    return record.coords.toArray(new int[2][size]);
                } else {
                    continue;
                }
            }

            int row = record.coords.size();
            for (int i = 0; i < size; ++i) {
                stack.push(record.addCoord(row, i));
            }
        }

        return null;
    }
}

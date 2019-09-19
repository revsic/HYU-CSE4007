package assignment1.algorithms;

import java.util.Stack;


public class DFS implements Solution {
    private int maxdepth;

    public DFS() {
        this.maxdepth = Integer.MAX_VALUE;
    }

    public DFS(int maxdepth) {
        this.maxdepth = maxdepth;
    }

    public int[][] solve(int size) {
        Stack<Record> stack = new Stack<Record>();
        stack.push(Record.empty(size));

        while (!stack.empty()) {
            Record record = stack.pop();
            if (record.coords.size() > maxdepth
                || record.coords.size() == size)
            {
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

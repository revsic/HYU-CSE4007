package assignment1.algorithms;

import java.util.ArrayList;
import java.util.Stack;
import assignment1.problem.NQueens;


public class DFS implements Solution {
    public int[][] solve(int size) {
        Stack<Record> stack = new Stack<Record>();

        while (!stack.empty()) {
            Record record = stack.pop();
            if (record.coords.size() == size && record.nQueens.isSolved()) {
                return record.coords.toArray(new int[2][size]);
            }

            int row = record.coords.size();
            for (int i = 0; i < size; ++i) {
                stack.push(record.addCoord(i, row));
            }
        }

        return null;
    }

    static class Record {
        NQueens nQueens;
        ArrayList<int[]> coords;

        static Record empty(int size) {
            return new Record(new NQueens(size), new ArrayList<int[]>());
        }

        Record(NQueens nQueens, ArrayList<int[]> coords) {
            this.nQueens = nQueens;
            this.coords = coords;
        }

        Record addCoord(int x, int y) {
            NQueens queens = nQueens.clone();
            queens.setPos(x, y);

            ArrayList<int[]> coordinates = new ArrayList<int[]>(coords);
            coordinates.add(new int[]{ x, y });

            return new Record(queens, coordinates);
        }
    }
}

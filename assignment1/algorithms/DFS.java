package assignment1.algorithms;

import java.util.ArrayList;
import assignment1.problem.NQueens;


public class DFS implements Solution {
    public int[][] solve(int size) {
        return recursion(size, new ArrayList<int[]>(), new NQueens(size));
    }

    public int[][] recursion(int size, ArrayList<int[]> coords, NQueens nQueens) {
        if (coords.size() == size) {
            if (nQueens.isSolved()) {
                return coords.toArray(new int[2][size]);
            } else {
                return null;
            }
        }

        int row = coords.size();
        for (int i = 0; i < size; ++i) {
            NQueens clonedQueens = nQueens.clone();
            clonedQueens.setPos(row, i);

            ArrayList<int[]> clonedCoords = cloneCoordinates(coords);
            
            int[] pos = { row, i };
            clonedCoords.add(pos);

            int[][] res = recursion(size, clonedCoords, clonedQueens);
            if (res != null) {
                return res;
            }
        }

        return null;
    }

    private ArrayList<int[]> cloneCoordinates(ArrayList<int[]> coords) {
        ArrayList<int[]> cloned = new ArrayList<int[]>(coords.size());
        for (int i = 0; i < coords.size(); ++i) {
            int[] pos = coords.get(i);
            int[] clonedPos = { pos[0], pos[1] };
            cloned.add(clonedPos);
        }
        return cloned;
    }
}

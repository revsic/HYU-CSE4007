package assignment1.algorithms;

import java.util.ArrayList;
import assignment1.problem.NQueens;


public class Record {
    public NQueens nQueens;
    public ArrayList<int[]> coords;

    public static Record empty(int size) {
        return new Record(new NQueens(size), new ArrayList<int[]>());
    }

    public Record(NQueens nQueens, ArrayList<int[]> coords) {
        this.nQueens = nQueens;
        this.coords = coords;
    }

    public Record addCoord(int x, int y) {
        NQueens queens = nQueens.clone();
        queens.setPos(x, y);

        ArrayList<int[]> coordinates = new ArrayList<int[]>(coords);
        coordinates.add(new int[]{ x, y });

        return new Record(queens, coordinates);
    }
}

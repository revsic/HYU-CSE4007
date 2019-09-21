package assignment1.algorithms;

import java.util.ArrayList;
import assignment1.problem.NQueens;


/**
 * Node record for searching methods.
 */
public class Record {
    public NQueens nQueens;
    public ArrayList<int[]> coords;

    /**
     * Return empty record.
     * @param size size of the board.
     * @return Record, empty record.
     */
    public static Record empty(int size) {
        return new Record(new NQueens(size), new ArrayList<int[]>());
    }

    /**
     * Construct new record with board and positions of queens.
     * @param nQueens N-Queens problem environment.
     * @param coords 2D coordinates array.
     */
    public Record(NQueens nQueens, ArrayList<int[]> coords) {
        this.nQueens = nQueens;
        this.coords = coords;
    }

    /**
     * Generate new record with adding given coordinates.
     * @param x coordinate value for x-axis.
     * @param y coordinate value for y-axis.
     * @return new record.
     */
    public Record addCoord(int x, int y) {
        NQueens queens = nQueens.clone();
        queens.setPos(x, y);

        ArrayList<int[]> coordinates = new ArrayList<int[]>(coords);
        coordinates.add(new int[]{ x, y });

        return new Record(queens, coordinates);
    }
}

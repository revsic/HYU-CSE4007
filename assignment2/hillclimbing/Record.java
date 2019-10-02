package assignment2.hillclimbing;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import problem.nqueens.NQueens;


/**
 * Record for hill climbing search.
 */
public class Record {
    public NQueens nQueens;
    public ArrayList<int[]> coords;

    /**
     * Create new record with given board and coordinates.
     * @param nQueens N-Queens environment.
     * @param coords 2D coordinates.
     */
    public Record(NQueens nQueens, ArrayList<int[]> coords) {
        this.nQueens = nQueens;
        this.coords = coords;
    }

    /**
     * Generate neighbor sequence with delta values.
     * @return Record sequence of neighbors.
     */
    public ArrayList<Record> neighbor() {
        final int[][] deltas = {
            { -1,  1 }, { 0,  1 }, { 1,  1 },
            { -1,  0 },            { 1,  0 },
            { -1, -1 }, { 0, -1 }, { 1, -1 },
        };

        int idx = 0;
        int size = nQueens.getSize();
        int numNeighbors = size * deltas.length;
        ArrayList<Record> records = new ArrayList<Record>(numNeighbors);
        for (int[] pos : coords) {
            for (int[] delta : deltas) {
                // generate neighbor positions
                int[] newPos = { pos[0] + delta[0], pos[1] + delta[1] };
                // coordinate validation
                if (newPos[0] < 0 || newPos[0] >= size
                    || newPos[1] < 0 || newPos[1] >= size) {
                    continue;
                }

                // set queens
                NQueens queens = nQueens.clone();
                queens.relasePos(pos[0], pos[1]);
                queens.setPos(newPos[0], newPos[1]);

                // update coordinates
                ArrayList<int[]> newCoords = new ArrayList<int[]>(coords);
                newCoords.set(idx, newPos);

                // add new records
                records.add(new Record(queens, newCoords));
            }
            idx += 1;
        }
        return records;
    }

    /**
     * Randomize positions of queens.
     * @param size size of the board.
     * @return record with randomized position.
     */
    public static Record random(int size) {
        NQueens queens = new NQueens(size);
        ArrayList<int[]> coords = new ArrayList<int[]>(size);

        Random random = new Random();
        for (int i = 0; i < size; ++i) {
            int[] pos = null;
            boolean exist = true;

            while (exist) {
                // new coordinates
                int x = random.nextInt(size);
                int y = random.nextInt(size);
                pos = new int[] { x, y };
                final int[] finalized = pos;
                // check existance
                exist = coords.stream().anyMatch(a -> Arrays.equals(finalized, a));
            }
            // add coordinates
            coords.add(pos);
            queens.setPos(pos[0], pos[1]);
        }

        // return randomized envrionment
        return new Record(queens, coords);
    }
}

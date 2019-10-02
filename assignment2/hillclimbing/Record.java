package assignment2.hillclimbing;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import problem.nqueens.NQueens;


public class Record {
    public NQueens nQueens;
    public ArrayList<int[]> coords;

    public Record(NQueens nQueens, ArrayList<int[]> coords) {
        this.nQueens = nQueens;
        this.coords = coords;
    }

    public Record[] neighbor() {
        final int[][] deltas = {
            { -1,  1 }, { 0,  1 }, { 1,  1 },
            { -1,  0 },            { 1,  0 },
            { -1, -1 }, { 0, -1 }, { 1, -1 },
        };

        int idx = 0;
        int num = 0;
        int size = nQueens.getSize();
        int numNeighbors = size * deltas.length;
        ArrayList<Record> records = new ArrayList<Record>(numNeighbors);
        for (int[] pos : coords) {
            for (int[] delta : deltas) {
                int[] newPos = { pos[0] + delta[0], pos[1] + delta[1] };
                if (newPos[0] < 0 || newPos[0] >= size
                    || newPos[1] < 0 || newPos[1] >= size) {
                    continue;
                }

                num += 1;
                NQueens queens = nQueens.clone();
                queens.relasePos(pos[0], pos[1]);
                queens.setPos(newPos[0], newPos[1]);

                ArrayList<int[]> newCoords = new ArrayList<int[]>(coords);
                newCoords.set(idx, newPos);

                records.add(new Record(queens, newCoords));
            }
            idx += 1;
        }

        return records.toArray(new Record[num]);
    }

    public static Record random(int size) {
        NQueens queens = new NQueens(size);
        ArrayList<int[]> coords = new ArrayList<int[]>(size);

        Random random = new Random();
        for (int i = 0; i < size; ++i) {
            int[] pos = null;
            boolean exist = true;

            while (exist) {
                int x = random.nextInt(size);
                int y = random.nextInt(size);
                pos = new int[] { x, y };
                final int[] finalized = pos;
                exist = coords.stream().anyMatch(a -> Arrays.equals(finalized, a));
            }
            coords.add(pos);
        }

        return new Record(queens, coords);
    }
}

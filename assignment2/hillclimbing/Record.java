package assignment2.hillclimbing;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import problem.nqueens.NQueens;


public class Record {
    public NQueens nQueens;
    public ArrayList<int[]> coords;
    public Objective objective;

    public Record(NQueens nQueens, ArrayList<int[]> coords, Objective objective) {
        this.nQueens = nQueens;
        this.coords = coords;
        this.objective = objective;
    }

    public Record[] neighbor() {
        final int[][] deltas = {
            { -1,  1 }, { 0,  1 }, { 1,  1 },
            { -1,  0 },          , { 1,  0 },
            { -1, -1 }, { 0, -1 }, { 1, -1 },
        }

        int idx = 0;
        Record[] records = new Record[nQueens.getSize() * deltas.length];
        for (int[] pos : coords) {
            for (int[] delta : deltas) {
                NQueens queens = nQueens.clone();
                queens.relasePos(pos[0], pos[1]);
                queens.setPos(pos[0] + delta[0], pos[1] + delta[1]);

                ArrayList<int[]> newCoords = coords.clone();

                records[idx] = new Record(quuens, newCoords, objective);
                idx += 1;
            }
        }

        return records;
    }

    public Record highestNeighbor() {
        double score = Double.MIN_VALUE;
        Record highest = null;
        for (Record record : neighbor()) {
            double res = objective.score(record.nQueens);
            if (res > score) {
                score = res;
                highest = record;
            }
        }
        return highest;
    }

    public static Record random(int size, Objective objective) {
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
                exist = coords.stream().anyMatch(a -> Arrays.equals(pos, a));
            }
            coords.add(pos);
        }

        return new Record(queens, coords, objective);
    }
}

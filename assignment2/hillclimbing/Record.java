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
        
        return null;
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

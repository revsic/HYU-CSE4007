package assignment2.hillclimbing;

import problem.nqueens.NQueens;


/**
 * Define default objective.
 */
public abstract class DefaultObjective implements Policy {
    /**
     * Compute default objectives from N-Queens environment.
     * @param queens N-Queens environment.
     * @return objective value.
     */
    @Override
    public double objective(NQueens nQueens) {
        int size = nQueens.getSize();

        int[] columns = new int[size];
        int[] rows = new int[size];
        
        int[] rightdown = new int[size * 2 - 1];
        int[] leftdown = new int[size * 2 - 1];

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (nQueens.getPos(i, j)) {
                    // number of queens in each rows
                    rows[i] += 1;
                    // number of queens in each columns
                    columns[j] += 1;

                    // right-down direction cross line validation
                    if (i >= j) {
                        rightdown[i - j] += 1;
                    } else {
                        rightdown[size + j - i - 1] += 1;
                    }

                    // left-down direction cross line validation
                    if (size - i - 1 >= j) {
                        leftdown[size - i - j - 1] += 1;
                    } else {
                        leftdown[i + j] += 1;
                    }
                }
            }
        }

        int score = 0;
        for (int i = 0; i < size; ++i) {
            score += Math.abs(columns[i] - 1) + Math.abs(rows[i] - 1);
        }
        for (int i = 0; i < size * 2 - 1; ++i) {
            score += Math.max(leftdown[i] - 1, 0) + Math.max(rightdown[i] - 1, 0);
        }
        return 10 - score;
    }

    /**
     * Get next records from neighbor sequence.
     * @param current current record.
     * @return next record.
     */
    public abstract Record next(Record record);
}

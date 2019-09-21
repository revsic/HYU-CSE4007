package assignment1.problem;


/**
 * Environment definition for solving N-Queens problem.
 */
public class NQueens {
    private int size;

    private boolean[][] matrix;

    /**
     * Construct default N-Queens environment with board size zero.
     */
    public NQueens() {
        this(0);
    }

    /**
     * Construct N-Queens environment with given board size.
     * @param size int, size of the board.
     */
    public NQueens(int size) {
        this.size = size;
        // null-size validation
        if (size > 0) {
            matrix = new boolean[size][size];
        }
    }

    /**
     * Clone N-Queens environment without any modification.
     * @return cloned N-Queens environment.
     */
    public NQueens clone() {
        NQueens queens = new NQueens(size);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                // copying values
                queens.matrix[i][j] = matrix[i][j];
            }
        }
        return queens;
    }

    /**
     * Set queens in given coordinate.
     * @param x coordinate value for x-axis.
     * @param y coordinate value for y-axis.
     * @exception java.lang.ArrayIndexOutOfBoundsException
     *  if x and y coordinates are either out of board size.
     */
    public void setPos(int x, int y) {
        if (matrix != null) {
            matrix[y][x] = true;
        }
    }

    /**
     * Return whether given position is occupied.
     * @param x coordinate value for x-axis.
     * @param y coordinate value for y-axis.
     * @return whether given position has queens.
     */
    public boolean getPos(int x, int y) {
        return matrix[y][x];
    }

    /**
     * Return whether this board solves the N-Queens problem.
     * @return true if problem is solved else false.
     */
    public boolean isSolved() {
        int[] columns = new int[size];
        int[] rows = new int[size];
        
        int[] rightdown = new int[size * 2 - 1];
        int[] leftdown = new int[size * 2 - 1];

        int num = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (matrix[i][j]) {
                    // number of queens
                    num += 1;

                    // number of queens in each columns
                    columns[j] += 1;
                    // number of queens in each rows
                    rows[i] += 1;

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

        if (num != size) {
            return false;
        }

        for (int i = 0; i < size; ++i) {
            if (columns[i] != 1 || rows[i] != 1) {
                return false;
            }
        }

        for (int i = 0; i < size * 2 - 1; ++i) {
            if (leftdown[i] > 1 || rightdown[i] > 1) {
                return false;
            }
        }

        return true;
    }
}

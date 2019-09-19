package assignment1.problem;


public class NQueens {
    private int size;

    private boolean[][] matrix;

    public NQueens() {
        this(0);
    }

    public NQueens(int size) {
        this.size = size;
        if (size > 0) {
            matrix = new boolean[size][size];
        }
    }

    public NQueens clone() {
        NQueens queens = new NQueens(size);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                queens.matrix[i][j] = matrix[i][j];
            }
        }
        return queens;
    }

    public void setPos(int x, int y) {
        if (matrix != null) {
            matrix[y][x] = true;
        }
    }

    public boolean getPos(int x, int y) {
        return matrix[y][x];
    }

    public boolean isSolved() {
        int[] columns = new int[size];
        int[] rows = new int[size];
        
        int[] rightdown = new int[size * 2 - 1];
        int[] leftdown = new int[size * 2 - 1];

        int num = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (matrix[i][j]) {
                    num += 1;

                    columns[j] += 1;
                    rows[i] += 1;

                    if (i >= j) {
                        rightdown[i - j] += 1;
                    } else {
                        rightdown[size + j - i - 1] += 1;
                    }

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

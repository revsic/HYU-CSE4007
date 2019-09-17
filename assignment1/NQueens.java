public class NQueens {
    private int size;

    private bool[][] matrix;

    public NQueens() {
        this(0);
    }

    public NQueens(int size) {
        this.size = size;
        if (size > 0) {
            matrix = new bool[size][size];
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

    public bool isSolved() {
        int[] columns = new int[size];
        int[] rows = new int[size];
        
        int[] rightdown = new int[size * 2 - 1];
        int[] leftdown = new int[size * 2 - 1];

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (matrix[i][j]) {
                    columns[j] += 1;
                    rows[i] += 1;

                    if (i >= j) {
                        rightdown[i - j] += 1;
                    } else {
                        rightdown[size + j - i] += 1;
                    }

                    if (size - i - 1 >= j) {
                        leftdown[size - i - j - 1] += 1;
                    } else {
                        leftdown[i + j] += 1;
                    }
                }
            }
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
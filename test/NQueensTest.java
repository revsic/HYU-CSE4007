package test;

import java.util.Arrays;
import java.util.stream.Stream;

import problem.nqueens.NQueens;


/**
 * Test suit for NQueens class.
 */
public class NQueensTest implements Testable {
    /**
     * Implement test method for Testable interface.
     */
    @Override
    public boolean test() {
        return positionSetterGetterTest()
            && getBoardTest()
            && cloneTest()
            && isSolvedTest();
    }

    /**
     * Compare the position lists with real N-Queens environment.
     * @param posList 2D coordinates array where queen exists.
     * @param queens N-Queens environment.
     * @param msgbase head of failure message.
     * @return
     */
    private boolean positionTest(int[][] posList, NQueens queens, String msgbase) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                final int[] pos = { i, j };
                boolean value = queens.getPos(i, j);
                boolean existance = Stream.of(posList)
                                          .anyMatch(x -> Arrays.equals(pos, x));
                if (value != existance) {
                    System.out.println(msgbase + 
                                       ": i=" + i + 
                                       ", j=" + j +
                                       ", value=" + value + 
                                       ", existance=" + existance);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Test position setter and getter of N-Queens enviornment.
     * @return whether test success.
     */
    private boolean positionSetterGetterTest() {
        // sample position list
        int[][] positionList = { { 1, 1 }, { 3, 2 } };

        // set queens
        NQueens nQueens = new NQueens(4);
        for (int[] pos : positionList) {
            nQueens.setPos(pos[0], pos[1]);
        }

        // check queens are placed properly.
        return positionTest(positionList, nQueens, "setPos, getPos failure");
    }

    /**
     * Test getBoard method of NQueens.
     * @return whether test success.
     */
    private boolean getBoardTest() {
        int[][] positionList = { { 1, 1 }, { 3, 2 } };

        NQueens nQueens = new NQueens(4);
        for (int[] pos : positionList) {
            nQueens.setPos(pos[0], pos[1]);
        }

        boolean[][] board = nQueens.getBoard();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (board[j][i] != nQueens.getPos(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Test clone method.
     * @return whether test success.
     */
    private boolean cloneTest() {
        // sample coordinates
        int[][] positionList = { { 1, 1 }, { 3, 2 } };

        // set queens
        NQueens nQueens = new NQueens(4);
        for (int[] pos : positionList) {
            nQueens.setPos(pos[0], pos[1]);
        }

        // clone and check the queens.
        NQueens cloned = nQueens.clone();
        return positionTest(positionList, cloned, "clone failure");
    }

    /**
     * Set queens with given coordinates and return is problem solved.
     * @param coords coordinates of queens.
     * @return whether problem is solved.
     */
    private boolean caseTest(int[][] coords) {
        NQueens nQueens = new NQueens(4);
        for (int[] pos : coords) {
            nQueens.setPos(pos[0], pos[1]);
        } 
        return nQueens.isSolved();
    }

    private boolean isSolvedTest() {
        // failure case
        int[][][] failed = {
            { { 0, 1 }, { 1, 3 }, { 2, 0 } }, // number of queens
            { { 0, 0 }, { 1, 3 }, { 2, 0 }, { 3, 2 } }, // 1st row
            { { 0, 0 }, { 1, 3 }, { 2, 3 }, { 3, 1 } }, // 4th row
            { { 0, 0 }, { 3, 1 }, { 0, 2 }, { 2, 3 } }, // 1st column
            { { 2, 0 }, { 0, 1 }, { 2, 2 }, { 1, 3 } }, // 3rd column + lowerhalf leftdown
            { { 0, 1 }, { 1, 2 }, { 2, 2 }, { 3, 0 } }, // 3rd row + lowerhalf rightdown
            { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 3, 3 } }, // lowerhalf rightdown
            { { 0, 2 }, { 1, 0 }, { 2, 1 }, { 3, 3 } }, // upperhalf rightdown
            { { 0, 3 }, { 1, 1 }, { 2, 0 }, { 3, 2 } }, // upperhalf leftdown
            { { 0, 3 }, { 1, 0 }, { 2, 2 }, { 3, 1 } }, // lowerhalf leftdown
        };

        // success case
        int[][][] success = {
            { { 0, 1 }, { 1, 3 }, { 2, 0 }, { 3, 2 } },
            { { 0, 2 }, { 1, 0 }, { 2, 3 }, { 3, 1 } },
        };

        // test failure case
        for (int[][] f : failed) {
            if (caseTest(f)) {
                System.out.println("isSolved failure: false case, " + f);
                return false;
            }
        }

        // test success case
        for (int[][] s : success) {
            if (!caseTest(s)) {
                System.out.println("isSolved failure: true case, " + s);
                return false;
            }
        }

        return true;
    }
}

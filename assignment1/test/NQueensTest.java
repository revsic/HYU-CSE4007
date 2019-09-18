package assignment1.test;

import java.util.Arrays;
import java.util.stream.Stream;

import assignment1.problem.NQueens;


public class NQueensTest implements Testable {
    @Override
    public boolean test() {
        return positionSetterGetterTest()
            && cloneTest()
            && isSolvedTest();
    }

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

    private boolean positionSetterGetterTest() {
        int[][] positionList = { { 1, 1 }, { 3, 2 } };

        NQueens nQueens = new NQueens(4);
        for (int i = 0; i < positionList.length; ++i) {
            int[] pos = positionList[i];
            nQueens.setPos(pos[0], pos[1]);
        }

        return positionTest(positionList, nQueens, "setPos, getPos failure");
    }

    private boolean cloneTest() {
        int[][] positionList = { { 1, 1 }, { 3, 2 } };

        NQueens nQueens = new NQueens(4);
        for (int i = 0; i < positionList.length; ++i) {
            int[] pos = positionList[i];
            nQueens.setPos(pos[0], pos[1]);
        }

        NQueens cloned = nQueens.clone();
        return positionTest(positionList, cloned, "clone failure");
    }

    private boolean caseTest(int[][] coords) {
        NQueens nQueens = new NQueens(4);
        for (int i = 0; i < coords.length; ++i) {
            int[] pos = coords[i];
            nQueens.setPos(pos[0], pos[1]);
        } 
        return nQueens.isSolved();
    }

    private boolean isSolvedTest() {
        int[][][] failed = {
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

        int[][][] success = {
            { { 0, 1 }, { 1, 3 }, { 2, 0 }, { 3, 2 } },
            { { 0, 2 }, { 1, 0 }, { 2, 3 }, { 3, 1 } },
        };

        for (int i = 0; i < failed.length; ++i) {
            if (caseTest(failed[i])) {
                System.out.println("isSolved failure: false case, " + failed[i]);
                return false;
            }
        }

        for (int i = 0; i < success.length; ++i) {
            if (!caseTest(success[i])) {
                System.out.println("isSolved failure: true case, " + success[i]);
            }
        }

        return true;
    }
}

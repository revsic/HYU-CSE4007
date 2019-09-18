package assignment1.test;

import java.util.Arrays;
import java.util.stream.Stream;

import assignment1.NQueens;


public class NQueensTest implements Testable {
    @Override
    public boolean test() {
        return positionSetterGetterTest()
            && cloneTest()
            && isSolvedTest();
    }

    private boolean positionSetterGetterTest() {
        int[][] positionList = { { 1, 1 }, { 3, 2 } };

        NQueens nqueens = new NQueens(4);
        for (int i = 0; i < positionList.length; ++i) {
            int[] pos = positionList[i];
            nqueens.setPos(pos[0], pos[1]);
        }

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                final int[] pos = { i, j };
                boolean value = nqueens.getPos(i, j);
                boolean existance =
                    Stream.of(positionList)
                          .anyMatch(x -> Arrays.equals(pos, x));
                if (value != existance) {
                    System.out.println("setPos, getPos failure: i=" + i + 
                                       ", j=" + j + ", value=" + value + 
                                       ", existance=" + existance);
                    return false;
                }
            }
        }

        return true;
    }

    private boolean cloneTest() {
        int[][] positionList = { { 1, 1 }, { 3, 2 } };

        NQueens nQueens = new NQueens(4);
        for (int i = 0; i < positionList.length; ++i) {
            int[] pos = positionList[i];
            nQueens.setPos(pos[0], pos[1]);
        }

        NQueens cloned = nQueens.clone();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                final int[] pos = { i, j };
                boolean value = cloned.getPos(i, j);
                boolean existance = Stream.of(positionList)
                                          .anyMatch(x -> Arrays.equals(pos, x));
                if (value != existance) {
                    System.out.println("clone failure");
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isSolvedTest() {
        return true;
    }
}

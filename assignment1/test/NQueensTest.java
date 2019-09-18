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
        NQueens nqueens = new NQueens(4);
        
        nqueens.setPos(1, 1);
        nqueens.setPos(3, 2);

        int[][] positionList = {
            { 1, 1 },
            { 3, 2 }
        };

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
        return true;
    }

    private boolean isSolvedTest() {
        return true;
    }
}

package assignment1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import assignment1.algorithms.DFS;
import assignment1.algorithms.Solution;


public class Application {
    public static String solve(Solution solution, int size) {
        int[][] res = solution.solve(size);
        if (res == null) {
            return "No solution";
        }

        Arrays.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(int[] c1, int[] c2) {
                return c1[0] - c2[0];
            }
        });

        return Stream.of(res)
            .map(x -> String.valueOf(x[1]))
            .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        System.out.println(solve(new DFS(), 5));
    }
}

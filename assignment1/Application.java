package assignment1;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import assignment1.algorithms.DFS;


public class Application {
    public static void main(String[] args) {
        int[][] res = new DFS().solve(4);
        String rows = Stream.of(res)
            .map(x -> String.valueOf(x[1]))
            .collect(Collectors.joining(" "));

        System.out.println(rows);
    }
}

package assignment2;

import java.util.stream.Stream;
import assignment2.hillclimbing.HighestNeighbor;
import assignment2.hillclimbing.HillClimbing;
import problem.nqueens.App;


/**
 * Application for showing assignment2 solutions of N-Queens problem.
 */
public class Application {
    /**
     * Main application, run hill climbing method with given board size and log path.
     * @param args command line argument, board size and log path.
     * @exception java.lang.NumberFormatException exception can occur in Integer.parseInt.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: [size of board: INT] [path: STRING]");
            return;
        }

        int size = Integer.parseInt(args[0]);
        String path = args[1];

        final int maxRetry = Integer.MAX_VALUE;
        App.run(new HillClimbing(maxRetry, new HighestNeighbor()), size, path);
        // experiment();
    }

    public static void experiment() {
        HillClimbing sol = new HillClimbing(Integer.MAX_VALUE, new HighestNeighbor());
        for (int i = 4; i <= 10; ++i) {
            App.Info[] res = App.experiment(sol, i, 100, 10);
            Double[] times = Stream.of(res)
                                   .map(x -> x.elapsed)
                                   .filter(x -> x != 0.0)
                                   .toArray(Double[]::new);
            double mean = Stream.of(times).reduce(0.0, Double::sum) / times.length;
            System.out.println(i + " " + times.length + " " + mean);
        }
    }
}

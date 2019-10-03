package assignment2;

import java.io.File;
import java.io.FileOutputStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;
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

    /**
     * Experiment and check performance for hill climbing approach.
     */
    public static void experiment() {
        String log = "";

        HillClimbing sol = new HillClimbing(Integer.MAX_VALUE, new HighestNeighbor());
        // iterating with board size i
        for (int i = 4; i <= 10; ++i) {
            // experiment
            App.Info[] res = App.experiment(sol, i, 100, 10);
            // get running time
            Double[] times = Stream.of(res)
                                   .map(x -> x.elapsed)
                                   .filter(x -> x != 0.0)
                                   .toArray(Double[]::new);
            // compute mean
            double mean = Stream.of(times).reduce(0.0, Double::sum) / times.length;
            System.out.println(i + " " + times.length + " " + mean);

            // write log
            log += i + " | " 
                + Stream.of(times)
                        .map(x -> String.valueOf(x))
                        .collect(Collectors.joining(" | "))
                + " |\n";
        }
        // write log as file
        FileOutputStream stream;
        try {
            stream = new FileOutputStream("../log.txt");
            stream.write(log.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

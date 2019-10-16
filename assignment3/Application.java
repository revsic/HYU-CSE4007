package assignment3;

import java.io.FileOutputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import assignment3.NQueensSolver;
import problem.nqueens.App;


/**
 * Application for showing assignment3 solutions of N-Queens problem.
 */
public class Application {
    /**
     * Main application, run genetic algorithm with given board size and log path.
     * @param args command line argument, board size and log path.
     * @exception java.lang.NumberFormatException exception can occur in Integer.parseInt.
     */
    public static void main(String[] args) {
        // if (args.length < 2) {
        //     System.out.println("usage: [size of board: INT] [path: STRING]");
        //     return;
        // }

        // int size = Integer.parseInt(args[0]);
        // String path = args[1];

        // NQueensSolver.Param param = new NQueensSolver.Param(7, 5000, 1000, 4000, 0);
        // App.run(new NQueensSolver(size, param, 500), size, path);
        experiment();
    }

    /**
     * Experiment and check performance for genetic solver.
     */
    public static void experiment() {
        String log = "";

        NQueensSolver.Param param = new NQueensSolver.Param(7, 5000, 1000, 4000, 0);
        // iterating with board size i
        for (int i = 4; i <= 10; ++i) {
            // experiment
            NQueensSolver sol = new NQueensSolver(i, param, 50);
            App.Info[] res = App.experiment(sol, i, 100, 10);
            // get running time
            Double[] times = Stream.of(res)
                                   .map(x -> x.elapsed)
                                   .filter(x -> x != 0.0)
                                   .toArray(Double[]::new);
            // compute mean
            double timesMean =
                Stream.of(times).reduce(0.0, Double::sum) / times.length;

            // get number of trying
            Integer[] ntries = sol.meta.stream()
                                       .filter(x -> x.solved)
                                       .map(x -> x.ntry)
                                       .toArray(Integer[]::new);
            double ntriesMean =
                (double)Stream.of(ntries).reduce(0, Integer::sum) / ntries.length;

            System.out.println(
                i + " " + times.length
                + " " + timesMean
                + " " + ntriesMean);

            // write log
            log += "| " + i + " times | " 
                + Stream.of(times)
                        .map(x -> String.valueOf(x))
                        .collect(Collectors.joining(" | "))
                + " |\n"
                + "| " + i + " try | "
                + Stream.of(ntries)
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

package assignment3;

import assignment3.NQueensSolver;
import problem.nqueens.App;


/**
 * Application for showing assignment2 solutions of N-Queens problem.
 */
public class Application {
    /**
     * Main application, run genetic algorithm with given board size and log path.
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

        App.run(new NQueensSolver(size), size, path);
    }
}

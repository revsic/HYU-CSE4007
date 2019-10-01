package assignment1;

import assignment1.algorithms.BFS;
import assignment1.algorithms.DFID;
import assignment1.algorithms.DFS;
import problem.nqueens.App;
import problem.nqueens.Solution;


/**
 * Application for showing assignment1 solutions of N-Queens problem.
 */
public class Application {
    /**
     * Main application, run BFS, DFS, DFID with given board size and log path.
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

        App.run(new Solution[]{ new DFS(), new BFS(), new DFID() }, size, path);
    }
}
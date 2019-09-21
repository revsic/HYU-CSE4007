package assignment1;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import assignment1.algorithms.BFS;
import assignment1.algorithms.DFID;
import assignment1.algorithms.DFS;
import assignment1.algorithms.Solution;


/**
 * Java application for solving N-Queens problem with several searching methods.
 */
public class Application {
    /**
     * Main method, pass size of board and path to write the log file.
     * Then main method will write the log about which is solvable,
     * what is the first answer they found, how long do they take.
     * @param args Command line arguments.
     * @exception java.lang.NumberFormatException, if first argument cannot casted to integers.
     */
    public static void main(String[] args) {
        // argument length validation.
        if (args.length < 2) {
            System.out.println("usage: [size of board: INT] [path: STRING]");
            return;
        }


        // solve with several methods.
        int size = Integer.parseInt(args[0]);
        String msg = 
            ">DFS\n" + solve(new DFS(), size) + "\n\n" +
            ">BFS\n" + solve(new BFS(), size) + "\n\n" +
            ">DFID\n" + solve(new DFID(), size) + "\n";

        // file io
        String path = args[1];
        try {
            File file = new File(path, "result" + size + ".txt");
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(msg.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Solve with interface `solution`.
     * @param solution N-Queens solvable methods.
     * @param size the size of the board.
     * @return Solved information.
     */
    public static Info solve(Solution solution, int size) {
        // check time elapsing
        long start = System.currentTimeMillis();
        // solve
        int[][] res = solution.solve(size);
        long end = System.currentTimeMillis();

        // if solution method cannot solve the problem.
        if (res == null || res.length != size) {
            return new Info("No solution", 0);
        }

        // sort the result in x-axis.
        Arrays.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(int[] c1, int[] c2) {
                return c1[0] - c2[0];
            }
        });

        // make message string.
        String msg = Stream.of(res)
            .map(x -> String.valueOf(x[1]))
            .collect(Collectors.joining(" "));
        return new Info(msg, (end - start) / 1000.0);
    }
    
    /**
     * Inclass static class for solved result.
     */
    static class Info {
        String msg;
        double elapsed;

        /**
         * Construct information structure.
         * @param msg Message string.
         * @param elapsed elapsed times in sec unit.
         */
        Info(String msg, double elapsed) {
            this.msg = msg;
            this.elapsed = elapsed;
        }

        @Override
        public String toString() {
            return "Location : " + msg + "\nTime : " + elapsed;
        }
    }
}

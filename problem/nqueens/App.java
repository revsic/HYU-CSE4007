package problem.nqueens;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Java application for solving N-Queens problem with several searching methods.
 */
public class App {
    /**
     * Run NQueens application, pass size of board and path to write the log file.
     * Then run method will write the log about which is solvable,
     * what is the first answer they found, how long do they take.
     * @param solvers NQueens solutions.
     * @param boardSize size of the board.
     * @param path path to write the log file.
     */
    public static void run(Solution[] solvers, int boardSize, String path) {
        String msg = "";
        for (Solution sol : solvers) {
            msg += '>' + sol.name() + '\n' + solve(sol, boardSize) + "\n\n";
        }

        if (path != null) {
            try {
                File file = new File(path, "result" + boardSize + ".txt");
                FileOutputStream stream = new FileOutputStream(file);
                stream.write(msg.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Alias of method run for single solution argument.
     * @param solver N-Queens solution.
     * @param boardSize size of the board.
     * @param path path to write the log file.
     */
    public static void run(Solution solver, int boardSize, String path) {
        run(new Solution[]{ solver }, boardSize, path);
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
            return new Info("No solution", 0, null);
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
        return new Info(msg, (end - start) / 1000.0, res);
    }

    /**
     * Solve N-Queens problem in multiple thread.
     * @param solution N-Queens solvable methods.
     * @param size size of the board.
     * @param numExpr the number of experiment.
     * @param numThread the number of thread.
     */
    public static Info[] experiment(Solution solution, int size, int numExpr, int numThread) {
        int batch = numExpr / numThread;        
        Info[] info = new Info[batch * numThread];
        Thread[] threads = new Thread[numThread];

        for (int i = 0; i < numThread; ++i) {
            final int threadId = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < batch; ++j) {
                        info[threadId * batch + j] = solve(solution, size);
                    }
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThread; ++i) {
            try {
                threads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return info;
    }
    
    /**
     * Inclass static class for solved result.
     */
    public static class Info {
        public String msg;
        public double elapsed;
        public int[][] solution;

        /**
         * Construct information structure.
         * @param msg Message string.
         * @param elapsed elapsed times in sec unit.
         * @param solution coordinate array.
         */
        Info(String msg, double elapsed, int[][] solution) {
            this.msg = msg;
            this.elapsed = elapsed;
            this.solution = solution;
        }

        @Override
        public String toString() {
            return "Location : " + msg + "\nTime : " + elapsed;
        }
    }
}

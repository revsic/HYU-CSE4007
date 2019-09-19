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


public class Application {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: [size of board: INT] [path: STRING]");
            return;
        }

        int size = Integer.parseInt(args[0]);
        String msg = 
            ">DFS\n" + solve(new DFS(), size) + "\n\n" +
            ">BFS\n" + solve(new BFS(), size) + "\n\n" +
            ">DFID\n" + solve(new DFID(), size) + "\n";

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

    public static Info solve(Solution solution, int size) {
        long start = System.currentTimeMillis();
        int[][] res = solution.solve(size);
        long end = System.currentTimeMillis();

        if (res == null || res.length != size) {
            return new Info("No solution", 0);
        }

        Arrays.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(int[] c1, int[] c2) {
                return c1[0] - c2[0];
            }
        });

        String msg = Stream.of(res)
            .map(x -> String.valueOf(x[1]))
            .collect(Collectors.joining(" "));
        return new Info(msg, (end - start) / 1000.0);
    }
    
    static class Info {
        String msg;
        double elapsed;

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

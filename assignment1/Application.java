package assignment1;

import assignment1.algorithms.BFS;
import assignment1.algorithms.DFID;
import assignment1.algorithms.DFS;
import problem.nqueens.App;
import problem.nqueens.Solution;


public class Application {
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
package assignment1.algorithms;


public class DFID implements Solution {
    public int[][] solve(int size) {
        for (int i = 1; i <= size; ++i) {
            Solution dfs = new DFS(i);

            int[][] res = dfs.solve(size);
            if (res != null) {
                return res;
            }
        }
        return null;
    }
}
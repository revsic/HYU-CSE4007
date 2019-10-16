package assignment3;

import java.util.ArrayList;

import assignment3.genetic.GeneticSolver;
import problem.nqueens.Solution;


/**
 * N-Queens solver with genetic algorithm.
 */
public class NQueensSolver extends GeneticSolver<NQueensState> implements Solution {
    /**
     * Construct N-Queens solver.
     * @param size size of the board.
     */
    public NQueensSolver(int size) {
        super(new NQueensGene(size, 7), 5000, 500, 4500, 0);
    }

    /**
     * Name of the solution.
     * @return name of the solution.
     */
    @Override
    public String name() {
        return "Genetic Algorithm";
    }

    /**
     * Implement ending condition.
     * @param generation NQueensState[], one generation.
     * @return NQueensState[], found solution.
     */
    @Override
    public NQueensState[] findSolution(NQueensState[] generation) {
        ArrayList<NQueensState> sol = new ArrayList<NQueensState>();
        for (NQueensState state : generation) {
            // if state solved
            if (state.make().isSolved()) {
                sol.add(state);
            }
        }

        if (sol.size() > 0) {
            // return as array
            return sol.toArray(new NQueensState[sol.size()]);
        }
        return null;
    }

    public static final int MAX_ITER = 500;

    /**
     * Solve the problem.
     * @param size size of the board.
     * @return int[][], found solution.
     */
    @Override
    public int[][] solve(int size) {
        NQueensState[] res = run(MAX_ITER);
        if (res != null) {
            int[][] sol = new int[size][2];
            for (int i = 0; i < size; ++i) {
                sol[i][0] = i;
                sol[i][1] = res[0].value(i);
            }
            return sol;
        }
        return null;
    }
}

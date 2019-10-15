package assignment3;

import java.util.ArrayList;

import assignment3.genetic.GeneticSolver;
import problem.nqueens.Solution;


public class NQueensSolver extends GeneticSolver<NQueensState> implements Solution {
    public NQueensSolver(int size) {
        super(new NQueensGene(size, 7), 5000, 500, 4500, 0);
    }

    public String name() {
        return "Genetic Algorithm";
    }

    public NQueensState[] findSolution(NQueensState[] generation) {
        ArrayList<NQueensState> sol = new ArrayList<NQueensState>();
        for (NQueensState state : generation) {
            if (state.make().isSolved()) {
                sol.add(state);
            }
        }

        return sol.toArray(new NQueensState[sol.size()]);
    }

    public static final int MAX_ITER = 500;

    public int[][] solve(int size) {
        NQueensState[] res = run(MAX_ITER);
        if (res != null) {
            int[][] sol = new int[2][size];
            for (int i = 0; i < size; ++i) {
                sol[i][0] = i;
                sol[i][1] = res[0].value(0);
            }
            return sol;
        }
        return null;
    }
}

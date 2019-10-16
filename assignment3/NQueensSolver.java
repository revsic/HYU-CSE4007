package assignment3;

import java.util.ArrayList;

import assignment3.genetic.GeneticSolver;
import problem.nqueens.Solution;


/**
 * N-Queens solver with genetic algorithm.
 */
public class NQueensSolver extends GeneticSolver<NQueensState> implements Solution {
    /**
     * Parameters for GeneticSolver.
     */
    public static class Param {
        public int tournament;

        public int initialNumber;

        public int parentNumber;

        public int crossNumber;

        public int mutationNumber;

        /**
         * Construct new parameter family.
         * @param tournament int, the number of the tournament.
         * @param initial int, the number of the initial state.
         * @param parent int, the number of the selected state, it should be positive number.
         * @param cross int, the number of the mixed state.
         * @param mutation int, the number of the mutant.
         */
        public Param(int tournament, int initial, int parent, int cross, int mutation) {
            this.tournament = tournament;
            this.initialNumber = initial;
            this.parentNumber = parent;
            this.crossNumber = cross;
            this.mutationNumber = mutation;
        }
    }
    
    private Param param;

    public int maxGen;

    /**
     * Construct N-Queens solver.
     * @param size size of the board.
     * @param param parameter family.
     * @param maxGen maximum number of generations.
     */
    public NQueensSolver(int size, Param param, int maxGen) {
        super(new NQueensGene(size, param.tournament),
              param.initialNumber,
              param.parentNumber,
              param.crossNumber,
              param.mutationNumber);

        this.param = param;
        this.maxGen = maxGen;
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

    /**
     * Solve the problem.
     * @param size size of the board.
     * @return int[][], found solution.
     */
    @Override
    public int[][] solve(int size) {
        NQueensState[] res = run(maxGen);
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

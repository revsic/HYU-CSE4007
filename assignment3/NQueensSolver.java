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
            if (state.isSolved()) {
                sol.add(sol);
            }
        }

        return sol.toArray(new NQueensState[sol.size()]);
    }

    public int[][] solve(int size) {

    }
}
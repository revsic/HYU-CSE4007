package assignment2.hillclimbing;

import assignment2.hillclimbing.Objective;;
import problem.nqueens.Solution;


/**
 * N-Queens problem solver with Hill-Climbing policy.
 */
public class HillClimbing implements Solution {
    public HillClimbing() {
        
    }

    public HillClimbing(Objective obj) {

    }

    /**
     * Name of the solution.
     * @return name of the solution.
     */
    @Override
    public String name() {
        return "Hill Climbing";
    }

    /**
     * Solve N-Queens problem.
     * @param size int, size of the board.
     * @return 2D coordinates array for queens if solution exist, else null.
     */
    @Override
    public int[][] solve(int size) {
        return null;
    }
}

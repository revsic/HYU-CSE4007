package assignment2.hillclimbing;

import java.util.ArrayList;
import problem.nqueens.Solution;


/**
 * N-Queens problem solver with Hill-Climbing policy.
 */
public class HillClimbing implements Solution {
    private int maxRetry;
    private Policy policy;

    public ArrayList<MetaData> meta;
    public static final int MAX_METADATA = 100;

    /**
     * Construct hill climbing method with given policy.
     * @param maxRetry maximum number of retry.
     * @param policy neighbor selection policy.
     */
    public HillClimbing(int maxRetry, Policy policy) {
        this.maxRetry = maxRetry;
        this.policy = policy;
        this.meta = new ArrayList<MetaData>();
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
        // retry
        int ntry = 0;
        double[] times = new double[MetaData.MAX_TIMESTEP];
        for (; ntry < maxRetry; ++ntry) {
            // randomize record
            Record record = Record.random(size);
            // calculate objective
            double score = policy.objective(record.nQueens);

            // timer
            long start = System.currentTimeMillis();
            while (true) {
                // generate next record
                Record next = policy.next(record);
                double nextScore = policy.objective(next.nQueens);

                // if record is in local minima
                if (score >= nextScore) {
                    break;
                }

                // update record
                record = next;
                score = nextScore;
            }
            long end = System.currentTimeMillis();
            times[ntry % MetaData.MAX_TIMESTEP] = (end - start) / 1000.0;

            // if given record is solved
            if (record.nQueens.isSolved()) {
                writeMetaData(ntry, times, true);
                return record.coords.toArray(new int[2][size]);
            }
        }

        // write metadata
        writeMetaData(ntry, times, false);
        return null;
    }

    /**
     * Write metadata thread safely.
     * @param ntry number of try.
     * @param times running time per try in sec unit.
     * @param solved whether this try solve the problem or not.
     */
    private void writeMetaData(int ntry, double[] times, boolean solved) {
        MetaData metadata = new MetaData(ntry, times, solved);
        synchronized(this) {
            // resize array
            if (meta.size() >= MAX_METADATA) {
                meta.remove(0);
            }
            meta.add(metadata);
        }
    }

    /**
     * Meta data for hill climbing method
     */
    public static class MetaData {
        public int numTry;
        public double[] timePerTry;
        public boolean solved;

        public static final int MAX_TIMESTEP = 500;

        /**
         * Construct meta data with several parameters.
         * @param numTry number of try.
         * @param timePerTry running time per try in sec unit.
         * @param solved whether this try solve the problem or not.
         */
        public MetaData(int numTry, double[] timePerTry, boolean solved) {
            this.numTry = numTry;
            this.timePerTry = timePerTry;
            this.solved = solved;
        }
    }
}

package assignment2.hillclimbing;


/**
 * Select highest objectives from neighbors.
 */
public class HighestNeighbor extends DefaultObjective {
    /**
     * Get neighbor which have highest objectives.
     * @param record current record.
     * @return neighbor record.
     */
    @Override
    public Record next(Record record) {
        Record highest = null;
        double score = -1024;
        // traverse all neighbor
        for (Record neighbor : record.neighbor()) {
            // get maximum objective
            double res = objective(neighbor.nQueens);
            if (res > score) {
                score = res;
                highest = neighbor;
            }
        }
        return highest;
    }
}

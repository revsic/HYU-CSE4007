package assignment2.hillclimbing;


public class HighestNeighbor extends DefaultObjective {
    @Override
    public Record next(Record record) {
        Record highest = null;
        double score = Double.MIN_VALUE;
        for (Record neighbor : record.neighbor()) {
            double res = objective(neighbor.nQueens);
            if (res > score) {
                score = res;
                highest = record;
            }
        }
        return highest;
    }
}

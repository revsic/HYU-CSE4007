package assignment3;

import java.util.Random;

import assignment3.genetic.Gene;
import problem.nqueens.NQueensObjective;


/**
 * Gene implementation for N-Queens environment.
 */
public class NQueensGene implements Gene<NQueensState> {
    private int size;

    private int tournament;

    /**
     * Construct new gene derivation policy.
     * @param size int, size of the board.
     * @param tournament int, the number of genes for tournament to select next generation.  
     */
    public NQueensGene(int size, int tournament) {
        this.size = size;
        this.tournament = tournament;
    }

    /**
     * Create new states array.
     * @param size int, size of the board.
     * @return NQueensState[], new array.
     */
    @Override
    public NQueensState[] newArray(int size) {
        return new NQueensState[size];
    }

    /**
     * Initialize first generation.
     * @return NQueensState, random initialized state.
     */
    @Override
    public NQueensState initialState() {
        return NQueensState.random(size);
    }

    /**
     * Select next generation from previous generation.
     * @param family NQueensState[], previous generation.
     * @return NQueensState, parent gene.
     */
    @Override
    public NQueensState select(NQueensState[] family) {
        Random gen = new Random();

        double score = -1024;
        NQueensState state = null;
        // get minimum objectives
        for (int i = 0; i < tournament; ++i) {
            int idx = gen.nextInt(family.length);
            // evaluate
            double chosen = NQueensObjective.run(family[idx].make());
            if (score < chosen) {
                score = chosen;
                state = family[idx];
            }
        }

        return state;
    }

    /**
     * Mix information between some parents.
     * @param parents NQueensState[], parent genes.
     * @return NQueensState, mixed state.
     */
    @Override
    public NQueensState crossover(NQueensState[] parents) {
        Random gen = new Random();
        NQueensState state = new NQueensState(size);
        
        int point = gen.nextInt(size);
        NQueensState target1 = parents[gen.nextInt(parents.length)];
        NQueensState target2 = parents[gen.nextInt(parents.length)];

        for (int i = 0; i < size; ++i) {
            if (i < point) {
                state.assign(i, target1.value(i));
            } else {
                state.assign(i, target2.value(i));
            }
        }
        return state;
    }

    /**
     * Random mutation for genetic variety.
     * @param parents NQueensState[], parent genes.
     * @return NQueensState, mutated state.
     */
    @Override
    public NQueensState mutate(NQueensState[] parents) {
        return null;
    }
}

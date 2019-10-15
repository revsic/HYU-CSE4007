package assignment3;

import java.util.Random;

import assignment3.genetic.Gene;


public class NQueensGene implements Gene<NQueensState> {
    private int size;

    private int tournament;

    public NQueensGene(int size, int tournament) {
        this.size = size;
        this.tournament = tournament;
    }

    public NQueensState[] newArray(int size) {
        return new NQueensState[size];
    }

    public NQueensState initialState() {
        return NQueensState.random(size);
    }

    public NQueensState select(NQueensState[] family) {
        Random gen = new Random();

        double score = -1024;
        NQueensState state = null;

        for (int i = 0; i < tournament; ++i) {
            int idx = gen.nextInt(family.length);
            double chosen = family[idx].eval();
            if (score < chosen) {
                score = chosen;
                state = family[idx];
            }
        }

        return state;
    }

    public NQueensState crossover(NQueensState[] parents) {
        Random gen = new Random();
        NQueensState state = new NQueensState(size);
        for (int i = 0; i < size; ++i) {
            int idx = gen.nextInt(parents.length);
            state.assign(i, parents[idx].value(i));
        }
        return state;
    }

    public NQueensState mutate(NQueensState[] parents) {
        return null;
    }
}

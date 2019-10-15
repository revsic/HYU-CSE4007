package assignment3;

import java.util.Random;

import problem.nqueens.NQueens;
import problem.nqueens.NQueensObjective;


public class NQueensState {
    private int size;

    private int[] state;

    public NQueensState(int size) {
        this.size = size;
        this.state = new int[size];
    }

    public NQueensState(int[] state) {
        this.size = state.length;
        this.state = state;
    }

    public void assign(int idx, int value) {
        state[idx] = value;
    }

    public int value(int idx) {
        return state[idx];
    }

    public int[] getState() {
        return state;
    }

    private NQueens make() {
        NQueens queens = new NQueens();
        for (int i = 0; i < size; ++i) {
            queens.setPos(i, state[i]);
        }
        return queens;
    }

    public double eval() {
        return NQueensObjective.run(make());
    }

    public boolean isSolved() {
        return make().isSolved();
    }

    public static NQueensState random(int size) {
        Random gen = new Random();

        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = gen.nextInt(size);
        }

        return new NQueensState(state);
    }
}

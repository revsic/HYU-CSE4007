package assignment3;

import java.util.Random;

import problem.nqueens.NQueens;


/**
 * State defintion for genetic algorithm to solve N-Queens problem.
 */
public class NQueensState {
    private int size;

    private int[] state;

    /**
     * Construct new state.
     * @param size size of the board.
     */
    public NQueensState(int size) {
        this.size = size;
        this.state = new int[size];
    }

    /**
     * Construct new state with given array.
     * @param state x-coordinate array.
     */
    public NQueensState(int[] state) {
        this.size = state.length;
        this.state = state;
    }

    /**
     * Assign value to specified state cell.
     * @param idx int, index.
     * @param value int, value.
     */
    public void assign(int idx, int value) {
        state[idx] = value;
    }

    /**
     * Return value of specified cell.
     * @param idx int, index.
     * @return int, value.
     */
    public int value(int idx) {
        return state[idx];
    }

    /**
     * Generate NQueens from state cell.
     * @return NQueens, generated state.
     */
    public NQueens make() {
        NQueens queens = new NQueens(size);
        for (int i = 0; i < size; ++i) {
            queens.setPos(i, state[i]);
        }
        return queens;
    }

    /**
     * Construct random N-Queens state.
     * @param int, size of the board.
     * @return NQueensState, random state.
     */
    public static NQueensState random(int size) {
        Random gen = new Random();

        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = gen.nextInt(size);
        }

        return new NQueensState(arr);
    }
}

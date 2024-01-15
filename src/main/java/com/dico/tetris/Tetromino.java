package com.dico.tetris;

import java.util.Random;

public class Tetromino {
    private static final Random random = new Random();

    public int pointerRow = 0;
    public int pointerCol = 4;
    private int[][] blocks;

    public Tetromino() {
        setRandomTetromino();
    }

    public void resetPointer() {
        this.pointerRow = 0;
        this.pointerCol = 4;
    }

    public void setRandomTetromino() {
        int index = random.nextInt(7);
        this.blocks = Figure.values()[index].blocks;
    }

    public int[][] getBlocks() {
        return this.blocks;
    }

    public void setBlocks(int[][] blocks) {
        this.blocks = blocks;
    }

    public int getWidth() {
        return pointerCol + blocks[0].length;
    }
    public int getHeight() {
        return pointerRow + blocks.length;
    }

    enum Figure {
        I(new int[][]
                {
                        {1},
                        {1},
                        {1},
                        {1}
                }),
        J(new int[][]
                {
                        {0, 1},
                        {0, 1},
                        {1, 1}
                }),
        L(new int[][]
                {
                        {1, 0},
                        {1, 0},
                        {1, 1}
                }),
        O(new int[][]
                {
                        {1, 1},
                        {1, 1}
                }),
        S(new int[][]
                {
                        {0, 1, 1},
                        {1, 1, 0}
                }),
        T(new int[][]
                {
                        {1, 1, 1},
                        {0, 1, 0}
                }),
        Z(new int[][]
                {
                        {1, 1, 0},
                        {0, 1, 1}
                });

        private final int[][] blocks ;

        Figure(int[][] blocks) {
            this.blocks = blocks;
        }

        public int[][] getBlocks() {
            return this.blocks;
        }

    }

}

package com.dico.tetris;

import javafx.scene.paint.Color;

import java.util.Random;

public class Tetromino {
    private static final Random random = new Random();
    public int pointerRow = 0;
    public int pointerCol = 4;
    public Color color;
    private int[][] blocks;

    public Tetromino() {
        setRandomTetromino();
    }

    public void setRandomTetromino() {
        int index = random.nextInt(7);
        Figure figure = Figure.values()[index];
        this.blocks = figure.blocks;
        this.color = figure.color;
    }

    public boolean isBlockOccupied(int i, int j) {
        return getBlock(i, j) == Block.OCCUPIED;
    }

    public Color getColor() {
        return this.color;
    }
    public int getBlock(int i, int j) {
        return this.blocks[i][j];
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
                }, Color.CYAN),
        J(new int[][]
                {
                        {0, 1},
                        {0, 1},
                        {1, 1}
                }, Color.BLUE),
        L(new int[][]
                {
                        {1, 0},
                        {1, 0},
                        {1, 1}
                }, Color.DARKORANGE),
        O(new int[][]
                {
                        {1, 1},
                        {1, 1}
                }, Color.YELLOW.brighter()),
        T(new int[][]
                {
                        {1, 1, 1},
                        {0, 1, 0}
                }, Color.MEDIUMPURPLE),
        S(new int[][]
                {
                        {0, 1, 1},
                        {1, 1, 0}
                }, Color.LIMEGREEN),
        Z(new int[][]
                {
                        {1, 1, 0},
                        {0, 1, 1}
                }, Color.RED);

        private final int[][] blocks;
        private final Color color;

        Figure(int[][] blocks, Color color) {
            this.blocks = blocks;
            this.color = color;
        }

        public int[][] getBlocks() {
            return this.blocks;
        }
    }

}

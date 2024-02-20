package com.dico.tetris;

import javafx.scene.paint.Color;

import java.util.Random;

import static com.dico.tetris.BlockFactory.createBlock;

public class Tetromino {
    private static final Random random = new Random();
    public int pointerRow = 0;
    public int pointerCol = 4;
    private Block[][] blocks;

    public Tetromino() {
        setRandomTetromino();
    }

    public void setRandomTetromino() {
        int index = random.nextInt(7);
        Figure figure = Figure.values()[index];
        this.blocks = figure.blocks;
    }

    public boolean hasBlockColor(int i, int j) {
        return !getBlock(i, j).isWhite();
    }

    public Block getBlock(int i, int j) {
        return this.blocks[i][j];
    }

    public Block[][] getBlocks() {
        return this.blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public int getWidth() {
        return pointerCol + blocks[0].length;
    }

    public int getHeight() {
        return pointerRow + blocks.length;
    }

    enum Figure {
        I(new Block[][]
                {
                        {createBlock(Color.CYAN), createBlock(Color.CYAN), createBlock(Color.CYAN), createBlock(Color.CYAN)},
                }),
        J(new Block[][]
                {
                        {createBlock(Color.BLUE), createBlock(Color.WHITE), createBlock(Color.WHITE)},
                        {createBlock(Color.BLUE), createBlock(Color.BLUE), createBlock(Color.BLUE)}
                }),
        L(new Block[][]
                {
                        {createBlock(Color.WHITE), createBlock(Color.WHITE), createBlock(Color.DARKORANGE)},
                        {createBlock(Color.DARKORANGE), createBlock(Color.DARKORANGE), createBlock(Color.DARKORANGE)}
                }),
        O(new Block[][]
                {
                        {createBlock(Color.YELLOW.brighter()), createBlock(Color.YELLOW.brighter())},
                        {createBlock(Color.YELLOW.brighter()), createBlock(Color.YELLOW.brighter())}
                }),
        T(new Block[][]
                {
                        {createBlock(Color.MEDIUMPURPLE), createBlock(Color.MEDIUMPURPLE), createBlock(Color.MEDIUMPURPLE)},
                        {createBlock(Color.WHITE), createBlock(Color.MEDIUMPURPLE), createBlock(Color.WHITE)}
                }),
        S(new Block[][]
                {
                        {createBlock(Color.WHITE), createBlock(Color.LIMEGREEN), createBlock(Color.LIMEGREEN)},
                        {createBlock(Color.LIMEGREEN), createBlock(Color.LIMEGREEN), createBlock(Color.WHITE)}
                }),
        Z(new Block[][]
                {
                        {createBlock(Color.RED), createBlock(Color.RED), createBlock(Color.WHITE)},
                        {createBlock(Color.WHITE), createBlock(Color.RED), createBlock(Color.RED)}
                });

        private final Block[][] blocks;

        Figure(Block[][] blocks) {
            this.blocks = blocks;
        }

        public Block[][] getBlocks() {
            return this.blocks;
        }
    }

}

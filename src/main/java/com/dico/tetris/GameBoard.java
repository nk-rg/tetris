package com.dico.tetris;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public class GameBoard {

    private final int[][] board;
    private final Tetromino tetromino;

    public GameBoard(int rows, int cols, Tetromino tetromino) {
        this.board = new int[rows][cols];
        this.tetromino = tetromino;
    }

    public void update() {
        processBlocks(
                this::isBlockBoardFree,
                this::setBlockWithTetrominoValue);
    }

    public void clearPreviousTetromino() {
        processBlocks(
                this::isBlockBoardOccupied,
                this::setBlockWithFreeBlock);
    }

    public void processBlocks(BiPredicate<Integer, Integer> predicate, BiConsumer<Integer, Integer> action) {
        int[][] blocks = tetromino.getBlocks();
        IntStream.range(0, blocks.length)
                .forEach(i -> IntStream.range(0, blocks[i].length)
                        .filter((j) -> predicate.test(i, j) && tetromino.isBlockOccupied(i, j))
                        .forEach((j) -> action.accept(i, j)));
    }

    public void clearCompleteRows() {
        for (int i = board.length-1; i >= 0 ; i--) {
            boolean allOnes = true;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 1) {
                    allOnes = false;
                    break;
                }
            }
            if (allOnes) {
                for (int k = i; k >= 1; k--) {
                    System.arraycopy(board[k-1], 0, board[k], 0, board[0].length);
                }
                i++;
            }
        }
    }

    public boolean isBlockBoardFree(int i, int j) {
        return board[tetromino.pointerRow + i][tetromino.pointerCol + j] == Block.FREE;
    }

    public boolean isBlockBoardOccupied(int i, int j) {
        return board[tetromino.pointerRow + i][tetromino.pointerCol + j] == Block.OCCUPIED;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }
    public int[][] getBoard() {
        return board;
    }

    public int getHeight() {
        return getBoard().length;
    }

    public void setBlockWithFreeBlock(int i, int j) {
        setBlockFromTetrominoPosition(i, j, Block.FREE);
    }

    public void setBlockWithTetrominoValue(int i, int j) {
        setBlockFromTetrominoPosition(i, j, tetromino.getBlock(i, j));
    }

    public void setBlockFromTetrominoPosition(int i, int j, int value) {
        board[tetromino.pointerRow + i][tetromino.pointerCol + j] = value;
    }

    public int getBlock(int row, int col) {
        return board[row][col];
    }
}

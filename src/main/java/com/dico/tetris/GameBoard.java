package com.dico.tetris;

import javafx.scene.paint.Color;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import static com.dico.tetris.BlockFactory.createBlock;

public class GameBoard {

    private final Block[][] board;
    private final Tetromino tetromino;
    private final Tetromino nextTetromino;
    private final Game game;

    public GameBoard(int rows, int cols, Game game) {
        this.board = new Block[rows][cols];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = createBlock(Color.WHITE);
            }
        }
        this.tetromino = new Tetromino();
        this.nextTetromino = new Tetromino();
        this.game = game;
    }

    public void update() {
        processBlocks(
                this::isBlockFree,
                this::setBlockFromTetromino);
    }

    public void processBlocks(BiPredicate<Integer, Integer> predicate, BiConsumer<Integer, Integer> action) {
        Block[][] blocks = tetromino.getBlocks();
        IntStream.range(0, blocks.length)
                .forEach(i -> IntStream.range(0, blocks[i].length)
                        .filter((j) -> predicate.test(i, j) && tetromino.hasBlockColor(i, j))
                        .forEach((j) -> action.accept(i, j)));
    }

    public boolean isBlockFree(int i, int j) {
        return board[tetromino.pointerRow + i][tetromino.pointerCol + j].isWhite();
    }

    public void setBlockFromTetromino(int i, int j) {
        setBlock(i, j, tetromino.getBlock(i, j).color());
    }

    public void setBlock(int i, int j, Color color) {
        board[tetromino.pointerRow + i][tetromino.pointerCol + j] = createBlock(color);
    }

    public void clearPreviousTetromino() {
        processBlocks(
                this::isOccupied,
                this::setWhiteBlock);
    }

    public boolean isOccupied(int i, int j) {
        return !(board[tetromino.pointerRow + i][tetromino.pointerCol + j].isWhite());
    }

    public void setWhiteBlock(int i, int j) {
        setBlock(i, j, Color.WHITE);
    }

    public void clearCompleteRows() {
        for (int i = board.length - 1; i >= 0; i--) {
            boolean allOnes = true;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].isWhite()) {
                    allOnes = false;
                    break;
                }
            }
            if (allOnes) {
                for (int k = i; k >= 1; k--) {
                    System.arraycopy(board[k - 1], 0, board[k], 0, board[0].length);
                }
                i++;
            }
        }
    }

    public Tetromino getTetromino() {
        return tetromino;
    }

    public Tetromino getNextTetromino() {
        return nextTetromino;
    }

    public int getHeight() {
        return getBoard().length;
    }

    public Block[][] getBoard() {
        return board;
    }

    public Block getBlock(int row, int col) {
        return board[row][col];
    }

    public Game getGame() {
        return game;
    }
}

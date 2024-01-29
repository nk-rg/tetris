package com.dico.tetris.action;

import com.dico.tetris.GameBoard;
import com.dico.tetris.Tetromino;
import com.dico.tetris.Block;

public class MoveRight extends Movement {

    private static MoveRight instance;
    private final GameBoard gameBoard;
    private final Tetromino tetromino;

    private MoveRight(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.tetromino = gameBoard.getTetromino();
    }

    public static Move getInstance(GameBoard gameBoard) {
        if (instance == null) {
            instance = new MoveRight(gameBoard);
        }
        return instance;
    }

    @Override
    public int getRow() {
        return tetromino.pointerRow;
    }

    @Override
    public int getCol() {
        return tetromino.pointerCol + 1;
    }

    @Override
    public boolean isValid() {
        return !overflowRight() && !hasCollision();
    }

    private boolean overflowRight() {
        return tetromino.getWidth() == gameBoard.getBoard()[0].length;
    }

    @Override
    public void move() {
        ++tetromino.pointerCol;
        gameBoard.update();
    }

    @Override
    int[][] getTetromino() {
        return tetromino.getBlocks();
    }

    @Override
    GameBoard getGameBoard() {
        return gameBoard;
    }
}

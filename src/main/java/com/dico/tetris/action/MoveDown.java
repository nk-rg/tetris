package com.dico.tetris.action;

import com.dico.tetris.Block;
import com.dico.tetris.GameBoard;
import com.dico.tetris.Tetromino;

public class MoveDown extends Movement {
    private static MoveDown instance;
    private final GameBoard gameBoard;
    private final Tetromino tetromino;

    private MoveDown(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.tetromino = gameBoard.getTetromino();
    }

    public static Move getInstance(GameBoard gameBoard) {
        if (instance == null) {
            instance = new MoveDown(gameBoard);
        }
        return instance;
    }

    @Override
    public int getRow() {
        return tetromino.pointerRow + 1;
    }

    @Override
    public int getCol() {
        return tetromino.pointerCol;
    }

    @Override
    public boolean isValid() {
        if (overflowBottom() || hasCollision()) {
            resetTetromino();
            return false;
        }
        return true;
    }

    private void resetTetromino() {
        tetromino.setRandomTetromino();
        gameBoard.clearCompleteRows();
        tetromino.pointerRow = 0;
        tetromino.pointerCol = 4;
    }

    private boolean overflowBottom() {
        return tetromino.getHeight() == gameBoard.getHeight();
    }

    @Override
    public void move() {
        ++tetromino.pointerRow;
        gameBoard.update();
    }

    @Override
    Tetromino getTetromino() {
        return tetromino;
    }

    @Override
    GameBoard getGameBoard() {
        return gameBoard;
    }
}

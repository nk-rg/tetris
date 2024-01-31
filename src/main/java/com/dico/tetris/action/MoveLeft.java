package com.dico.tetris.action;

import com.dico.tetris.Block;
import com.dico.tetris.GameBoard;
import com.dico.tetris.Tetromino;

public class MoveLeft extends Movement {
    private static MoveLeft instance;
    private final GameBoard gameBoard;
    private final Tetromino tetromino;

    private MoveLeft(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.tetromino = gameBoard.getTetromino();
    }

    public static Move getInstance(GameBoard gameBoard) {
        if (instance == null) {
            instance = new MoveLeft(gameBoard);
        }
        return instance;
    }

    @Override
    public int getRow() {
        return tetromino.pointerRow;
    }

    @Override
    public int getCol() {
        return tetromino.pointerCol - 1;
    }

    @Override
    public boolean isValid() {
        return !overflowLeft() && !hasCollision();
    }

    @Override
    public void move() {
        --tetromino.pointerCol;
        gameBoard.update();
    }

    private boolean overflowLeft() {
        return tetromino.pointerCol == 0;
    }

    @Override
    GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    Block[][] getTetromino() {
        return tetromino.getBlocks();
    }
}

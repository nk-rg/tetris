package com.dico.tetris.action;

import com.dico.tetris.Block;
import com.dico.tetris.GameBoard;
import com.dico.tetris.Tetromino;
import com.dico.tetris.util.MatrixUtil;

public class Rotate extends Movement {
    private static Rotate instance;
    private final GameBoard gameBoard;
    private final Tetromino tetromino;
    private Block[][] rotatedBlocks;

    private Rotate(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.tetromino = gameBoard.getTetromino();
    }

    public static Move getInstance(GameBoard gameBoard) {
        if (instance == null) {
            instance = new Rotate(gameBoard);
        }
        return instance;
    }

    @Override
    public int getRow() {
        return tetromino.pointerRow;
    }

    @Override
    public int getCol() {
        return tetromino.pointerCol;
    }

    @Override
    public boolean isValid() {
        setRotatedBlocks();
        return !isOffset() && !hasCollision();
    }

    private void setRotatedBlocks() {
        rotatedBlocks = MatrixUtil.rotate90Rigth(tetromino.getBlocks());
    }

    private boolean isOffset() {
        return tetromino.pointerCol + rotatedBlocks[0].length - 1 >= gameBoard.getBoard()[0].length ||
                tetromino.pointerRow + rotatedBlocks.length >= gameBoard.getBoard().length;
    }

    @Override
    public void move() {
        gameBoard.clearPreviousTetromino();
        tetromino.setBlocks(rotatedBlocks);
        gameBoard.update();
    }

    @Override
    GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    Block[][] getTetromino() {
        return rotatedBlocks;
    }
}

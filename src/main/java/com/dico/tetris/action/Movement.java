package com.dico.tetris.action;

import com.dico.tetris.Block;
import com.dico.tetris.GameBoard;

public abstract class Movement implements Move {

    public boolean hasCollision() {
        GameBoard gameBoard = getGameBoard();
        gameBoard.clearPreviousTetromino();
        int[][] tetrominoBlocks = getTetromino();
        for (int i = 0; i < tetrominoBlocks.length; i++) {
            for (int j = 0; j < tetrominoBlocks[i].length; j++) {
                int row = getRow() + i;
                int col = getCol() + j;
                int boardBlock = gameBoard.getBoard()[row][col];
                int tetrominoBlock = tetrominoBlocks[i][j];
                if (boardBlock == Block.OCCUPIED && tetrominoBlock == Block.OCCUPIED) {
                    gameBoard.update();
                    return true;
                }
            }
        }
        return false;
    }

    abstract GameBoard getGameBoard();

    abstract int[][] getTetromino();
}

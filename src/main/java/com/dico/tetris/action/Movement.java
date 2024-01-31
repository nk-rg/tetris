package com.dico.tetris.action;

import com.dico.tetris.Block;
import com.dico.tetris.GameBoard;

public abstract class Movement implements Move {

    public boolean hasCollision() {
        GameBoard gameBoard = getGameBoard();
        gameBoard.clearPreviousTetromino();
        Block[][] tetrominoBlocks = getTetromino();
        for (int i = 0; i < tetrominoBlocks.length; i++) {
            for (int j = 0; j < tetrominoBlocks[i].length; j++) {
                int row = getRow() + i;
                int col = getCol() + j;
                Block boardBlock = gameBoard.getBoard()[row][col];
                Block tetrominoBlock = tetrominoBlocks[i][j];
                if (!boardBlock.isWhite() && !tetrominoBlock.isWhite()) {
                    gameBoard.update();
                    return true;
                }
            }
        }
        return false;
    }

    abstract GameBoard getGameBoard();

    abstract Block[][] getTetromino();
}

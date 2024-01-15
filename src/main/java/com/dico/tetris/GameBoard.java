package com.dico.tetris;

public class GameBoard {

    private final int[][] board;
    private final Tetromino tetromino;

    public GameBoard(int rows, int cols, Tetromino tetromino) {
        this.board = new int[rows][cols];
        this.tetromino = tetromino;
    }



    public void update() {
        int[][] blocks = tetromino.getBlocks();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (board[tetromino.pointerRow +i][tetromino.pointerCol +j] == 0
                        && blocks[i][j] == 1) {
                    board[tetromino.pointerRow +i][tetromino.pointerCol +j] = blocks[i][j];
                }
            }
        }
    }

    public void clearPreviousTetromino() {
        int[][] blocks = tetromino.getBlocks();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (board[tetromino.pointerRow + i][tetromino.pointerCol + j] == 1
                        && blocks[i][j] == 1) {
                    board[tetromino.pointerRow + i][tetromino.pointerCol + j] = 0;
                }
            }
        }
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

    public Tetromino getTetromino() {
        return tetromino;
    }
    public int[][] getBoard() {
        return board;
    }

    public int getHeight() {
        return getBoard().length;
    }
    public int getBlock(int row, int col) {
        return board[row][col];
    }
}

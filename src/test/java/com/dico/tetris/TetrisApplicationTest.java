package com.dico.tetris;

import com.dico.tetris.util.MatrixUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TetrisApplicationTest {

    @Test
    void testRotateMatrix90degree() {
        int[][] blocks = Tetromino.Figure.J.getBlocks();
        int[][] rotated = MatrixUtil.rotate90Rigth(blocks);
        assertEquals(2, rotated.length);
        assertEquals(3, rotated[0].length);
        assertArrayEquals(new int[][]{{1, 0, 0}, {1, 1, 1}}, rotated);
    }

    @Test
    void testRotateMatrix180degree() {
        int[][] blocks = Tetromino.Figure.J.getBlocks();
        int[][] rotated = MatrixUtil.rotate90Rigth(MatrixUtil.rotate90Rigth(blocks));
        assertEquals(3, rotated.length);
        assertEquals(2, rotated[0].length);
        assertArrayEquals(new int[][]{{1, 1}, {1, 0}, {1, 0}}, rotated);
    }

    @Test
    void testInvalidDownMovement() {

        int[][] board = new int[][]
                {
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},//7
                        {0, 1, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0},
                        {0, 1, 1, 0, 0, 0}
                };
        int pointerRow = 7;
        int pointerCol = 1;
        int[][] piece = new int[][]
                {
                        {1, 1, 1},
                        {0, 0, 1}
                };
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (board[pointerRow+i+1][pointerCol+j] == 1
                        && piece[i][j] == 1) {
                    System.out.println("sgte movimiento invalido");
                    return;
                }
            }
        }
    }

    @Test
    void testCompleteRows() {
        int[][] board = new int[][]
                {
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0},//7
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };
        for (int i = board.length-1; i >= 0 ; i--) {
           boolean allOnes = true;
           for (int j = 0; j < board[0].length; j++) {
               if (board[i][j] != 1) {
                   allOnes = false;
               }
           }
            if (allOnes) {
                for (int k = i; k >= 1; k--) {
                    System.arraycopy(board[k-1], 0, board[k], 0, board[0].length);
                }
                i++;
            }
        }
        for(int[] ar: board) {
            for (int a : ar) {
                System.out.print(a);
            }
            System.out.println();
        }
    }
}
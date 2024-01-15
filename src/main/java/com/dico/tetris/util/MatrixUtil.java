package com.dico.tetris.util;

public class MatrixUtil {

    public static int[][] rotate90Rigth(int[][] matrix) {
        int rows = matrix[0].length;
        int cols = matrix.length;
        int[][] newBlocks = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newBlocks[i][j] = matrix[cols - j - 1][i];
            }
        }
        return newBlocks;
    }
}

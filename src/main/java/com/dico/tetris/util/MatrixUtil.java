package com.dico.tetris.util;

import com.dico.tetris.Block;

public class MatrixUtil {

    public static Block[][] rotate90Rigth(Block[][] matrix) {
        int rows = matrix[0].length;
        int cols = matrix.length;
        Block[][] newBlocks = new Block[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newBlocks[i][j] = matrix[cols - j - 1][i];
            }
        }
        return newBlocks;
    }

}

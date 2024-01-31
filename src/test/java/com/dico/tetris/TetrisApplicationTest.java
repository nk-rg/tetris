package com.dico.tetris;

import com.dico.tetris.util.MatrixUtil;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static com.dico.tetris.BlockFactory.createBlock;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TetrisApplicationTest {

    @Test
    void testRotateMatrix90degree() {
        Block[][] blocks = Tetromino.Figure.J.getBlocks();
        Block[][] rotated = MatrixUtil.rotate90Rigth(blocks);
        assertEquals(2, rotated.length);
        assertEquals(3, rotated[0].length);
        assertArrayEquals(new Block[][]{
                        {createBlock(Color.BLUE), createBlock(Color.WHITE), createBlock(Color.WHITE)},
                        {createBlock(Color.BLUE), createBlock(Color.BLUE), createBlock(Color.BLUE)}},
                rotated);
    }

    @Test
    void testRotateMatrix180degree() {
        Block[][] blocks = Tetromino.Figure.J.getBlocks();
        Block[][] rotated = MatrixUtil.rotate90Rigth(MatrixUtil.rotate90Rigth(blocks));
        assertEquals(3, rotated.length);
        assertEquals(2, rotated[0].length);
        assertArrayEquals(new Block[][]{
                        {createBlock(Color.BLUE), createBlock(Color.BLUE)},
                        {createBlock(Color.BLUE), createBlock(Color.WHITE)},
                        {createBlock(Color.BLUE), createBlock(Color.WHITE)}},
                rotated);
    }
}
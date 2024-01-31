package com.dico.tetris;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class BlockFactory {

    private static final HashMap<Color, Block> blockHashMap = new HashMap<>();

    public static Block createBlock(Color color) {
        if (!blockHashMap.containsKey(color)) {
            Block newBlock = new Block(color);
            blockHashMap.put(color, newBlock);
            return newBlock;
        }
        return blockHashMap.get(color);
    }

}

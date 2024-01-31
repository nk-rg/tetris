package com.dico.tetris;

import javafx.scene.paint.Color;

public record Block(Color color) {

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }
}

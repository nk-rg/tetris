package com.dico.tetris.action;

public interface Move {
    int getRow();

    int getCol();

    default void performMove() {
        if (isValid()) {
            move();
        }
    }

    boolean isValid();

    void move();
}

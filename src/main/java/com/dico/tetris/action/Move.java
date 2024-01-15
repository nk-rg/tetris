package com.dico.tetris.action;

public interface Move {
    int getRow();
    int getCol();
    boolean isValid();
    void move();
}

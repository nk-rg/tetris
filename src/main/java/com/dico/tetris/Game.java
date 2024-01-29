package com.dico.tetris;

import com.dico.tetris.action.*;
import com.dico.tetris.util.MatrixUtil;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Game {

    @FXML
    GridPane gridPane;

    private static final int BOARD_ROWS = 20;
    private static final int BOARD_COLS = 10;
    private static final int BLOCK_SIZE = 30;
    private final Tetromino tetromino = new Tetromino();;
    private final GameBoard gameBoard = new GameBoard(BOARD_ROWS, BOARD_COLS, tetromino);
    private final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        move(MoveDown.getInstance(gameBoard));
        updateGridPane();
    }));

    public void draw() {
        gridPane.setStyle("-fx-grid-lines-visible: true;");
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLS; col++) {
                Rectangle rectangle = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.WHITE);
                gridPane.add(rectangle, col, row);
            }
        }
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void updateGridPane() {
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLS; col++) {
                int block = gameBoard.getBlock(row, col);
                Rectangle rectangle = (Rectangle) gridPane.getChildren().get(row * BOARD_COLS + col);
                if (block == Block.FREE) {
                    rectangle.setFill(Color.WHITE);
                } else if (Color.WHITE.equals(rectangle.getFill())){
                    rectangle.setFill(tetromino.getColor());
                }
            }
        }
    }

    public void checkKeyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getCode()) {
            case LEFT -> move(MoveLeft.getInstance(gameBoard));
            case RIGHT -> move(MoveRight.getInstance(gameBoard));
            case UP -> move(Rotate.getInstance(gameBoard));
            case DOWN -> move(MoveDown.getInstance(gameBoard));
            case S -> timeline.stop();
            case R -> timeline.play();
        }
        updateGridPane();
    }

    public void move(Move movement) {
        if (movement.isValid()) {
            movement.move();
        }
    }
}

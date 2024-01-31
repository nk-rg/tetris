package com.dico.tetris;

import com.dico.tetris.action.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Game {

    private static final int BOARD_ROWS = 20;
    private static final int BOARD_COLS = 10;
    private static final int BLOCK_SIZE = 30;
    private final GameBoard gameBoard;
    private final Move moveDown;
    private final Move moveRight;
    private final Move moveLeft;
    private final Move rotate;
    private final Timeline timeline;
    @FXML
    GridPane gridPane;
    @FXML
    Label lblScore;

    public Game() {
        this.gameBoard = new GameBoard(BOARD_ROWS, BOARD_COLS);
        this.moveDown = MoveDown.getInstance(gameBoard);
        this.moveRight = MoveRight.getInstance(gameBoard);
        this.moveLeft = MoveLeft.getInstance(gameBoard);
        this.rotate = Rotate.getInstance(gameBoard);
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            moveDown.performMove();
            updateGridPane();
        }));
    }

    private void updateGridPane() {
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLS; col++) {
                Block block = gameBoard.getBlock(row, col);
                Rectangle rectangle = (Rectangle) gridPane.getChildren().get(row * BOARD_COLS + col);
                rectangle.setFill(block.color());
            }
        }
    }

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

    public void checkKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT -> moveLeft.performMove();
            case RIGHT -> moveRight.performMove();
            case UP -> rotate.performMove();
            case DOWN -> moveDown.performMove();
            case S -> timeline.stop();
            case R -> timeline.play();
        }
        updateGridPane();
    }

}

package com.dico.tetris;

import com.dico.tetris.action.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    GridPane gpGame;
    @FXML
    Label lblScore;
    @FXML
    GridPane gpNext;
    private boolean gameOver = false;
    private Scene scene;


    public Game() {
        this.gameBoard = new GameBoard(BOARD_ROWS, BOARD_COLS, this);
        this.moveDown = MoveDown.getInstance(gameBoard);
        this.moveRight = MoveRight.getInstance(gameBoard);
        this.moveLeft = MoveLeft.getInstance(gameBoard);
        this.rotate = Rotate.getInstance(gameBoard);
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (!gameOver) {
                moveDown.performMove();
                updateGpGame();
            }
        }));
    }

    private void updateGpGame() {
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLS; col++) {
                Block block = gameBoard.getBlock(row, col);
                Rectangle rectangle = (Rectangle) gpGame.getChildren().get(row * BOARD_COLS + col);
                rectangle.setFill(block.color());
            }
        }
    }

    public void checkGameOver() {
        Tetromino tetromino = gameBoard.getTetromino();
        Block[][] blocks = tetromino.getBlocks();

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (!blocks[i][j].isWhite() &&
                        !gameBoard.getBoard()[tetromino.pointerRow + i][tetromino.pointerCol + j].isWhite()) {
                    gameOver = true;
                    return;
                }
            }
        }
    }

    public void checkKeyPressed(KeyEvent keyEvent) {
        if (!gameOver) {
            switch (keyEvent.getCode()) {
                case LEFT -> moveLeft.performMove();
                case RIGHT -> moveRight.performMove();
                case UP -> rotate.performMove();
                case DOWN -> moveDown.performMove();
                case S -> timeline.stop();
                case R -> timeline.play();
            }
            updateGpGame();
        }
    }

    public void init() {
        drawGpGame();
        drawGpNext();
        updateGpNext();
    }

    public void drawGpGame() {
        gpGame.setStyle("-fx-grid-lines-visible: true;");
        for (int row = 0; row < gpGame.getRowCount(); row++) {
            for (int col = 0; col < gpGame.getColumnCount(); col++) {
                Rectangle rectangle = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.WHITE);
                gpGame.add(rectangle, col, row);
            }
        }
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void drawGpNext() {
        gpNext.setStyle("-fx-grid-lines-visible: true;");
        for (int row = 0; row < gpNext.getRowCount(); row++) {
            for (int col = 0; col < gpNext.getColumnCount(); col++) {
                Rectangle rectangle = new Rectangle(20, 20);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.WHITE);
                gpNext.add(rectangle, col, row);
            }
        }
    }

    public void updateGpNext() {
        clearGpNext();
        Tetromino nextTetromino = gameBoard.getNextTetromino();
        Block[][] blocks = nextTetromino.getBlocks();
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[row].length; col++) {
                Rectangle rectangle = (Rectangle) gpNext.getChildren().get((1 + row) * 6 + col + 1);
                rectangle.setFill(blocks[row][col].color());
            }
        }
    }

    public void clearGpNext() {
        for (int row = 0; row < gpNext.getRowCount(); row++) {
            for (int col = 0; col < gpNext.getColumnCount(); col++) {
                Rectangle rectangle = (Rectangle) gpNext.getChildren().get(row * 6 + col);
                rectangle.setFill(Color.WHITE);
            }
        }
    }
}

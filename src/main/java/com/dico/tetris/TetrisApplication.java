package com.dico.tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TetrisApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        Game controller = loader.getController();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(controller::checkKeyPressed);
        primaryStage.setScene(scene);
        primaryStage.show();
        controller.draw();
    }

}

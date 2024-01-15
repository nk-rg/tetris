module com.dico.tetris {
    requires javafx.controls;
    requires javafx.fxml;

//    requires com.almasb.fxgl.all;

    opens com.dico.tetris to javafx.fxml;
    exports com.dico.tetris;
    exports com.dico.tetris.action;
    opens com.dico.tetris.action to javafx.fxml;
}
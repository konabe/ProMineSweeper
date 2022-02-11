package com.example.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MineSweeperApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var fxmlLoader = new FXMLLoader(MineSweeperApplication.class.getResource("minesweeper-view.fxml"));
        var scene = new Scene(fxmlLoader.load(), 1200, 800);
        var styleSheetPath = getClass().getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(styleSheetPath);
        stage.setTitle("Mine Sweeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
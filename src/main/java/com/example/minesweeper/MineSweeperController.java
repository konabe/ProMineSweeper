package com.example.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MineSweeperController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
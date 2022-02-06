package com.example.minesweeper;

import com.example.minesweeper.core.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private CellBoard board;
    private GridPane gridPane;
    private Button[][] buttons;

    @Override
    public void start(Stage stage) throws IOException {
        board = new CellBoard(new MineSweeperConfig(30, 20, 50), new SimpleRandomBombMapper());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        var node = (VBox)fxmlLoader.load();
        Scene scene = new Scene(node, 1200, 800);
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(0, 0, 0, 0));
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setVgap(0);
        gridPane.setHgap(0);
        buttons = new Button[board.getWidth()][board.getHeight()];
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                var button = new Button();
                button.setMinHeight(30);
                button.setMaxHeight(30);
                button.setMinWidth(30);
                button.setMaxWidth(30);
                button.setText("x");
                int finalI = i;
                int finalJ = j;
                button.setOnAction(event -> {
                    cellClicked(finalI, finalJ);
                });
                buttons[i][j] = button;
                gridPane.add(button, i, j);
            }
        }
        updateCells();
        node.getChildren().add(gridPane);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void cellClicked(int x, int y) {
        board.uncover(new Pos(x, y));
        updateCells();
    }

    private String getText(CellViewType type) {
        return switch (type) {
            case COVERED -> "■";
            case MINE -> "x";
            case ZERO -> "";
            case ONE -> "1";
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            default -> null;
        };
    }

    private void updateCells() {
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                var cell = board.getCell(new Pos(i, j));
                buttons[i][j].setText(getText(cell.getCellViewType()));
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.minesweeper;

import com.example.minesweeper.core.CellBoard;
import com.example.minesweeper.core.MineSweeperConfig;
import com.example.minesweeper.core.Pos;
import com.example.minesweeper.core.SimpleRandomBombMapper;
import com.example.minesweeper.core.cell.CellViewType;
import com.example.minesweeper.ui.CellButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MineSweeperController implements Initializable {
    private CellBoard cellBoard;
    private Button[][] buttons;

    @FXML
    private GridPane cellBoardPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cellBoard = new CellBoard(
                new MineSweeperConfig(30, 20, 100),
                new SimpleRandomBombMapper()
        );
        setupButtons();
        updateCells();
    }

    private void setupButtons() {
        buttons = new Button[cellBoard.getWidth()][cellBoard.getHeight()];
        for (int i = 0; i < cellBoard.getWidth(); i++) {
            for (int j = 0; j < cellBoard.getHeight(); j++) {
                var button = new CellButton();
                int finalI = i, finalJ = j;
                button.setOnAction(event -> {
                    cellClicked(finalI, finalJ);
                });
                button.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        cellRightClicked(finalI, finalJ);
                    }
                });
                buttons[i][j] = button;
                cellBoardPane.add(button, i, j);
            }
        }
    }

    private void cellClicked(int x, int y) {
        cellBoard.uncover(new Pos(x, y));
        updateCells();
    }

    private void cellRightClicked(int x, int y) {
        cellBoard.toggleFlag(new Pos(x, y));
        updateCells();
    }

    private ImageView getImageView(CellViewType type) {
        var imagePath = switch (type) {
            case COVERED -> null;
            case FLAG -> "img/kinzoku_tanchiki.png";
            case MINE -> "img/war_jirai.png";
            case ZERO -> null;
            case ONE -> "img/number_1.png";
            case TWO -> "img/number_2.png";
            case THREE -> "img/number_3.png";
            case FOUR -> "img/number_4.png";
            case FIVE -> "img/number_5.png";
            case SIX -> "img/number_6.png";
            case SEVEN -> "img/number_7.png";
            case EIGHT -> "img/number_8.png";
            default -> null;
        };
        if (imagePath == null) return null;
        return new ImageView(getClass().getResource(imagePath).toExternalForm());
    }

    private void updateCells() {
        for (int i = 0; i < cellBoard.getWidth(); i++) {
            for (int j = 0; j < cellBoard.getHeight(); j++) {
                var cell = cellBoard.getCell(new Pos(i, j));
                var button = buttons[i][j];
                var imageView = getImageView(cell.getCellViewType());
                if (imageView == null) {
                    if (cell.getCellViewType() == CellViewType.COVERED) {
                        button.getStyleClass().addAll(Arrays.asList("cell", "covered"));
                    }
                    if (cell.getCellViewType() == CellViewType.ZERO) {
                        button.getStyleClass().addAll(Arrays.asList("cell", "white"));
                    }
                    continue;
                }
                if (cell.getCellViewType() == CellViewType.FLAG) {
                    button.getStyleClass().addAll(Arrays.asList("cell", "covered"));
                } else {
                    button.getStyleClass().addAll(Arrays.asList("cell", "white"));
                }
                button.setGraphic(imageView);
                button.setContentDisplay(ContentDisplay.CENTER);
                imageView.fitWidthProperty().bind(button.widthProperty().divide(1.25));
                imageView.setPreserveRatio(true);
            }
        }
    }
}
package com.example.minesweeper.core;

import java.util.ArrayList;

public class SurroundingCell {
    private ArrayList<Cell> _array;

    SurroundingCell(CellBoard board, Coordinate coordinate) {
        var x = coordinate.x;
        var y = coordinate.y;
        _array = new ArrayList<>();
        _array.add(board.getCell(new Coordinate(x - 1, y - 1)));
        _array.add(board.getCell(new Coordinate(x, y - 1)));
        _array.add(board.getCell(new Coordinate(x + 1, y - 1)));
        _array.add(board.getCell(new Coordinate(x - 1, y)));
        _array.add(board.getCell(new Coordinate(x + 1, y)));
        _array.add(board.getCell(new Coordinate(x - 1, y + 1)));
        _array.add(board.getCell(new Coordinate(x, y + 1)));
        _array.add(board.getCell(new Coordinate(x + 1, y + 1)));
    }

    public int getBombAmount() {
        var bombCell = new Cell(new CellItem(-1));
        return (int) _array.stream()
                .filter(c -> c != null)
                .filter(c -> c.equals(bombCell))
                .count();
    }

}

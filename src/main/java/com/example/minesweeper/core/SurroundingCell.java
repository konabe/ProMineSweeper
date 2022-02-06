package com.example.minesweeper.core;

import java.util.ArrayList;

public class SurroundingCell {
    private final ArrayList<Cell> _array;
    private final Coordinate _centerCoordinate;

    SurroundingCell(CellBoard board, Coordinate coordinate) {
        _centerCoordinate = coordinate.clone();
        _array = new ArrayList<>();
        getCoordinates().forEach(c -> _array.add(board.getCell(c)));
    }

    public int getBombAmount() {
        var bombCell = new Cell(new CellItem(-1));
        return (int) _array.stream()
                .filter(c -> c != null)
                .filter(c -> c.equals(bombCell))
                .count();
    }

    public ArrayList<Coordinate> getCoordinates() {
        var x = _centerCoordinate.x;
        var y = _centerCoordinate.y;
        var result = new ArrayList<Coordinate>(8);
        result.add(new Coordinate(x - 1, y - 1));
        result.add(new Coordinate(x, y - 1));
        result.add(new Coordinate(x + 1, y - 1));
        result.add(new Coordinate(x - 1, y));
        result.add(new Coordinate(x + 1, y));
        result.add(new Coordinate(x - 1, y + 1));
        result.add(new Coordinate(x, y + 1));
        result.add(new Coordinate(x + 1, y + 1));
        return result;
    }

}

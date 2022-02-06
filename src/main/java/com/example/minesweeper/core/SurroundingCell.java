package com.example.minesweeper.core;

import java.util.ArrayList;

public class SurroundingCell {
    private final ArrayList<Cell> _array;
    private final Pos _centerPos;

    SurroundingCell(CellBoard board, Pos pos) {
        _centerPos = pos;
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

    public ArrayList<Pos> getCoordinates() {
        var x = _centerPos.x();
        var y = _centerPos.y();
        var result = new ArrayList<Pos>(8);
        result.add(new Pos(x - 1, y - 1));
        result.add(new Pos(x, y - 1));
        result.add(new Pos(x + 1, y - 1));
        result.add(new Pos(x - 1, y));
        result.add(new Pos(x + 1, y));
        result.add(new Pos(x - 1, y + 1));
        result.add(new Pos(x, y + 1));
        result.add(new Pos(x + 1, y + 1));
        return result;
    }

}

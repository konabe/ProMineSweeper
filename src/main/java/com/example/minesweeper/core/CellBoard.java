package com.example.minesweeper.core;

import java.util.ArrayList;

public class CellBoard {
    private int _width;
    private int _height;
    private final ICellShufflable _shuffler;
    private ArrayList<Cell> _cellsMatrix;

    CellBoard(MineSweeperConfig config, ICellShufflable shuffler) {
        _shuffler = shuffler;
        _width = config.width;
        _height = config.height;
        initialize(config.width, config.height, config.bomb);
    }

    public Cell getCell(Coordinate coordinate) {
        if (!coordinate.isValid(_width, _height)) {
            return null;
        }
        int index = getIndex(coordinate);
        if (!(0 <= index && index <= _cellsMatrix.size() - 1)) {
            return null;
        }
        return _cellsMatrix.get(index).clone();
    }

    private int getIndex(Coordinate coordinate) {
        return _width * coordinate.y + coordinate.x;
    }

    private Coordinate getCoordinate(int index) {
        return new Coordinate(index % _width, index / _width);
    }

    private void initialize(int width, int height, int bomb) {
        _cellsMatrix = new ArrayList<>();
        for(var isBomb: _shuffler.generateBombMap(width * height, bomb)) {
            var cell = isBomb ? new Cell(new CellItem(-1)): new Cell(new CellItem(0));
            _cellsMatrix.add(cell);
        }
        for(var i = 0; i < _cellsMatrix.size(); i++) {
            var cell = _cellsMatrix.get(i);
            var surroundingCell = new SurroundingCell(this, getCoordinate(i));
            if (cell.equals(new Cell(new CellItem(0)))) {
                _cellsMatrix.set(i, new Cell(new CellItem(surroundingCell.getBombAmount())));
            }
        }
    }

}

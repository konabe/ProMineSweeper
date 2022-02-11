package com.example.minesweeper.core;

import com.example.minesweeper.core.cell.Cell;

public class CellBoard {
    private final IBombMapper _bombMapper;
    private RectRepresentableArrayList<Cell> _cellsMatrix;

    public CellBoard(MineSweeperConfig config, IBombMapper bombMapper) {
        _bombMapper = bombMapper;
        initialize(config.width, config.height, config.bomb);
    }

    public int getWidth() {
        return _cellsMatrix.getWidth();
    }

    public int getHeight() {
        return _cellsMatrix.getHeight();
    }

    public Cell getCell(Pos coordinate) {
        var cell = _cellsMatrix.get(coordinate);
        return cell != null ? cell.clone() : null;
    }

    public void uncover(Pos coordinate) {
        var cell = _cellsMatrix.get(coordinate);
        if (cell == null) {
            return;
        }
        if (cell.equals(Cell.createLand())) {
            openCell(coordinate);
            var surroundingCell = new SurroundingCell(this, coordinate);
            var coordinatesNextTo = surroundingCell.getCoordinates();
            coordinatesNextTo.forEach(this::uncover);
            return;
        }
        if (cell.isOpen() && !cell.equals(Cell.createLand())) {
            return;
        }
        if (!cell.isOpen()) {
            openCell(coordinate);
            uncover(coordinate);
        }
    }

    private void initialize(int width, int height, int bomb) {
        _cellsMatrix = new RectRepresentableArrayList<>(width, height);
        for(var isBomb: _bombMapper.generate(width * height, bomb)) {
            var cell = isBomb ? Cell.createBomb(): Cell.createLand();
            _cellsMatrix.add(cell);
        }
        for(var i = 0; i < _cellsMatrix.size(); i++) {
            var cell = _cellsMatrix.get(i);
            var surroundingCell = new SurroundingCell(this, _cellsMatrix.getPos(i));
            if (cell.equals(Cell.createLand())) {
                _cellsMatrix.set(i, Cell.createLand(surroundingCell.getBombAmount()));
            }
        }
    }

    private void openCell(Pos coordinate) {
        var cell = _cellsMatrix.get(coordinate);
        cell.open();
    }

}

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

    public void uncover(Pos pos) {
        var cell = _cellsMatrix.get(pos);
        if (cell == null) return;
        if (cell.isFlag()) return;
        if (cell.equals(Cell.createLand())) {
            openCell(pos);
            var surroundingCell = new SurroundingCell(this, pos);
            var coordinatesNextTo = surroundingCell.getCoordinates();
            coordinatesNextTo.forEach(this::uncover);
            return;
        }
        if (cell.isOpen() && !cell.equals(Cell.createLand())) {
            return;
        }
        if (!cell.isOpen()) {
            openCell(pos);
            uncover(pos);
        }
    }

    public void toggleFlag(Pos pos) {
        var cell = _cellsMatrix.get(pos);
        if (cell == null) return;
        if (cell.isFlag()) {
            cell.dropFlag();
        } else {
            cell.raiseFlag();
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

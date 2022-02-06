package com.example.minesweeper.core;

public class CellBoard {
    private final IBombMapper _bombMapper;
    private RectRepresentableArrayList<Cell> _cellsMatrix;

    CellBoard(MineSweeperConfig config, IBombMapper bombMapper) {
        _bombMapper = bombMapper;
        initialize(config.width, config.height, config.bomb);
    }

    public Cell getCell(Coordinate coordinate) {
        var cell = _cellsMatrix.get(coordinate);
        return cell != null ? cell.clone() : null;
    }

    public void open(Coordinate coordinate) {
        var cell = _cellsMatrix.get(coordinate);
        if (cell == null) {
            return;
        }
        if (cell.equals(new Cell(new CellItem(0)))) {
            cell.open();
            var surroundingCell = new SurroundingCell(this, coordinate);
            var coordinatesNextTo = surroundingCell.getCoordinates();
            coordinatesNextTo.forEach(c -> open(c));
            return;
        }
        if (cell.isOpen() && !cell.equals(new Cell(new CellItem(0)))) {
            return;
        }
        if (!cell.isOpen()) {
            cell.open();
            open(coordinate);
            return;
        }
    }

    private void initialize(int width, int height, int bomb) {
        _cellsMatrix = new RectRepresentableArrayList<>(width, height);
        for(var isBomb: _bombMapper.generate(width * height, bomb)) {
            var cell = isBomb ? new Cell(new CellItem(-1)): new Cell(new CellItem(0));
            _cellsMatrix.add(cell);
        }
        for(var i = 0; i < _cellsMatrix.size(); i++) {
            var cell = _cellsMatrix.get(i);
            var surroundingCell = new SurroundingCell(this, _cellsMatrix.getCoordinate(i));
            if (cell.equals(new Cell(new CellItem(0)))) {
                _cellsMatrix.set(i, new Cell(new CellItem(surroundingCell.getBombAmount())));
            }
        }
    }

}

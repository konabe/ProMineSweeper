package com.example.minesweeper.core;

public class Cell implements Cloneable {

    private boolean _isOpen;
    private CellItem _item;

    Cell(CellItem item) {
        _item = item;
        _isOpen = false;
    }

    @Override
    protected Cell clone() {
        Cell obj = null;
        try {
            obj = (Cell)super.clone();
            obj._item = this._item.clone();
        } catch (CloneNotSupportedException e) {
            e.getStackTrace();
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;
        if (_isOpen != cell._isOpen) return false;
        return _item.equals(cell._item);
    }

    public boolean isOpen() {
        return _isOpen;
    }

    public void open() {
        _isOpen = true;
    }

    public CellViewType getCellViewType() {
        if (!_isOpen) {
            return CellViewType.COVERED;
        } else {
            int value = _item.getValue();
            switch (value) {
                case -1:
                    return CellViewType.MINE;
                case 0:
                    return CellViewType.ZERO;
                case 1:
                    return CellViewType.ONE;
                case 2:
                    return CellViewType.TWO;
                case 3:
                    return CellViewType.THREE;
                case 4:
                    return CellViewType.FOUR;
                case 5:
                    return CellViewType.FIVE;
                case 6:
                    return CellViewType.SIX;
                case 7:
                    return CellViewType.SEVEN;
                case 8:
                    return CellViewType.EIGHT;
                default:
                    throw new RuntimeException();
            }
        }
    }
}
package com.example.minesweeper.core.cell;

public class Cell implements Cloneable {
    private boolean _isOpen;
    private boolean _isFlag;
    private CellItem _item;

    private Cell(CellItem item) {
        _item = item;
        _isOpen = false;
        _isFlag = false;
    }

    public static Cell createBomb() {
        return new Cell(new CellItem(-1));
    }

    public static Cell createLand() {
        return createLand(0);
    }

    public static Cell createLand(int number) {
        if (number < 0 || number > 8) {
            return null;
        }
        return new Cell(new CellItem(number));
    }

    public boolean isOpen() {
        return _isOpen;
    }

    public void open() {
        _isOpen = true;
    }

    public boolean isFlag() { return _isFlag; }

    public void raiseFlag() {
        _isFlag = true;
    }

    public void dropFlag() { _isFlag = false; }

    public CellViewType getCellViewType() {
        if (_isOpen) {
            return _item.getCellViewType();
        }
        if (_isFlag) {
            return CellViewType.FLAG;
        }
        return CellViewType.COVERED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;
        if (_isOpen != cell._isOpen) return false;
        return _item.equals(cell._item);
    }

    /*
    Cloneable
     */
    @Override
    public Cell clone() {
        Cell obj = null;
        try {
            obj = (Cell)super.clone();
            obj._item = this._item.clone();
        } catch (CloneNotSupportedException e) {
            e.getStackTrace();
        }
        return obj;
    }
}
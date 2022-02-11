package com.example.minesweeper.core.cell;

class CellItem implements Cloneable {
    // 0: 何もなし, 1~8: 周りの爆弾の数, -1: 爆弾
    private final int _value;

    CellItem(int value) {
        _value = value;
    }

    CellViewType getCellViewType() {
        switch (_value) {
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

    @Override
    protected CellItem clone() {
        try {
            return (CellItem)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellItem cellItem = (CellItem) o;

        return _value == cellItem._value;
    }

}

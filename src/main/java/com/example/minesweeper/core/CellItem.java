package com.example.minesweeper.core;

public class CellItem implements Cloneable {
    // 0: 何もなし, 1~8: 周りの爆弾の数, -1: 爆弾
    private int _value;

    CellItem(int value) {
        _value = value;
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

    public int getValue() {
        return _value;
    }

}

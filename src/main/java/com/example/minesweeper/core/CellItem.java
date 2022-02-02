package com.example.minesweeper.core;

public class CellItem {
    // 0: 何もなし, 1~8: 周りの爆弾の数, -1: 爆弾
    private int _value;

    CellItem(int value) {
        _value = value;
    }

    public int getValue() {
        return _value;
    }
}

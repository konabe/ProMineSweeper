package com.example.minesweeper.core;

// 値オブジェクト
public class Pos implements Cloneable {
    private int _x;
    private int _y;

    public Pos(int x, int y) {
        this._x = x;
        this._y = y;
    }

    public int x() {
        return _x;
    }

    public int y() {
        return _y;
    }

    public boolean isValid(int width, int height) {
        return 0 <= _x && _x <= width -1 && 0 <= _y && _y <= height - 1;
    }
}

package com.example.minesweeper.core;

import java.util.ArrayList;

public class RectRepresentableArrayList<T> extends ArrayList<T> {
    private int _width;
    private int _height;

    RectRepresentableArrayList(int width, int height) {
        super(width * height);
        _width = width;
        _height = height;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public T get(Pos pos) {
        if (!pos.isValid(_width, _height)) {
            return null;
        }
        var index = getIndex(pos);
        if (!(0 <= index && index <= size() - 1)) {
            return null;
        }
        return super.get(index);
    }

    public Pos getPos(int index) {
        return new Pos(index % _width, index / _width);
    }

    private int getIndex(Pos pos) {
        return _width * pos.y() + pos.x();
    }
}

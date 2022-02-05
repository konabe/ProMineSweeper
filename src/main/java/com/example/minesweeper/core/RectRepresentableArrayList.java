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

    public T get(Coordinate coordinate) {
        if (!coordinate.isValid(_width, _height)) {
            return null;
        }
        var index = getIndex(coordinate);
        if (!(0 <= index && index <= size() - 1)) {
            return null;
        }
        return super.get(index);
    }

    private int getIndex(Coordinate coordinate) {
        return _width * coordinate.y + coordinate.x;
    }

    public Coordinate getCoordinate(int index) {
        return new Coordinate(index % _width, index / _width);
    }
}

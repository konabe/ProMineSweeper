package com.example.minesweeper.core;

public class Coordinate implements Cloneable {
    public int x;
    public int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isValid(int width, int height) {
        return 0 <= x && x <= width -1 && 0 <= y && y <= height - 1;
    }

    @Override
    protected Coordinate clone() {
        try {
            return (Coordinate) super.clone();
        } catch (CloneNotSupportedException e) {
            e.getStackTrace();
            return null;
        }
    }
}

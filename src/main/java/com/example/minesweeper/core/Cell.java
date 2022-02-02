package com.example.minesweeper.core;

public class Cell {
    public boolean isOpen() {
        return _isOpen;
    }

    public void open() {
        _isOpen = true;
    }

    private boolean _isOpen;

    Cell() {
        _isOpen = false;
    }

}

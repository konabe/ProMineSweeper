package com.example.minesweeper.core;

import java.util.ArrayList;

public interface ICellShufflable {
    public ArrayList<Boolean> generateBombMap(int size, int bomb);
}


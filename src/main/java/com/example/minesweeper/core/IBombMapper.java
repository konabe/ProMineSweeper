package com.example.minesweeper.core;

import java.util.ArrayList;

public interface IBombMapper {
    public ArrayList<Boolean> generate(int size, int bomb);
}


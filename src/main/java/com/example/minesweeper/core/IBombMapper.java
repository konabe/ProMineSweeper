package com.example.minesweeper.core;

import java.util.ArrayList;

public interface IBombMapper {
    ArrayList<Boolean> generate(int size, int bomb);
}


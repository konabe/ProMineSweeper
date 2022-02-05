package com.example.minesweeper.core;

public class MineSweeperConfig {
    int width;
    int height;
    int bomb;

    public MineSweeperConfig(int width, int height, int bomb) {
        this.width = width;
        this.height = height;
        this.bomb = bomb;
    }
}

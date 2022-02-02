package com.example.minesweeper.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    public void test_open() {
        Cell cell = new Cell();
        assertFalse(cell.isOpen());
        cell.open();
        assertTrue(cell.isOpen());
    }
}
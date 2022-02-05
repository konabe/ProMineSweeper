package com.example.minesweeper.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    public void test_clone() {
        var cell = new Cell(new CellItem(0));
        var copiedCell = cell.clone();
        // 副作用を起こそうとする
        copiedCell.open();
        assertTrue(copiedCell.isOpen());
        assertFalse(cell.isOpen());
    }

    @Test
    public void test_equals() {
        var cell = new Cell(new CellItem(0));
        var cell2 = new Cell(new CellItem(0));
        assertEquals(cell, cell2);
        var cell3 = new Cell(new CellItem(0));
        cell3.open();
        assertNotEquals(cell3, cell);
        var cell4 = new Cell(new CellItem(1));
        assertNotEquals(cell4, cell);
    }

    @Test
    public void test_open() {
        var item = new CellItem(0);
        var cell = new Cell(item);
        assertFalse(cell.isOpen());
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void test_getCellViewType() {
        var cell0 = new Cell(new CellItem(0));
        assertEquals(CellViewType.COVERED, cell0.getCellViewType());
        cell0.open();
        assertEquals(CellViewType.ZERO, cell0.getCellViewType());

        var cell1 = new Cell(new CellItem(1));
        assertEquals(CellViewType.COVERED, cell1.getCellViewType());
        cell1.open();
        assertEquals(CellViewType.ONE, cell1.getCellViewType());

        var cell8 = new Cell(new CellItem(8));
        assertEquals(CellViewType.COVERED, cell8.getCellViewType());
        cell8.open();
        assertEquals(CellViewType.EIGHT, cell8.getCellViewType());

        var cellMine = new Cell(new CellItem(-1));
        assertEquals(CellViewType.COVERED, cellMine.getCellViewType());
        cellMine.open();
        assertEquals(CellViewType.MINE, cellMine.getCellViewType());
    }

    @Test
    public void test_getCellViewType_error() {
        var cellError = new Cell(new CellItem(666));
        // TODO: ここはスローされるべきな気がする。
        assertDoesNotThrow(() -> cellError.getCellViewType());
        cellError.open();
        assertThrows(RuntimeException.class, () -> cellError.getCellViewType());
    }
}
package com.example.minesweeper.core.cell;

import com.example.minesweeper.core.cell.Cell;
import com.example.minesweeper.core.cell.CellViewType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    public void test_clone() {
        var cell = Cell.createLand();
        var copiedCell = cell.clone();
        // 副作用を起こそうとする
        copiedCell.open();
        assertTrue(copiedCell.isOpen());
        assertFalse(cell.isOpen());
    }

    @Test
    public void test_equals() {
        var cell = Cell.createLand();
        var cell2 = Cell.createLand();
        assertEquals(cell, cell2);
        var cell3 = Cell.createLand();
        cell3.open();
        assertNotEquals(cell3, cell);
        var cell4 = Cell.createLand(1);
        assertNotEquals(cell4, cell);
    }

    @Test
    public void test_open() {
        var cell = Cell.createLand();
        assertFalse(cell.isOpen());
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void test_getCellViewType() {
        var cell0 = Cell.createLand();
        assertEquals(CellViewType.COVERED, cell0.getCellViewType());
        cell0.open();
        assertEquals(CellViewType.ZERO, cell0.getCellViewType());

        var cell1 = Cell.createLand(1);
        assertEquals(CellViewType.COVERED, cell1.getCellViewType());
        cell1.open();
        assertEquals(CellViewType.ONE, cell1.getCellViewType());

        var cell2 = Cell.createLand(2);
        assertEquals(CellViewType.COVERED, cell2.getCellViewType());
        cell2.open();
        assertEquals(CellViewType.TWO, cell2.getCellViewType());

        var cell3 = Cell.createLand(3);
        assertEquals(CellViewType.COVERED, cell3.getCellViewType());
        cell3.open();
        assertEquals(CellViewType.THREE, cell3.getCellViewType());

        var cell4 = Cell.createLand(4);
        assertEquals(CellViewType.COVERED, cell4.getCellViewType());
        cell4.open();
        assertEquals(CellViewType.FOUR, cell4.getCellViewType());

        var cell5 = Cell.createLand(5);
        assertEquals(CellViewType.COVERED, cell5.getCellViewType());
        cell5.open();
        assertEquals(CellViewType.FIVE, cell5.getCellViewType());

        var cell6 = Cell.createLand(6);
        assertEquals(CellViewType.COVERED, cell6.getCellViewType());
        cell6.open();
        assertEquals(CellViewType.SIX, cell6.getCellViewType());

        var cell7 = Cell.createLand(7);
        assertEquals(CellViewType.COVERED, cell7.getCellViewType());
        cell7.open();
        assertEquals(CellViewType.SEVEN, cell7.getCellViewType());

        var cell8 = Cell.createLand(8);
        assertEquals(CellViewType.COVERED, cell8.getCellViewType());
        cell8.open();
        assertEquals(CellViewType.EIGHT, cell8.getCellViewType());

        var cellMine = Cell.createBomb();
        assertEquals(CellViewType.COVERED, cellMine.getCellViewType());
        cellMine.open();
        assertEquals(CellViewType.MINE, cellMine.getCellViewType());
    }

    @Test
    public void test_getCellViewType_error() {
        var cellError = Cell.createLand(666);
        assertNull(cellError);
    }
}
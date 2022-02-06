package com.example.minesweeper.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MockBombMapper implements IBombMapper {
    int generateCallCount = 0;
    @Override
    public ArrayList<Boolean> generate(int size, int bomb) {
        generateCallCount++;
        assert size == 16;
        assert bomb == 4;
        return new ArrayList<>(Arrays.asList(
                true, false,  false, true,
                false, false, false, false,
                true, true, false ,false,
                false, false, false, false
        ));
    }
}

public class CellBoardTest {
    CellBoard sampleBoard1;

    @BeforeEach
    public void beforeEach() {
        var config = new MineSweeperConfig(4, 4, 4);
        var bombMapper = new MockBombMapper();
        sampleBoard1 = new CellBoard(config, bombMapper);
    }

    @Test
    public void test_getCell() {
        assertEquals(new Cell(new CellItem(-1)), sampleBoard1.getCell(new Pos(0, 0)));
    }

    @Test
    public void test_initialize() {
        assertEquals(sampleBoard1.getCell(new Pos(0, 0)), new Cell(new CellItem((-1))));
        assertEquals(sampleBoard1.getCell(new Pos(1, 0)), new Cell(new CellItem((1))));
        assertEquals(sampleBoard1.getCell(new Pos(2, 0)), new Cell(new CellItem((1))));
        assertEquals(sampleBoard1.getCell(new Pos(3, 0)), new Cell(new CellItem((-1))));
        assertEquals(sampleBoard1.getCell(new Pos(0, 1)), new Cell(new CellItem((3))));
        assertEquals(sampleBoard1.getCell(new Pos(1, 1)), new Cell(new CellItem((3))));
        assertEquals(sampleBoard1.getCell(new Pos(2, 1)), new Cell(new CellItem((2))));
        assertEquals(sampleBoard1.getCell(new Pos(3, 1)), new Cell(new CellItem((1))));
        assertEquals(sampleBoard1.getCell(new Pos(0, 2)), new Cell(new CellItem((-1))));
        assertEquals(sampleBoard1.getCell(new Pos(1, 2)), new Cell(new CellItem((-1))));
        assertEquals(sampleBoard1.getCell(new Pos(2, 2)), new Cell(new CellItem((1))));
        assertEquals(sampleBoard1.getCell(new Pos(3, 2)), new Cell(new CellItem((0))));
        assertEquals(sampleBoard1.getCell(new Pos(0, 3)), new Cell(new CellItem((2))));
        assertEquals(sampleBoard1.getCell(new Pos(1, 3)), new Cell(new CellItem((2))));
        assertEquals(sampleBoard1.getCell(new Pos(2, 3)), new Cell(new CellItem((1))));
        assertEquals(sampleBoard1.getCell(new Pos(3, 3)), new Cell(new CellItem((0))));
    }

    @Test
    public void test_getWidth_getHeight() {
        assertEquals(4, sampleBoard1.getWidth());
        assertEquals(4, sampleBoard1.getHeight());
    }

    @Test
    public void test_uncover() {
        sampleBoard1.uncover(new Pos(0, 0));
        var cell = sampleBoard1.getCell(new Pos(0, 0));
        assertTrue(cell.isOpen());
    }

    @Test
    public void test_uncover_recursively_1() {
        sampleBoard1.uncover(new Pos(3, 2));
        assertTrue(sampleBoard1.getCell(new Pos(2, 1)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(3, 1)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(2, 2)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(3, 2)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(2, 3)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(3, 3)).isOpen());

        assertFalse(sampleBoard1.getCell(new Pos(0, 0)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 1)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 2)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 3)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 1)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(1, 1)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 2)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(1, 2)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 3)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(1, 3)).isOpen());
    }

    @Test
    public void test_uncover_recursively_2() {
        sampleBoard1.uncover(new Pos(3, 3));
        assertTrue(sampleBoard1.getCell(new Pos(2, 1)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(3, 1)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(2, 2)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(3, 2)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(2, 3)).isOpen());
        assertTrue(sampleBoard1.getCell(new Pos(3, 3)).isOpen());

        assertFalse(sampleBoard1.getCell(new Pos(0, 0)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 1)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 2)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 3)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 1)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(1, 1)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 2)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(1, 2)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(0, 3)).isOpen());
        assertFalse(sampleBoard1.getCell(new Pos(1, 3)).isOpen());
    }

}

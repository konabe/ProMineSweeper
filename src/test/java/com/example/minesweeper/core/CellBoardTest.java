package com.example.minesweeper.core;

import org.junit.jupiter.api.BeforeAll;
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
    CellBoard board;

    @BeforeEach
    public void beforeEach() {
        var config = new MineSweeperConfig(4, 4, 4);
        var bombMapper = new MockBombMapper();
        board = new CellBoard(config, bombMapper);
    }

    @Test
    public void test_getCell() {
        assertEquals(new Cell(new CellItem(-1)), board.getCell(new Coordinate(0, 0)));
    }

    @Test
    public void test_initialize() {
        assertEquals(board.getCell(new Coordinate(0, 0)), new Cell(new CellItem((-1))));
        assertEquals(board.getCell(new Coordinate(1, 0)), new Cell(new CellItem((1))));
        assertEquals(board.getCell(new Coordinate(2, 0)), new Cell(new CellItem((1))));
        assertEquals(board.getCell(new Coordinate(3, 0)), new Cell(new CellItem((-1))));
        assertEquals(board.getCell(new Coordinate(0, 1)), new Cell(new CellItem((3))));
        assertEquals(board.getCell(new Coordinate(1, 1)), new Cell(new CellItem((3))));
        assertEquals(board.getCell(new Coordinate(2, 1)), new Cell(new CellItem((2))));
        assertEquals(board.getCell(new Coordinate(3, 1)), new Cell(new CellItem((1))));
        assertEquals(board.getCell(new Coordinate(0, 2)), new Cell(new CellItem((-1))));
        assertEquals(board.getCell(new Coordinate(1, 2)), new Cell(new CellItem((-1))));
        assertEquals(board.getCell(new Coordinate(2, 2)), new Cell(new CellItem((1))));
        assertEquals(board.getCell(new Coordinate(3, 2)), new Cell(new CellItem((0))));
        assertEquals(board.getCell(new Coordinate(0, 3)), new Cell(new CellItem((2))));
        assertEquals(board.getCell(new Coordinate(1, 3)), new Cell(new CellItem((2))));
        assertEquals(board.getCell(new Coordinate(2, 3)), new Cell(new CellItem((1))));
        assertEquals(board.getCell(new Coordinate(3, 3)), new Cell(new CellItem((0))));
    }

    @Test
    public void test_open() {
        board.open(new Coordinate(0, 0));
        var cell = board.getCell(new Coordinate(0, 0));
        assertTrue(cell.isOpen());
    }

    @Test
    public void test_open_recursively_1() {
        board.open(new Coordinate(3, 2));
        assertTrue(board.getCell(new Coordinate(2, 1)).isOpen());
        assertTrue(board.getCell(new Coordinate(3, 1)).isOpen());
        assertTrue(board.getCell(new Coordinate(2, 2)).isOpen());
        assertTrue(board.getCell(new Coordinate(3, 2)).isOpen());
        assertTrue(board.getCell(new Coordinate(2, 3)).isOpen());
        assertTrue(board.getCell(new Coordinate(3, 3)).isOpen());

        assertFalse(board.getCell(new Coordinate(0, 0)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 1)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 2)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 3)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 1)).isOpen());
        assertFalse(board.getCell(new Coordinate(1, 1)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 2)).isOpen());
        assertFalse(board.getCell(new Coordinate(1, 2)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 3)).isOpen());
        assertFalse(board.getCell(new Coordinate(1, 3)).isOpen());
    }

    @Test
    public void test_open_recursively_2() {
        board.open(new Coordinate(3, 3));
        assertTrue(board.getCell(new Coordinate(2, 1)).isOpen());
        assertTrue(board.getCell(new Coordinate(3, 1)).isOpen());
        assertTrue(board.getCell(new Coordinate(2, 2)).isOpen());
        assertTrue(board.getCell(new Coordinate(3, 2)).isOpen());
        assertTrue(board.getCell(new Coordinate(2, 3)).isOpen());
        assertTrue(board.getCell(new Coordinate(3, 3)).isOpen());

        assertFalse(board.getCell(new Coordinate(0, 0)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 1)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 2)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 3)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 1)).isOpen());
        assertFalse(board.getCell(new Coordinate(1, 1)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 2)).isOpen());
        assertFalse(board.getCell(new Coordinate(1, 2)).isOpen());
        assertFalse(board.getCell(new Coordinate(0, 3)).isOpen());
        assertFalse(board.getCell(new Coordinate(1, 3)).isOpen());
    }

}

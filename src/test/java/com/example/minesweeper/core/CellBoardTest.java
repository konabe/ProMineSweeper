package com.example.minesweeper.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MiniShuffler implements ICellShufflable {
    int generateBombMapCallCount = 0;
    @Override
    public ArrayList<Boolean> generateBombMap(int size, int bomb) {
        generateBombMapCallCount++;
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
    @Test
    public void test_initialize() {
        var config = new MineSweeperConfig(4, 4, 4);
        var shuffler = new MiniShuffler();
        var board = new CellBoard(config, shuffler);
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
}

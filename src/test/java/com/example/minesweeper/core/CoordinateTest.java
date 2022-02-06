package com.example.minesweeper.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordinateTest {
    @Test
    public void test_isValid() {
        var c1 = new Coordinate(2, 2);
        assertTrue(c1.isValid(3, 4));
        var c2 = new Coordinate(0, 2);
        assertTrue(c2.isValid(3, 4));
        var c3 = new Coordinate(2, 0);
        assertTrue(c3.isValid(3, 4));
        var c4 = new Coordinate(2, 2);
        assertTrue(c4.isValid(3, 4));
        var c5 = new Coordinate(2, 3);
        assertTrue(c5.isValid(3, 4));

        var c6 = new Coordinate(-1, 2);
        assertFalse(c6.isValid(3, 4));
        var c7 = new Coordinate(2, -1);
        assertFalse(c7.isValid(3, 4));
        var c8 = new Coordinate(3, 2);
        assertFalse(c8.isValid(3, 4));
        var c9 = new Coordinate(2, 4);
        assertFalse(c9.isValid(3, 4));
    }
}

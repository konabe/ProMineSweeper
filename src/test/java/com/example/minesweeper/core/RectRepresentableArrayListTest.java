package com.example.minesweeper.core;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectRepresentableArrayListTest {
    @Test
    public void test() {
        int width = 3;
        int height = 4;
        var list = new RectRepresentableArrayList<Integer>(width, height);
        list.addAll(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9,
                10, 11, 12
        ));
        assertEquals(1, list.get(new Pos(0, 0)));
        assertEquals(2, list.get(new Pos(1, 0)));
        assertEquals(3, list.get(new Pos(2, 0)));
        assertEquals(4, list.get(new Pos(0, 1)));
        assertEquals(5, list.get(new Pos(1, 1)));
        assertEquals(6, list.get(new Pos(2, 1)));
        assertEquals(7, list.get(new Pos(0, 2)));
        assertEquals(8, list.get(new Pos(1, 2)));
        assertEquals(9, list.get(new Pos(2, 2)));
        assertEquals(10, list.get(new Pos(0, 3)));
        assertEquals(11, list.get(new Pos(1, 3)));
        assertEquals(12, list.get(new Pos(2, 3)));
    }

    @Test
    public void test_getPos() {
        int width = 3;
        int height = 4;
        var list = new RectRepresentableArrayList<Integer>(width, height);
        var pos = list.getPos(4);
        assertEquals(1, pos.x());
        assertEquals(1, pos.y());
    }
}

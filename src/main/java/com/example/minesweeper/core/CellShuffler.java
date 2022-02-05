package com.example.minesweeper.core;

import java.util.ArrayList;
import java.util.Collections;

public class CellShuffler implements ICellShufflable {
    @Override
    public ArrayList<Boolean> generateBombMap(int size, int bomb) {
        var array = new ArrayList<Boolean>();
        for(int i = 0; i < size; i++) {
            if(i < bomb) {
                array.add(true);
            } else {
                array.add(false);
            }
        }
        Collections.shuffle(array);
        return array;
    }
}

package com.example.minesweeper.core;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleRandomBombMapper implements IBombMapper {
    @Override
    public ArrayList<Boolean> generate(int size, int bomb) {
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

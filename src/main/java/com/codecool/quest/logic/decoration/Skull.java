package com.codecool.quest.logic.decoration;

import com.codecool.quest.logic.Cell;

public class Skull extends Decor {
    public Skull (Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skull";
    }
}

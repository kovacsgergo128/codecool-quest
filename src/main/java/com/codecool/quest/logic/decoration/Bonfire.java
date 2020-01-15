package com.codecool.quest.logic.decoration;

import com.codecool.quest.logic.Cell;

public class Bonfire extends Decor {
    public Bonfire(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bonfire";
    }
}

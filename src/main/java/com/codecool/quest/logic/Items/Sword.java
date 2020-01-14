package com.codecool.quest.logic.Items;

import com.codecool.quest.logic.Cell;

public class Sword extends Items {

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}

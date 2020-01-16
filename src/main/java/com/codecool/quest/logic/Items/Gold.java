package com.codecool.quest.logic.Items;

import com.codecool.quest.logic.Cell;

public class Gold extends Items {
    public Gold(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "gold";
    }
}

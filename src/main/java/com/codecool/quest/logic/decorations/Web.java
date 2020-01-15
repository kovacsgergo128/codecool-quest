package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;

public class Web extends Decor{
    public Web(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "web";
    }
}

package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Web extends Decor{
    @JsonCreator
    public Web() {
    }

    public Web(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "web";
    }
}

package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;
import com.fasterxml.jackson.annotation.JsonCreator;

public class GraveStone extends Decor{
    @JsonCreator
    public GraveStone() {
    }

    public GraveStone(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "grave";
    }
}

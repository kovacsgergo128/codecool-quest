package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Skull extends Decor {
    @JsonCreator
    public Skull() {
    }

    public Skull (Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skull";
    }
}

package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Bonfire extends Decor {
    @JsonCreator
    public Bonfire() {
    }

    public Bonfire(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bonfire";
    }
}

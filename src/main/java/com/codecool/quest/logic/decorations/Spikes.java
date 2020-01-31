package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Spikes extends Decor {
    @JsonCreator
    public Spikes() {
    }

    public Spikes(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "spikes";
    }
}

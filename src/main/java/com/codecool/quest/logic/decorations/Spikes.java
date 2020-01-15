package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;

public class Spikes extends Decor {
    public Spikes(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "spikes";
    }
}

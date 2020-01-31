package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;

public abstract class Decor implements Drawable {
    @JsonBackReference
    private Cell cell;

    @JsonCreator
    public Decor() {
    }

    public Decor(Cell cell) {
        this.cell = cell;
        this.cell.setDecor(this);
    }
}

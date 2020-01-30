package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.fasterxml.jackson.annotation.JsonBackReference;

public abstract class Decor implements Drawable {
    @JsonBackReference
    private Cell cell;

    public Decor(Cell cell) {
        this.cell = cell;
        this.cell.setDecor(this);
    }
}

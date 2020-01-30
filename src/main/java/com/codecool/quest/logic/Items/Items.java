package com.codecool.quest.logic.Items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.fasterxml.jackson.annotation.JsonBackReference;

public abstract class Items implements Drawable {
    @JsonBackReference
    private Cell cell;

    public Items(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }
}

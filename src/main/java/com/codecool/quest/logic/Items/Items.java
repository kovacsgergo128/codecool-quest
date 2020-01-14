package com.codecool.quest.logic.Items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class Items implements Drawable {
    private Cell cell;

    public Items(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }
}

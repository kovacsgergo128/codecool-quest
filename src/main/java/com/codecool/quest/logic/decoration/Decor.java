package com.codecool.quest.logic.decoration;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class Decor implements Drawable {
    private Cell cell;

    public Decor(Cell cell) {
        this.cell = cell;
        this.cell.setDecor(this);
    }
}

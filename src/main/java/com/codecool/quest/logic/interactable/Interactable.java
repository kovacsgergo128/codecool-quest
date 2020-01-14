package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class Interactable implements Drawable {
    private Cell cell;

    public Interactable(Cell cell) {
        this.cell = cell;
        this.cell.setInteractable(this);
    }
}

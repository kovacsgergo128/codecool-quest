package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Door implements Drawable {
    @JsonBackReference
    private Cell cell;
    private boolean isLocked = true;

    public boolean isLocked() {
        return isLocked;
    }

    public void openDoor() {
        isLocked = !isLocked;
    }

    public Door(Cell cell) {
        this.cell = cell;
        this.cell.setDoor(this);
    }

    @Override
    public String getTileName() {
        return isLocked ? "door" : "door_open";
    }
}

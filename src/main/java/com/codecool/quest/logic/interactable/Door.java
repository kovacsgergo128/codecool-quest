package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public class Door implements Drawable {
    private Cell cell;
    private boolean isOpen = false;

    public Door (Cell cell) {
        this.cell = cell;
    }

    public void openDoor() {
        this.isOpen = true;
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public String getTileName() {
        return isOpen ? "openDoor" : "lockedDoor";
    }
}

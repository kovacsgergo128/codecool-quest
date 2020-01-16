package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public class Stairs implements Drawable {
    private Cell cell;
    private String tileName;
    private String toLevel;


    public Stairs(Cell cell, String level, String tileName) {
        this.cell = cell;
        this.toLevel = level;
        this.tileName = tileName;
        this.cell.setStairs(this);
    }

    @Override
    public String getTileName() {
        return this.tileName;
    }

    public String getLevel() {
        return this.toLevel;
    }
}

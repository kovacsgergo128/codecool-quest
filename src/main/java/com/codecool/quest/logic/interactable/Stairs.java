package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Stairs implements Drawable {
    @JsonBackReference
    private Cell cell;
    private String tileName;
    private int toLevel;


    public Stairs(Cell cell, int level, String tileName) {
        this.cell = cell;
        this.toLevel = level;
        this.tileName = tileName;
        this.cell.setStairs(this);
    }

    @Override
    public String getTileName() {
        return this.tileName;
    }

    public int getLevel() {
        return this.toLevel;
    }
}

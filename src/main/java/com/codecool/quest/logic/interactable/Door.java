package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;

public class Door extends Interactable {
    public Door(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "door";
    }
}


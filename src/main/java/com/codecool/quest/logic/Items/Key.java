package com.codecool.quest.logic.Items;

import com.codecool.quest.logic.Cell;

public class Key extends Items {
    public Key(Cell cell) { super(cell); }

    @Override
    public String getTileName() {
        return "key";
    }
}

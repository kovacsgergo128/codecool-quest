package com.codecool.quest.logic.Items;

import com.codecool.quest.logic.Cell;

public class Crown extends Items{
    public Crown(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return ("crown");
    }
}

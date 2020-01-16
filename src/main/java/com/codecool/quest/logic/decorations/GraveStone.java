package com.codecool.quest.logic.decorations;

import com.codecool.quest.logic.Cell;

public class GraveStone extends Decor{
    public GraveStone(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "grave";
    }
}

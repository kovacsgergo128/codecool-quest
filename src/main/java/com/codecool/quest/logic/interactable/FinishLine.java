package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public class FinishLine implements Drawable {
    private Cell cell;

    public FinishLine(Cell cell) {
        this.cell = cell;
        this.cell.setFinish(this);
    }


    @Override
    public String getTileName() {
        return "finish";
    }
}

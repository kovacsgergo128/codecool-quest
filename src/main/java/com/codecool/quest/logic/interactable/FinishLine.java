package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public class FinishLine implements Drawable {
    private Cell cell;
    private boolean hasWon = false;

    public FinishLine(Cell cell) {
        this.cell = cell;
        this.cell.setFinish(this);
    }

    public void winGame(){
        hasWon = !hasWon;
    }

    @Override
    public String getTileName() {
        return "finish";
    }
}

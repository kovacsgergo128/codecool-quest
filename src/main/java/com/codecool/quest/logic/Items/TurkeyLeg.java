package com.codecool.quest.logic.Items;

import com.codecool.quest.logic.Cell;

public class TurkeyLeg extends Items{
    public TurkeyLeg(Cell cell) { super(cell); }

    @Override
    public String getTileName() {
        return "turkey leg";
    }
}

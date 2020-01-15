package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Skeleton extends Npc {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}

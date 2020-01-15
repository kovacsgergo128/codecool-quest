package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Player extends Actor {
    private String name = "Player";
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

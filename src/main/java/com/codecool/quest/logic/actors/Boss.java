package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Direction;
import com.codecool.quest.logic.Cell;

import java.util.ArrayList;

public class Boss extends Npc{

    public Boss(Cell cell) {
        super(cell);
        this.setHealth(20);
        this.setDefaultAttackPower(3);
    }

    @Override
    public String getTileName() {
        return "boss";
    }

    @Override
    public void moveAi() {
        ArrayList<int[]> validMoves = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Cell neighbor = cell.getNeighbor(direction);
            Actor actor = neighbor.getActor();
            if (actor instanceof Player) {
                if (((Player) actor).getInventory().contains("gold")) {
                    this.move(Direction.EAST);
                    ((Player) actor).getInventory().removeItemByItemName("gold");
                }else {
                    this.attack(actor);
                }
            }
        }
    }
}

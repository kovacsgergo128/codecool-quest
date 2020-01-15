package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

import java.util.ArrayList;

public class Boss extends Npc{

    public Boss(Cell cell) {
        super(cell);
        this.setHealth(20);
    }

    @Override
    public String getTileName() {
        return "boss";
    }

    @Override
    public void attack(Actor actor){
        actor.changeHealth(-3);
    }

    @Override
    public void moveAi() {
        final int X = 0, Y = 1;
        int[][] directions = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };
        ArrayList<int[]> validMoves = new ArrayList<>();
        for (int[] direction : directions) {
            Cell neighbor = cell.getNeighbor(direction[X], direction[Y]);
            if (neighbor.getActor() instanceof Player) {
                this.attack(neighbor.getActor());
            }
        }
    }
}

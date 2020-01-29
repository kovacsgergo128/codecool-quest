package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Direction;
import com.codecool.quest.logic.Cell;

import java.util.ArrayList;
import java.util.Random;

public class Skeleton extends Npc {
    public Skeleton(Cell cell) {
        super(cell);
        this.setDefaultAttackPower(2);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void moveAi(){
        ArrayList<Direction> validMoves = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Cell neighbor = cell.getNeighbor(direction);
            if (neighbor != null && neighbor.getActor() instanceof Player){
                this.attack(neighbor.getActor());
                return;
            }
            if (neighbor != null && neighbor.isValidDest()) {
                validMoves.add(direction);
            }
        }
        Random random = new Random();
        try {
            Direction direction = validMoves.get(random.nextInt(Direction.values().length));
            move(direction);
        }
        catch (Exception ignored) {
        }
    }
}

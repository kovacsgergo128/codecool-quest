package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

import java.util.ArrayList;
import java.util.Random;

public class Skeleton extends Npc {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void moveAi(){
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
            if (neighbor.getActor() instanceof Player){
//                #TODO Damaging methode HERE!
                return;
            }
            if (neighbor.isValidDest() && neighbor.isValidMove(direction[X], direction[Y])) {
                validMoves.add(direction);
            }
        }
        Random random = new Random();
        try {
            int[] vector = validMoves.get(random.nextInt(directions.length - 1));
            move(vector[X], vector[Y]);
        } catch (Exception e) {
        }
    }

}

package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

import java.util.ArrayList;
import java.util.Random;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell != null && nextCell.isValidDest()) { // if the next cell is unoccupied
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

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
            if (cell.getNeighbor(direction[X], direction[Y]).isValidDest() && cell.getNeighbor(direction[X], direction[Y]).isValidMove(direction[X], direction[Y])) {
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

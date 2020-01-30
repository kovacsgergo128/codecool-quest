package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Direction;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.fasterxml.jackson.annotation.JsonBackReference;

public abstract class Actor implements Drawable {
    @JsonBackReference
    protected Cell cell;
    private int health = 10;
    private int defaultAttackPower;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private boolean alive = true;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public Cell move(Direction direction) {
        Cell nextCell = cell.getNeighbor(direction);
        if (nextCell != null && nextCell.isValidDest()) { // if the next cell is unoccupied
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        return nextCell;
    }

    public void changeHealth(int hp){
        this.health += hp;
        onHealthChange();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int num) {
        this.health = num;
        onHealthChange();
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

    public void moveAi() { }

    public void onHealthChange(){
        if (this.health <= 0){
            this.alive = false;
        }
    }

    public void attack(Actor actor){
        actor.changeHealth(-defaultAttackPower);
    }

    public int getDefaultAttackPower() {
        return defaultAttackPower;
    }

    public void setDefaultAttackPower(int defaultAttackPower) {
        this.defaultAttackPower = defaultAttackPower;
    }
}

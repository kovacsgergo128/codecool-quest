package com.codecool.quest.logic;

import com.codecool.quest.logic.Items.Items;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.decorations.Decor;
import com.codecool.quest.logic.interactable.Door;
import com.codecool.quest.logic.interactable.FinishLine;
import com.codecool.quest.logic.interactable.Stairs;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonValue;


public class Cell implements Drawable {

    private CellType type;

    @JsonManagedReference
    private Actor actor;
    @JsonManagedReference
    private Decor decor;
    @JsonManagedReference
    private Door door;
    @JsonManagedReference
    private Stairs stairs;
    @JsonManagedReference
    private FinishLine finish;

    @JsonBackReference
    private GameMap gameMap;

    @JsonManagedReference
    private Items item;
    private int x, y;

    public Cell() {
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public FinishLine getFinish() {
        return finish;
    }

    public void setFinish(FinishLine finish) {
        this.finish = finish;
    }

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Cell(int x, int y, CellType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void removeActor(){
        this.actor = null;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public Actor getActor() {
        try {
            return actor;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Cell getNeighbor(Direction direction) {
        if (isValidMove(direction)) {
            return gameMap.getCell(x + direction.getDx(), y + direction.getDy());
        } else {
            return null;
        }
    }
    @JsonIgnore
    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private boolean isValidMove(Direction direction) {
        return !(
                this.getX() + direction.getDx() < 0 ||
                this.getX() + direction.getDx() >= this.gameMap.getWidth() ||
                this.getY() + direction.getDy() < 0 ||
                this.getY() + direction.getDy() >= this.gameMap.getHeight()
        );
    }
    @JsonIgnore
    public boolean isValidDest() {
        return !(
                this.type.equals(CellType.WALL) ||
                this.type.equals(CellType.EMPTY) ||
                !(getActor() == null) ||
                (this.door != null && this.door.isLocked())
        );
    }

    public Door getDoor() {
        try {
            return door;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public void setStairs(Stairs stairs) {
        this.stairs = stairs;
    }
    public Stairs getStairs() {
        return this.stairs;
    }

    public Decor getDecor() {
        return decor;
    }

    public void setDecor(Decor decor) { this.decor = decor; }
}

package com.codecool.quest.logic;

import com.codecool.quest.logic.Items.Items;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.decorations.Decor;
import com.codecool.quest.logic.interactable.Door;

public class Cell implements Drawable {
    private Decor decor;
    private CellType type;
    private Actor actor;
    private Door door;
    private GameMap gameMap;
    private Items item;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
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

    public Cell getNeighbor(int dx, int dy) {
        if (isValidMove(dx,dy)) {
            return gameMap.getCell(x + dx, y + dy);
        } else {
            return null;
        }
    }

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

    public boolean isValidMove(int dirX, int dirY) {
        return !(this.getX() + dirX < 0 || this.getX() + dirX >= this.gameMap.getWidth() || this.getY() + dirY < 0 || this.getY() + dirY >= this.gameMap.getHeight());
    }

    public boolean isValidDest() {
        return !(this.type.equals(CellType.WALL) || this.type.equals(CellType.EMPTY) || !(getActor() == null) || (this.door != null && this.door.isLocked()));
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

    public Decor getDecor() {
        return decor;
    }

    public void setDecor(Decor decor) { this.decor = decor; }
}

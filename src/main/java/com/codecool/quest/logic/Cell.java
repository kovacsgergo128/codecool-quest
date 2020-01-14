package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Actor;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
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

    public Actor getActor() {
        try {
            return actor;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
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
        return !(this.type.equals(CellType.WALL) || this.type.equals(CellType.EMPTY) || !(getActor() == null));
    }
}

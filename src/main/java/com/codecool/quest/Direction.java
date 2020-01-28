package com.codecool.quest;

public enum Direction {

    NORTH (0, -1),
    SOUTH (0, 1),
    WEST (-1,0),
    EAST (1, 0);

    private int dx;
    private int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}

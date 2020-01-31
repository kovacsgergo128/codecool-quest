package com.codecool.quest.logic;

public enum LEVEL {
    LEVEL1 (0, "/map.txt"),
    LEVEL2 (1, "/map2.txt"),
    LEVEL3 (2, "/map2.txt"),
    LEVEL4 (3, "/map3.txt");

    private final int levelIndex;
    private final String levelMap;

    LEVEL(int levelIndex, String levelMap) {
        this.levelIndex = levelIndex;
        this.levelMap = levelMap;
    }

    public int getLevelIndex() {
        return levelIndex;
    }

    public String getLevelMap() {
        return levelMap;
    }
}

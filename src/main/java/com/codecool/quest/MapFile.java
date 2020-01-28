package com.codecool.quest;

public enum MapFile {
    LEVEL1 (0, "/map.txt"),
    LEVEL2 (1, "/map2.txt"),
    LEVEL3 (2, "/map3.txt");

    private final int levelIndex;
    private final String levelMap;

    MapFile(int levelIndex, String levelMap) {
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

package com.codecool.quest.logic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    @JsonValue
    public String getTileName() {
        return tileName;
    }
}

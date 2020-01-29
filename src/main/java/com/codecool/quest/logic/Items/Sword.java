package com.codecool.quest.logic.Items;

import com.codecool.quest.logic.Cell;

public class Sword extends Items {

    private static final int EXTRA_ATTACK_POWER = 5;

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }

    public static int getExtraAttackPower() {
        return EXTRA_ATTACK_POWER;
    }
}

package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.Items.Key;

public class Player extends Actor {
    private String name = "Player";
    Inventory inventory = new Inventory();

    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "player";
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void pickItem() {
        this.inventory.addItem(this.cell.getItem());
        this.cell.setItem(null);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell != null && nextCell.getDoor() != null && nextCell.getDoor().isLocked() && this.inventory.contains("key")) {
            this.inventory.removeItemByItemName("key");
            nextCell.getDoor().openDoor();
        } else if (nextCell != null && nextCell.getDecor() != null && (nextCell.getDecor().getTileName().equals("bonfire") || nextCell.getDecor().getTileName().equals("spikes"))) {
            this.setHealth(this.getHealth() - 2);
        }
        super.move(dx, dy);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

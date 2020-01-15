package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.Items.Key;

public class Player extends Actor {
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
        if (nextCell.getActor() instanceof Npc){
            attack(nextCell.getActor());
            return;
        }
        if (nextCell.getDoor() != null && nextCell.getDoor().isLocked() && this.inventory.contains("key")) {
            this.inventory.removeItemByItemName("key");
            nextCell.getDoor().openDoor();
        }
        super.move(dx, dy);
    }
    @Override
    public void attack(Actor enemy) {
//        this.changeHealth(-2);
        enemy.attack(this);
        enemy.changeHealth(this.inventory.contains("sword")?-10:-5);
    }

}

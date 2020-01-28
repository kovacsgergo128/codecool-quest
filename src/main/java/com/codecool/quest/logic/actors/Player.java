package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Direction;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;

public class Player extends Actor {
    private String name = "Player";
    Inventory inventory = new Inventory();
    private boolean god = false;

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
    public Cell move(Direction direction) {
        Cell nextCell = cell.getNeighbor(direction);
        if (nextCell != null && nextCell.getActor() instanceof Npc) {
            attack(nextCell.getActor());
            return nextCell;
        }
        if (nextCell != null &&
                nextCell.getDoor() != null &&
                nextCell.getDoor().isLocked() &&
                this.inventory.contains("key")) {
            this.inventory.removeItemByItemName("key");
            nextCell.getDoor().openDoor();
        } else if (nextCell != null &&
                nextCell.getDecor() != null &&
                !this.god &&
                (nextCell.getDecor().getTileName().equals("bonfire") ||
                        nextCell.getDecor().getTileName().equals("spikes"))) {
            this.setHealth(this.getHealth() - 2);
        }
        if (name.equals("Max")) {
            if (nextCell != null) { // if the next cell is unoccupied
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
        } else if (nextCell != null && nextCell.isValidDest()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        return nextCell;
    }

    @Override
    public void attack(Actor enemy) {
        enemy.changeHealth(this.inventory.contains("sword") ? -10 : -5);
    }

    @Override
    public void changeHealth(int hp) {
        if (hp < 0 && god)  return;
        super.changeHealth(hp);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setGod(boolean god) {
        this.god = god;
    }
    public boolean getGod() {
        return this.god;
    }
    public Object[] getAttributesForLevelChange() {
        return new Object[]{
                this.getName(),
                this.getHealth(),
                this.getInventory(),
                this.getGod()
        };
    }
    public void setAttributesOnNewLevel(Object[] attributes) {
        this.setName((String) attributes[0]);
        this.setHealth((int) attributes[1]);
        this.setInventory((Inventory) attributes[2]);
        this.setGod((boolean) attributes[3]);
    }
}

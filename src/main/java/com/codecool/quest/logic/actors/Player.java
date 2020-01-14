package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.actors.Actor;

public class Player extends Actor {
    Inventory inventory = new Inventory();
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public Inventory getInventory() {
        return inventory;
    }

    //@Override
    // public void move(int dx, int dy) {
        //super.move(dx, dy);
        //if (super.cell.getItem() != null) {
            //this.inventory.addItem(cell.getItem());
            //this.cell.setItem(null);
       // }
    //}
    public void pickItem() {
        this.inventory.addItem(this.cell.getItem());
        this.cell.setItem(null);
    }
}

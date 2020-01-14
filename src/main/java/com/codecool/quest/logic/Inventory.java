package com.codecool.quest.logic;

import com.codecool.quest.logic.Items.Items;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Items> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Items item){
        items.add(item);
    }

    public void removeItem(Items item){
        items.remove(item);
    }

    public void removeItemByItemName(String itemName){
        for (Items item: items) {
            if (item.getTileName().equals(itemName)) {
                removeItem(item);
                break;
            }
        }
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public boolean contains(String itemName) {
        for (Items item: items) {
            if (item.getTileName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }



}

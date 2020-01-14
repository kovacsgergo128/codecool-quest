package com.codecool.quest.logic;

import com.codecool.quest.logic.items.Items;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Items> items;

    public Inventory() {
        items = new ArrayList<Items>();
    }

    public void addItem(Items item){
        items.add(item);
    }

    public void removeItem(Items item){
        items.remove(item);
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }



}

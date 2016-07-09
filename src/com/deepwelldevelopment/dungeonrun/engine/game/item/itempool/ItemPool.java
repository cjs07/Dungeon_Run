package com.deepwelldevelopment.dungeonrun.engine.game.item.itempool;

import com.deepwelldevelopment.dungeonrun.engine.game.item.Item;

import java.util.ArrayList;

public class ItemPool {

    ArrayList<Item> items;

    public ItemPool(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}

package com.deepwelldevelopment.dungeonrun.engine;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.item.Item;
import com.deepwelldevelopment.dungeonrun.gui.GameFrame;

public class DungeonRun {

    public static void main (String[] args) {
        GameFrame gf = new GameFrame();
        Character.initCharacters();
        Entity.initializeEntityLibrary();
        Item.initializeItems();
    }
}

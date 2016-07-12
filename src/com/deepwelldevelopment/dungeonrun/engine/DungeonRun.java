package com.deepwelldevelopment.dungeonrun.engine;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.item.Item;
import com.deepwelldevelopment.dungeonrun.gui.GameFrame;
import com.deepwelldevelopment.dungeonrun.lib.Library;

public class DungeonRun {

    public static Library library;

    public static void main (String[] args) {
        library = new Library();
        GameFrame gf = new GameFrame();
        Character.initCharacters();
        Entity.initializeEntityLibrary();
        Item.initializeItems();
    }
}

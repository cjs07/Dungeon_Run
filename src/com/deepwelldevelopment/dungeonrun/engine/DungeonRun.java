package com.deepwelldevelopment.dungeonrun.engine;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.gui.GameFrame;

public class DungeonRun {

    public static void main (String[] args) {
        GameFrame gf = new GameFrame();
        Character.initCharacters();
    }
}

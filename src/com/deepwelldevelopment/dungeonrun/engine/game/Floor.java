package com.deepwelldevelopment.dungeonrun.engine.game;

import com.deepwelldevelopment.dungeonrun.engine.prefab.Prefab;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import java.util.ArrayList;
import java.util.Random;

public class Floor {

    Room[][] layout;
    Room currentRoom;

    public Floor(int[][] layout) {
        this.layout = new Room[layout.length][layout[0].length];
        ArrayList<Prefab> roomLayouts = Run.instance.getPrefabsForFloor();
        Random random = new Random(Run.instance.getGenerator().generateRandomSeed());

        for (int x = 0; x < layout.length; x++) {
            for (int y = 0; y < layout.length; y++) {
                if (layout[x][y] == 0) {
                    this.layout[x][y] = null;
                } else {

                }
            }
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void moveRoom(int direction) {

    }
}

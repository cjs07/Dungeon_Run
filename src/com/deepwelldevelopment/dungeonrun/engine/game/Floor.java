package com.deepwelldevelopment.dungeonrun.engine.game;

import com.deepwelldevelopment.dungeonrun.engine.prefab.Prefab;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;
import com.deepwelldevelopment.dungeonrun.gui.GamePanel;

public class Floor {

    Room[][] layout;
    Room currentRoom;

    public Floor(int[][] layout, Prefab[][] prefabLayout) {
        this.layout = new Room[layout.length][layout[0].length];

        for (int x = 0; x < layout.length; x++) {
            for (int y = 0; y < layout.length; y++) {
                if (layout[x][y] == 0) {
                    this.layout[x][y] = null;
                } else {
                    this.layout[x][y] = prefabLayout[x][y].toRoom();
                }

                if (layout[x][y] == 2) {
                    currentRoom = this.layout[x][y];
                }
            }
        }
        Run.instance.getPlayer().setX(GamePanel.width/2);
        Run.instance.getPlayer().setY(GamePanel.height/2);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void moveRoom(int direction) {

    }
}

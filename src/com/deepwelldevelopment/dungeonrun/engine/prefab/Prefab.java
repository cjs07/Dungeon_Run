package com.deepwelldevelopment.dungeonrun.engine.prefab;

import com.deepwelldevelopment.dungeonrun.engine.game.Room;

import java.awt.*;

public class Prefab {

    Image visual;
    int[][] gridData;
    int[][] entityGrid;

    public Prefab(Image visual, int[][] gridData, int[][] entityGrid) {
        this.visual = visual;
        this.gridData = gridData;
        this.entityGrid = entityGrid;
    }

    public Room toRoom() {
        return new Room(visual, gridData, entityGrid);
    }
}

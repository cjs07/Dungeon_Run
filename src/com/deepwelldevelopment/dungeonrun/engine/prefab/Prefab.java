package com.deepwelldevelopment.dungeonrun.engine.prefab;

import com.deepwelldevelopment.dungeonrun.engine.game.Room;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

public class Prefab {

    int[][] entityGrid;
    ArrayList<Entity> specialEntities;
    private Image visual;
    private int[][] gridData;

    Prefab(Image visual, int[][] gridData, int[][] entityGrid) {
        this.visual = visual;
        this.gridData = gridData;
        this.entityGrid = entityGrid;

        specialEntities = new ArrayList<>();
    }

    public Room toRoom() {
        return new Room(visual, gridData, entityGrid, this);
    }

    public void addSpecialEntities(Room room) {
        specialEntities.forEach(room::addEntity);
    }
}

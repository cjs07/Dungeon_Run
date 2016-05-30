package com.deepwelldevelopment.dungeonrun.engine.game;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

public class Room {

    Image display;
    int[][] grid;
    int[][] entityGrid;
    ArrayList<Entity> entities;
    //TODO: EntityPlayer player


    public Room(Image display, int[][] grid, int[][] entityGrid) {
        this.display = display;
        this.grid = grid;
        this.entityGrid = entityGrid;
        entities = new ArrayList<>();

        for (int x = 0; x < entityGrid.length; x++) {
            for (int y = 0; y < entityGrid[0].length; y++) {
                int value = entityGrid[x][y];
                if (value != -1) {
                    entities.add(Entity.gameEntities.get(entityGrid[x][y]));
                }
            }
        }
    }

    public void update() {

    }

    public void playerEnter() {

    }

    public void playerExit() {

    }
}

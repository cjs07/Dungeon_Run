package com.deepwelldevelopment.dungeonrun.engine.game;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Room {

    Image display;
    int[][] grid;
    int[][] entityGrid;
    ArrayList<Entity> entities;
    EntityPlayer player;


    public Room(Image display, int[][] grid, int[][] entityGrid) {
        this.display = display;
        this.grid = grid;
        this.entityGrid = entityGrid;
        entities = new ArrayList<>();
        player = Run.instance.getPlayer();

        for (int x = 0; x < entityGrid.length; x++) {
            for (int y = 0; y < entityGrid[0].length; y++) {
                int value = entityGrid[x][y];
                if (value != -1) {
                    entities.add(Entity.gameEntities.get(entityGrid[x][y]));
                }
            }
        }
    }

    public void draw(JPanel source, Graphics g) {
        int blankSpaceX = source.getWidth() - display.getWidth(null);
        int blankSpaceY = source.getHeight() - display.getHeight(null);
        int offsetX = blankSpaceX/2;
        int offsetY = blankSpaceY/2;
        g.drawImage(display, offsetX, offsetY, null);

        g.drawImage(player.getImage(), player.getX(), player.getY(), null);

        //TODO: DRAW OBSTACLE LAYER
        //TODO: DRAW ENTITIES
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void update() {
        player = Run.instance.getPlayer();
        entities.forEach(Entity::update);
    }

    public void playerEnter() {

    }

    public void playerExit() {

    }
}

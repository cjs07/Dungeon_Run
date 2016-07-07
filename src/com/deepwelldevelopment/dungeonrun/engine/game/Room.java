package com.deepwelldevelopment.dungeonrun.engine.game;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.EntityDamagable;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;
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

    private boolean showHitboxes = true;

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
                    Entity toAdd = Entity.gameEntities.get(entityGrid[x][y]);
                    toAdd.setX(x*(display.getWidth(null)/entityGrid.length)).setY(y*(display.getHeight(null)/entityGrid[0].length));
                    entities.add(toAdd);
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
        for (Entity e : entities) {
            g.drawImage(e.getImage(), e.getX(), e.getY(), null);
            if (showHitboxes) {
                if (e instanceof EntityDamagable) {
                    EntityDamagable ed = (EntityDamagable)e;
                    Rectangle r = ed.getHitbox().toRect();
                    g.setColor(Color.RED);
                    g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
                } else if (e instanceof EntityProjectile) {
                    EntityProjectile ep = (EntityProjectile)e;
                    Rectangle r = ep.getHitbox().toRect();
                    g.setColor(Color.RED);
                    g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
                }
            }
        }

        if (showHitboxes) {
            Rectangle r = player.getHitbox().toRect();
            g.setColor(Color.RED);
            g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void update() {
        player = Run.instance.getPlayer();
        entities.forEach(Entity::update);
        player.update();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void playerEnter() {

    }

    public void playerExit() {

    }
}

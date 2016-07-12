package com.deepwelldevelopment.dungeonrun.engine.game;

import com.deepwelldevelopment.dungeonrun.engine.DungeonRun;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.EntityDoor;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.EntityDamageable;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.EnemySpider;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.EntityEnemy;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.item.EntityItem;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.item.EntityItemPedestal;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;
import com.deepwelldevelopment.dungeonrun.engine.game.item.Item;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.physics.PhysicsManager;
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
    PhysicsManager physicsManager;

    boolean clear;

    private boolean showHitboxes = true;

    public Room(Image display, int[][] grid, int[][] entityGrid) {
        this.display = display;
        this.grid = grid;
        this.entityGrid = entityGrid;
        entities = new ArrayList<>();
        player = Run.instance.getPlayer();
        physicsManager = new PhysicsManager(this);
        clear = false;

        for (int x = 0; x < entityGrid[0].length; x++) {
            for (int y = 0; y < entityGrid.length; y++) {
                int value = entityGrid[y][x];
                if (value != -1) {
                    int id = ((EntityEnemy) (Entity.gameEntities.get(entityGrid[y][x]))).getEnemyId();
                    Entity toAdd;
                    switch (id) {
                        case EntityEnemy.SPIDER_ID:
                            toAdd = new EnemySpider();
                            break;
                        default:
                            toAdd = new EnemySpider();
                            break;
                    }
                    toAdd.setX(x * (display.getWidth(null) / entityGrid[0].length)).setY(y * (display.getHeight(null) / entityGrid.length));
                    entities.add(toAdd);
                }
            }
        }

        entities.add(new EntityItemPedestal(Item.items.get(0), new Hitbox(0, 0, 64, 64)).setX(500).setY(300));
    }

    public void draw(JPanel source, Graphics g) {
        int blankSpaceX = DungeonRun.library.getScreenWidth() - display.getWidth(null);
        int blankSpaceY = DungeonRun.library.getScreenHeight() - display.getHeight(null);
        int offsetX = blankSpaceX/2;
        int offsetY = blankSpaceY/2;
        g.drawImage(display, offsetX, offsetY, null);

        g.drawImage(player.getImage(), player.getX(), player.getY(), null);

        //TODO: DRAW OBSTACLE LAYER

        for (Entity e : entities) {
            g.drawImage(e.getImage(), e.getX(), e.getY(), null);
            if (showHitboxes) {
                if (e instanceof EntityDamageable) {
                    EntityDamageable ed = (EntityDamageable) e;
                    Rectangle r = ed.getHitbox().toRect();
                    g.setColor(Color.RED);
                    g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
                } else if (e instanceof EntityProjectile) {
                    EntityProjectile ep = (EntityProjectile)e;
                    Rectangle r = ep.getHitbox().toRect();
                    g.setColor(Color.RED);
                    g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
                } else if (e instanceof EntityItemPedestal) {
                    EntityItemPedestal eip = ((EntityItemPedestal) e);
                    Rectangle r = eip.getHitbox().toRect();
                    g.setColor(Color.RED);
                    g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
                } else if (e instanceof EntityDoor) {
                    EntityDoor ed = ((EntityDoor) e);
                    Rectangle r = ed.getHitbox().toRect();
                    g.setColor(Color.RED);
                    g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
                }
            }
            if (e instanceof EntityItemPedestal) {
                EntityItemPedestal itemPedestal = (EntityItemPedestal)e;
                if (itemPedestal.getItem() != null) {
                    g.drawImage(itemPedestal.getItem().getImage(), itemPedestal.getX(), itemPedestal.getY()-80, null);
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

    public synchronized void update() {
        player = Run.instance.getPlayer();
        ArrayList postUpdate = (ArrayList) entities.clone();
        for (Entity e : entities) {
            if (e.destroyed()) {
                postUpdate.remove(e);
            } else {
                e.update();
            }
        }
        player.update();
        physicsManager.tick(entities);
        entities = postUpdate;
        for (Entity e : entities) {
            if (e instanceof EntityEnemy) {
                return;
            }
        }
        if (!clear) {
            clear();
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void initializeDoors() {
        int blankSpaceX = DungeonRun.library.getScreenWidth() - display.getWidth(null);
        int blankSpaceY = DungeonRun.library.getScreenHeight() - display.getHeight(null);
        int offsetX = blankSpaceX/2;
        int offsetY = blankSpaceY/2;
        int displayWidth = display.getWidth(null);
        int displayHeight = display.getHeight(null);
        for (Entity e : entities) {
            if (e instanceof EntityDoor) {
                EntityDoor entityDoor = (EntityDoor) e;
                switch (entityDoor.getDirection()) {
                    case Floor.LEFT:
                        entityDoor.setX(offsetX-entityDoor.getImage().getWidth(null));
                        entityDoor.setY(offsetY+(displayHeight/2)-(entityDoor.getImage().getHeight(null)/2));
                        break;
                    case Floor.UP:
                        entityDoor.setX(offsetX+(displayWidth/2)-(entityDoor.getImage().getWidth(null)/2));
                        entityDoor.setY(offsetY-entityDoor.getImage().getHeight(null));
                        break;
                    case Floor.RIGHT:
                        entityDoor.setX(offsetX+displayWidth);
                        entityDoor.setY(offsetY+(displayHeight/2)-(entityDoor.getImage().getHeight(null)/2));
                        break;
                    case Floor.DOWN:
                        entityDoor.setX(offsetX+(displayWidth/2)-(entityDoor.getImage().getWidth(null)/2));
                        entityDoor.setY(offsetY+displayHeight);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void playerEnter() {

    }

    public void playerExit() {
        for (Entity e : entities) {
            if (e instanceof EntityEnemy) {
                reinitialize();
                return;
            }
        }
    }

    public void clear() {
        System.out.println("room cleared. opening doors");
        clear = true;
        for (Entity e : entities) {
            if (e instanceof EntityDoor) {
                ((EntityDoor) e).open();
            }
        }
    }

    public void reinitialize() {
        ArrayList<Entity> nonEnemies = new ArrayList<>();
        for (Entity e : entities) {
            if (e instanceof EntityItemPedestal || e instanceof EntityItem || e instanceof EntityDoor) {
                nonEnemies.add(e);
            }
        }
        entities.clear();
        entities.addAll(nonEnemies);
        for (int x = 0; x < entityGrid[0].length; x++) {
            for (int y = 0; y < entityGrid.length; y++) {
                int value = entityGrid[y][x];
                if (value != -1) {
                    int id = ((EntityEnemy) (Entity.gameEntities.get(entityGrid[x][y]))).getEnemyId();
                    Entity toAdd;
                    switch (id) {
                        case EntityEnemy.SPIDER_ID:
                            toAdd = new EnemySpider();
                            break;
                        default:
                            toAdd = new EnemySpider();
                            break;
                    }
                    toAdd.setX(x * (display.getWidth(null) / entityGrid[0].length)).setY(y * (display.getHeight(null) / entityGrid.length));
                    entities.add(toAdd);
                }
            }
        }
    }

    public void destroy() {
        entities.forEach(Entity::destroy);
        entities.clear();
        player = null;
        entities = null;
        display = null;
        grid = null;
        entityGrid = null;
        physicsManager = null;
    }
}

package com.deepwelldevelopment.dungeonrun.engine.game;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.EntityDoor;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.EntityDamageable;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityMovable;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.EnemySpider;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.EntityEnemy;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.item.EntityItem;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.item.EntityItemPedestal;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;
import com.deepwelldevelopment.dungeonrun.engine.physics.PhysicsManager;
import com.deepwelldevelopment.dungeonrun.engine.prefab.Prefab;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

import static com.deepwelldevelopment.dungeonrun.engine.DungeonRun.library;

public class Room {

    private Image display;
    private int[][] grid;
    private int[][] entityGrid;
    private ArrayList<Entity> entities;
    private EntityPlayer player;
    private PhysicsManager physicsManager;
    private ArrayList<Entity> toAdd;

    private boolean clear;

    private boolean showHitboxes = true;

    public Room(Image display, int[][] grid, int[][] entityGrid, Prefab prefab) {
        this.display = display;
        this.grid = grid;
        this.entityGrid = entityGrid;
        entities = new ArrayList<>();
        player = Run.instance.getPlayer();
        physicsManager = new PhysicsManager(this);
        clear = false;

        int blankSpaceX = library.getScreenWidth() - display.getWidth(null);
        int blankSpaceY = library.getScreenHeight() - display.getHeight(null);
        int offsetX = blankSpaceX/2;
        int offsetY = blankSpaceY/2;
        toAdd = new ArrayList<>();
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
                    toAdd.setX((x * (display.getWidth(null) / entityGrid[0].length) + offsetX)).setY((y * (display.getHeight(null) / entityGrid.length)) + offsetY);
                    ((EntityEnemy) toAdd).registerAis();
                    entities.add(toAdd);
                }
            }
        }
        prefab.addSpecialEntities(this);
        entities.addAll(toAdd);
    }

    public void draw(Graphics g) {
        int blankSpaceX = library.getScreenWidth() - display.getWidth(null);
        int blankSpaceY = library.getScreenHeight() - display.getHeight(null);
        int offsetX = blankSpaceX/2;
        int offsetY = blankSpaceY/2;
        g.drawImage(display, offsetX, offsetY, null);

        g.drawImage(player.getImage(), player.getX(), player.getY(), null);

        //TODO: DRAW OBSTACLE LAYER

        for (Entity e : entities) {
            e.draw(g);
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

    public void update() {
        int blankSpaceX = library.getScreenWidth() - display.getWidth(null);
        int blankSpaceY = library.getScreenHeight() - display.getHeight(null);
        int offsetX = blankSpaceX / 2;
        int offsetY = blankSpaceY / 2;
        player = Run.instance.getPlayer();
        synchronized (entities) {
            entities.addAll(toAdd);
            toAdd.clear();
            for (Iterator<Entity> iterator = entities.iterator(); iterator.hasNext(); ) {
                Entity e = iterator.next();
                if (e.destroyed()) {
                    iterator.remove();
                } else {
                    e.update();
                    if (e.getX() < offsetX + library.getWallWidth() || e.getX() + e.getImage().getWidth(null) > offsetX + display.getWidth(null) - library.getWallWidth()) {
                        if (e instanceof EntityProjectile) {
                            e.destroy();
                        } else if (e instanceof EntityMovable) {
                            EntityMovable entityMovable = ((EntityMovable) e);
                            entityMovable.setDx(0);
                            entityMovable.setXForced(e.getX() < offsetX + library.getWallWidth() ? offsetX + library.getWallWidth() : offsetX + display.getWidth(null) - entityMovable.getImage().getWidth(null) - library.getWallWidth());
                        }
                    }
                    if (e.getY() < offsetY + library.getWallWidth() || e.getY() + e.getImage().getHeight(null) > offsetY + display.getHeight(null) - library.getWallWidth()) {
                        if (e instanceof EntityProjectile) {
                            e.destroy();
                        } else if (e instanceof EntityMovable) {
                            EntityMovable entityMovable = ((EntityMovable) e);
                            entityMovable.setDy(0);
                            entityMovable.setYForced(e.getY() < offsetY + library.getWallWidth() ? offsetY + library.getWallWidth() : offsetY + display.getHeight(null) - entityMovable.getImage().getHeight(null) - library.getWallWidth());
                        }
                    }
                }
            }
            player.update();
            if (player.getX() < offsetX || player.getX() + player.getImage().getWidth(null) > offsetX + display.getWidth(null)) {
                player.setDx(0);
                player.setXForced(player.getX() < offsetX ? offsetX : offsetX + display.getWidth(null) - player.getImage().getWidth(null));
            }
            if (player.getY() < offsetY || player.getY() + player.getImage().getHeight(null) > offsetY + display.getHeight(null)) {
                player.setDy(0);
                player.setYForced(player.getY() < offsetY ? offsetY : offsetY + display.getHeight(null) - player.getImage().getHeight(null));
            }
            physicsManager.tick();
            for (Entity e : entities) {
                if (e instanceof EntityEnemy) {
                    return;
                }
            }
            if (!clear) {
                clear();
            }
        }
        if (Run.instance.getCurrentFloor().getRoom(Floor.UP) == Run.instance.getCurrentFloor().getFloorBossRoom()) {
            entities.stream().filter(e -> e instanceof EntityDoor).filter(e -> ((EntityDoor) e).getDirection() == Floor.UP && !Run.instance.getCurrentFloor().isFloorBossUnlocked()).close();
        }
    }

    public void entityUpdate() {
        int blankSpaceX = library.getScreenWidth() - display.getWidth(null);
        int blankSpaceY = library.getScreenHeight() - display.getHeight(null);
        int offsetX = blankSpaceX / 2;
        int offsetY = blankSpaceY / 2;
        player = Run.instance.getPlayer();
        synchronized (entities) {
            entities.addAll(toAdd);
            toAdd.clear();
            for (Iterator<Entity> iterator = entities.iterator(); iterator.hasNext(); ) {
                Entity e = iterator.next();
                if (e.destroyed()) {
                    iterator.remove();
                } else {
                    e.update();
                    if (e.getX() < offsetX + library.getWallWidth() || e.getX() + e.getImage().getWidth(null) > offsetX + display.getWidth(null) - library.getWallWidth()) {
                        if (e instanceof EntityProjectile) {
                            e.destroy();
                        } else if (e instanceof EntityMovable) {
                            EntityMovable entityMovable = ((EntityMovable) e);
                            entityMovable.setDx(0);
                            entityMovable.setXForced(e.getX() < offsetX + library.getWallWidth() ? offsetX + library.getWallWidth() : offsetX + display.getWidth(null) - entityMovable.getImage().getWidth(null) - library.getWallWidth());
                        }
                    }
                    if (e.getY() < offsetY + library.getWallWidth() || e.getY() + e.getImage().getHeight(null) > offsetY + display.getHeight(null) - library.getWallWidth()) {
                        if (e instanceof EntityProjectile) {
                            e.destroy();
                        } else if (e instanceof EntityMovable) {
                            EntityMovable entityMovable = ((EntityMovable) e);
                            entityMovable.setDy(0);
                            entityMovable.setYForced(e.getY() < offsetY + library.getWallWidth() ? offsetY + library.getWallWidth() : offsetY + display.getHeight(null) - entityMovable.getImage().getHeight(null) - library.getWallWidth());
                        }
                    }
                }
            }
            player.update();
            if (player.getX() < offsetX || player.getX() + player.getImage().getWidth(null) > offsetX + display.getWidth(null)) {
                player.setDx(0);
                player.setXForced(player.getX() < offsetX ? offsetX : offsetX + display.getWidth(null) - player.getImage().getWidth(null));
            }
            if (player.getY() < offsetY || player.getY() + player.getImage().getHeight(null) > offsetY + display.getHeight(null)) {
                player.setDy(0);
                player.setYForced(player.getY() < offsetY ? offsetY : offsetY + display.getHeight(null) - player.getImage().getHeight(null));
            }
        }
    }

    public synchronized void addEntity(Entity entity) {
        toAdd.add(entity);
    }

    public synchronized void addEntities(Entity[] entities) {
        Collections.addAll(toAdd, entities);
    }

    void initializeDoors() {
        int blankSpaceX = library.getScreenWidth() - display.getWidth(null);
        int blankSpaceY = library.getScreenHeight() - display.getHeight(null);
        int offsetX = blankSpaceX/2;
        int offsetY = blankSpaceY/2;
        int displayWidth = display.getWidth(null);
        int displayHeight = display.getHeight(null);
        for (Entity e : entities) {
            if (e instanceof EntityDoor) {
                EntityDoor entityDoor = (EntityDoor) e;
                switch (entityDoor.getDirection()) {
                    case Floor.LEFT:
                        entityDoor.setX(offsetX-entityDoor.getImage().getWidth(null)+library.getDoorOffset()+library.getWallOffset());
                        entityDoor.setY(offsetY+(displayHeight/2)-(entityDoor.getImage().getHeight(null)/2));
                        break;
                    case Floor.UP:
                        entityDoor.setX(offsetX+(displayWidth/2)-(entityDoor.getImage().getWidth(null)/2));
                        entityDoor.setY(offsetY-entityDoor.getImage().getHeight(null)+library.getDoorOffset()+library.getWallOffset());
                        break;
                    case Floor.RIGHT:
                        entityDoor.setX(offsetX+displayWidth-library.getDoorOffset()-library.getWallOffset());
                        entityDoor.setY(offsetY+(displayHeight/2)-(entityDoor.getImage().getHeight(null)/2));
                        break;
                    case Floor.DOWN:
                        entityDoor.setX(offsetX+(displayWidth/2)-(entityDoor.getImage().getWidth(null)/2));
                        entityDoor.setY(offsetY+displayHeight-library.getDoorOffset()-library.getWallOffset());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    void playerEnter(int fromDirection) {
        int blankSpaceX = library.getScreenWidth() - display.getWidth(null);
        int blankSpaceY = library.getScreenHeight() - display.getHeight(null);
        int offsetX = blankSpaceX/2;
        int offsetY = blankSpaceY/2;
        int displayWidth = display.getWidth(null);
        int displayHeight = display.getHeight(null);
        switch (fromDirection) {
            case Floor.LEFT:
                player.setX(offsetX +library.getDoorOffset() + library.getWallOffset());
                break;
            case Floor.UP:
                player.setY(offsetY + library.getDoorOffset() + library.getWallOffset());
                break;
            case Floor.RIGHT:
                player.setX(offsetX + displayWidth - library.getDoorOffset() - player.getImage().getWidth(null) - library.getWallOffset());
                break;
            case Floor.DOWN:
                player.setY(offsetY + displayHeight - library.getDoorOffset() - player.getImage().getHeight(null) - library.getWallOffset());
                break;
            default:
                break;
        }
    }

    void playerExit() {
        for (Entity e : entities) {
            if (e instanceof EntityEnemy) {
                reinitialize();
                return;
            }
        }
    }

    private void clear() {
        clear = true;
        entities.stream().filter(e -> e instanceof EntityDoor).filter(e -> ((EntityDoor) e).isUnlocked()).forEach(e -> ((EntityDoor) e).open());
        if (Run.instance.getCurrentFloor().getRoom(Floor.UP) == Run.instance.getCurrentFloor().getFloorBossRoom()) {
            entities.stream().filter(e -> e instanceof EntityDoor).filter(e -> ((EntityDoor) e).getDirection() == Floor.UP && !Run.instance.getCurrentFloor().isFloorBossUnlocked()).close();
        }
        Entity roomReward = Run.instance.getGenerator().generateRoomRewards(display.getWidth(null) / 2, display.getHeight(null) / 2);
        if (roomReward != null) {
            entities.add(roomReward);
        }
    }

    private void reinitialize() {
        ArrayList<Entity> nonEnemies = entities.stream().filter(e -> e instanceof EntityItemPedestal || (e instanceof EntityItem) || (e instanceof EntityDoor)).collect(Collectors.toCollection(ArrayList::new));
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

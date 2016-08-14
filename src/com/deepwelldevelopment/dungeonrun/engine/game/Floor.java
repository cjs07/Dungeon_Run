package com.deepwelldevelopment.dungeonrun.engine.game;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.EntityDoor;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.prefab.Prefab;
import com.deepwelldevelopment.dungeonrun.engine.run.Generator;

import javax.swing.*;

public class Floor {

    //direction constants
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;

    private Room[][] layout;
    private Room currentRoom;
    private Room floorBossRoom;
    private int currentX;
    private int currentY;

    private boolean floorBossUnlocked;

    public Floor(int[][] layout, Prefab[][] prefabLayout) {
        this.layout = new Room[layout.length][layout[0].length];

        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                if (layout[y][x] == 0) {
                    this.layout[y][x] = null;
                } else {
                    Room toAdd = prefabLayout[y][x].toRoom();
                    if (y > 0) {
                        if (layout[y - 1][x] != Generator.NON_ROOM) {
                            toAdd.addEntity(new EntityDoor(new ImageIcon("res/assets/doorup.png").getImage(), 0, 0, new Hitbox(0, 0, 64, 128), UP));
                        }
                    }

                    if (x > 0) {
                        if (layout[y][x - 1] != Generator.NON_ROOM) {
                            toAdd.addEntity(new EntityDoor(new ImageIcon("res/assets/doorleft.png").getImage(), 0, 0, new Hitbox(0, 0, 128, 64), LEFT));
                        }
                    }

                    if (y < layout.length - 1) {
                        if (layout[y + 1][x] != Generator.NON_ROOM) {
                            toAdd.addEntity(new EntityDoor(new ImageIcon("res/assets/doordown.png").getImage(), 0, 0, new Hitbox(0, 0, 64, 128), DOWN));
                        }
                    }

                    if (x < layout[0].length - 1) {
                        if (layout[y][x + 1] != Generator.NON_ROOM) {
                            toAdd.addEntity(new EntityDoor(new ImageIcon("res/assets/doorright.png").getImage(), 0, 0, new Hitbox(0, 0, 128, 64), RIGHT));
                        }
                    }
                    toAdd.entityUpdate();
                    toAdd.initializeDoors();
                    this.layout[y][x] = toAdd;
                }

                if (layout[y][x] == 2) {
                    currentRoom = this.layout[y][x];
                    currentX = x;
                    currentY = y;
                }
                if (layout[y][x] == 5) {
                    floorBossRoom = this.layout[y][x];
                }
            }
        }
        floorBossUnlocked = false;
    }

    public Room[][] getLayout() {
        return layout;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getFloorBossRoom() {
        return floorBossRoom;
    }

    public boolean isFloorBossUnlocked() {
        return floorBossUnlocked;
    }

    public void moveRoom(int direction) {
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case LEFT:
                dx = -1;
                dy = 0;
                break;
            case UP:
                dx = 0;
                dy = -1;
                break;
            case RIGHT:
                dx = 1;
                dy = 0;
                break;
            case DOWN:
                dx = 0;
                dy = 1;
                break;
            default:
                break;
        }
        currentRoom.playerExit();
        currentRoom = layout[currentY+dy][currentX+dx];
        currentRoom.playerEnter(direction == LEFT  ? RIGHT : direction == UP ? DOWN : direction == RIGHT ? LEFT : UP);
        currentX += dx;
        currentY += dy;
    }

    public Room getRoom(int direction) {
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case LEFT:
                dx = -1;
                dy = 0;
                break;
            case UP:
                dx = 0;
                dy = -1;
                break;
            case RIGHT:
                dx = 1;
                dy = 0;
                break;
            case DOWN:
                dx = 0;
                dy = 1;
                break;
            default:
                break;
        }
        return layout[currentY + dy][currentX + dx];
    }

    public void unlockFloorBoss() {
        floorBossUnlocked = true;
    }
}

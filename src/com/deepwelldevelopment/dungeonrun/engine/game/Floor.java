package com.deepwelldevelopment.dungeonrun.engine.game;

import com.deepwelldevelopment.dungeonrun.engine.DungeonRun;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.EntityDoor;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.prefab.Prefab;

import javax.swing.*;

public class Floor {

    //direction constants
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;

    Room[][] layout;
    Room currentRoom;
    int currentX;
    int currentY;

    public Floor(int[][] layout, Prefab[][] prefabLayout) {
        this.layout = new Room[layout.length][layout[0].length];

        System.out.println("" + DungeonRun.library.getScreenWidth() + DungeonRun.library.getScreenHeight());

        for (int x = 0; x < layout[0].length; x++) {
            for (int y = 0; y < layout.length; y++) {
                if (layout[y][x] == 0) {
                    this.layout[y][x] = null;
                } else {
                    Room toAdd = prefabLayout[y][x].toRoom();
                    if (y > 0) {
                        if (layout[y - 1][x] == 1 || layout[y - 1][x] == 2) {
                            toAdd.addEntity(new EntityDoor(new ImageIcon("res/assets/doorleft.png").getImage(), 0, 0, new Hitbox(0, 0, 128, 64), LEFT));
                        }
                    }

                    if (x > 0) {
                        if (layout[y][x - 1] == 1 || layout[y][x - 1] == 2) {
                            toAdd.addEntity(new EntityDoor(new ImageIcon("res/assets/doorup.png").getImage(), 0, 0, new Hitbox(0, 0, 64, 128), UP));
                        }
                    }

                    if (y < layout.length-1) {
                        if (layout[y + 1][x] == 1 || layout[y + 1][x] == 2) {
                            toAdd.addEntity(new EntityDoor(new ImageIcon("res/assets/doorright.png").getImage(), 0, 0, new Hitbox(0, 0, 128, 64), RIGHT));
                        }
                    }

                    if (x < layout[0].length-1) {
                        if (layout[y][x + 1] == 1 || layout[y][x + 1] == 2) {
                            toAdd.addEntity(new EntityDoor(new ImageIcon("res/assets/doordown.png").getImage(), 0, 0, new Hitbox(0, 0, 64, 128), DOWN));
                        }
                    }
                    toAdd.initializeDoors();
                    this.layout[y][x] = toAdd;
                }

                if (layout[y][x] == 2) {
                    currentRoom = this.layout[x][y];
                    currentX = x;
                    currentY = y;
                }
            }
            System.out.println("" + currentX + ", " + currentY);
        }
    }

    public Room[][] getLayout() {
        return layout;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void moveRoom(int direction) {
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case LEFT:
                dx = 0;
                dy = -1;
                break;
            case UP:
                dx = -1;
                dy = 0;
                break;
            case RIGHT:
                dx = 0;
                dy = 1;
                break;
            case DOWN:
                dx = 1;
                dy = 0;
                break;
            default:
                break;
        }
        currentRoom.playerExit();
        currentRoom = layout[currentY+dy][currentX+dx];
        currentRoom.playerEnter(direction == LEFT  ? RIGHT : direction == UP ? DOWN : direction == RIGHT ? LEFT : UP);
        currentX += dx;
        currentY += dy;
        System.out.println("room moved");
        System.out.println("" + currentX + ", " + currentY);
    }
}

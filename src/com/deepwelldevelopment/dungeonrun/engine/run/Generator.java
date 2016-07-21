package com.deepwelldevelopment.dungeonrun.engine.run;

import com.deepwelldevelopment.dungeonrun.engine.game.Floor;
import com.deepwelldevelopment.dungeonrun.engine.prefab.ItemRoomPrefab;
import com.deepwelldevelopment.dungeonrun.engine.prefab.Prefab;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Generator {

    public static final int NON_ROOM = 0;
    public static final int NORMAL_ROOM = 1;
    public static final int SPAWN_ROOM = 2;
    public static final int ITEM_ROOM = 3;
    public static final int BOSS_ROOM = 4;
    public static final int FLOOR_BOSS_ROOM = 5;

    Random rng;
    int bossRooms;
    int itemRooms;
    private int[][] layout;
    private Prefab[][] prefabLayout;

    public Generator(Random rng) {
        this.rng = rng;
        layout = new int[15][15];
        prefabLayout = new Prefab[15][15];
    }

    public Floor[] generateRun(Run run) {
        Floor[] floors = new Floor[run.floorTo];
        for (int i = 0; i < run.floorTo; i++) {
            int[][] layout = generateFloor(new File("res/generation/floor" + i + ".drg"));
            prefabLayout = generatePrebabLayout(layout);
            floors[i] = new Floor(layout, prefabLayout);
        }
        return floors;
    }

    private int[][] generateFloor(File restrictions) {
        for (int x = 0; x < layout.length; x++) {
            for (int y = 0; y < layout.length; y++) {
                layout[x][y] = NON_ROOM;
            }
        }
        Scanner scanner;
        try {
            scanner = new Scanner(restrictions);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            scanner = new Scanner("res/generation/defaults.drg");
        }

        int spawnX = (int) Math.floor(layout.length / 2);
        int spawnY = (int) Math.floor(layout.length / 2);

        layout[spawnY][spawnX] = SPAWN_ROOM;
        layout[spawnY - 1][spawnX] = FLOOR_BOSS_ROOM;

        generateBranch(spawnX, spawnY, 0, rng.nextInt(3) + 5);
        generateBranch(spawnX, spawnY, 2, rng.nextInt(3) + 5);
        generateBranch(spawnX, spawnY, 3, rng.nextInt(3) + 5);

        System.out.println();
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                System.out.print(layout[y][x] + "    ");
            }
            System.out.print("\n");
        }

        bossRooms = 3;
        itemRooms = 3;
        try {
            addSpecialRooms();
        } catch (StackOverflowError e) {
            generateSpecialRooms();
        }

        validate();

        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                System.out.print(layout[y][x] + "    ");
            }
            System.out.print("\n");
        }
        return layout;
    }

    private Prefab[][] generatePrebabLayout(int[][] layout) {
        ArrayList<Prefab> availablePrefabs = Run.instance.getPrefabsForFloor();
        for (int y = 0; y < prefabLayout.length; y++) {
            for (int x = 0; x < prefabLayout[0].length; x++) {
                if (layout[y][x] == NORMAL_ROOM || layout[x][y] == SPAWN_ROOM) {
                    int selectionIndex = rng.nextInt(availablePrefabs.size());
                    prefabLayout[y][x] = availablePrefabs.get(selectionIndex);
                } else if (layout[y][x] == ITEM_ROOM) {
                    prefabLayout[y][x] = new ItemRoomPrefab(new ImageIcon("res/prefabs/forest2.png").getImage(), new int[50][50], new int[50][50]);

                } else if (layout[y][x] == BOSS_ROOM) {
                    int selectionIndex = rng.nextInt(availablePrefabs.size());
                    prefabLayout[y][x] = availablePrefabs.get(selectionIndex);
                } else if (layout[y][x] == FLOOR_BOSS_ROOM) {
                    int selectionIndex = rng.nextInt(availablePrefabs.size());
                    prefabLayout[y][x] = availablePrefabs.get(selectionIndex);
                }
            }
        }
        return prefabLayout;
    }

    private void generateBranch(int x, int y, int side, int rooms) {
        int currentX, currentY, xOffset, yOffset;
        switch (side) {
            case 0:
                currentX = x-1;
                currentY = y;
                xOffset = -1;
                yOffset = 0;
                break;
            case 1:
                currentX = x;
                currentY = y-1;
                xOffset = 0;
                yOffset = -1;
                break;
            case 2:
                currentX = x+1;
                currentY = y;
                xOffset = 1;
                yOffset = 0;
                break;
            case 3:
                currentX = x;
                currentY = y+1;
                xOffset = 0;
                yOffset = 1;
                break;
            default:
                return;
        }

        for (int i = 0; i < rooms; i++) {
            int newY = currentY + (yOffset * i);
            int newX = currentX + (xOffset * i);
            if (newY > 0 && newY < layout.length - 1 && newX > 0 && newX < layout[0].length - 1)
                if (layout[newY][newX] == 0) {
                    layout[newY][newX] = NORMAL_ROOM;
                }
        }

        for (int i = 2; i < rooms; i++) {
            int branchCreationIndex = rng.nextInt(16);
            if (branchCreationIndex < 3) {
                generateBranch((currentX + (xOffset * i)), (currentY + (yOffset * i)), side + 1 <= 3 ? side + 1 : side + 1 == 4 ? 0 : 0, rooms - 1);
            }
            if (branchCreationIndex < 6) {
                generateBranch((currentX + (xOffset * i)), (currentY + (yOffset * i)), side + 3 <= 3 ? side + 3 : side + 3 == 4 ? 0 : side + 3 == 5 ? 1 : side + 3 == 5 ? 2 : side + 3 == 6 ? 3 : 0, rooms - 1);
            }
        }
    }

    void addSpecialRooms() {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                if (layout[y][x] == NORMAL_ROOM) {
                    if (rng.nextInt(100) < 10) {
                        int down = layout[y + 1][x];
                        int right = layout[y][x + 1];
                        int up = layout[y - 1][x];
                        int left = layout[y][x - 1];
                        int generationId = rng.nextInt(2);
                        if (generationId == 0 && itemRooms == 0) {
                            generationId = 1;
                        }
                        if (generationId == 1 && bossRooms == 0) {
                            generationId = 0;
                        }
                        if (itemRooms == 0 && bossRooms == 0) {
                            return;
                        }
                        if (down == NORMAL_ROOM) {
                            if (right == NON_ROOM && up == NON_ROOM && left == NON_ROOM) {
                                switch (generationId) {
                                    case 0:
                                        layout[y][x] = ITEM_ROOM;
                                        itemRooms--;
                                        break;
                                    case 1:
                                        layout[y][x] = BOSS_ROOM;
                                        bossRooms--;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else if (right == NORMAL_ROOM) {
                            if (down == NON_ROOM && up == NON_ROOM && left == NON_ROOM) {
                                switch (generationId) {
                                    case 0:
                                        layout[y][x] = ITEM_ROOM;
                                        itemRooms--;
                                        break;
                                    case 1:
                                        layout[y][x] = BOSS_ROOM;
                                        bossRooms--;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else if (up == NORMAL_ROOM) {
                            if (right == NON_ROOM && down == NON_ROOM && left == NON_ROOM) {
                                switch (generationId) {
                                    case 0:
                                        layout[y][x] = ITEM_ROOM;
                                        itemRooms--;
                                        break;
                                    case 1:
                                        layout[y][x] = BOSS_ROOM;
                                        bossRooms--;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else if (left == NORMAL_ROOM) {
                            if (right == NON_ROOM && up == NON_ROOM && down == NON_ROOM) {
                                switch (generationId) {
                                    case 0:
                                        layout[y][x] = ITEM_ROOM;
                                        itemRooms--;
                                        break;
                                    case 1:
                                        layout[y][x] = BOSS_ROOM;
                                        bossRooms--;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
                if (bossRooms == 0 && itemRooms == 0) {
                    return;
                }
            }
        }
        System.out.println(bossRooms + ", " + itemRooms);
        if (bossRooms > 0 || itemRooms > 0) {
            addSpecialRooms();
        }
    }

    private void generateSpecialRooms() {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                if (layout[y][x] == NON_ROOM) {
                    if (rng.nextInt(100) < 10) {
                        int down = -1;
                        int right = -1;
                        int up = -1;
                        int left = -1;
                        if (y < layout.length - 1) {
                            down = layout[y + 1][x];
                        }
                        if (x < layout[0].length - 1) {
                            right = layout[y][x + 1];
                        }
                        if (y > 0) {
                            up = layout[y - 1][x];
                        }
                        if (x > 0) {
                            left = layout[y][x - 1];
                        }
                        int generationId = rng.nextInt(2);
                        if (generationId == 0 && itemRooms == 0) {
                            generationId = 1;
                        }
                        if (generationId == 1 && bossRooms == 0) {
                            generationId = 0;
                        }
                        if (itemRooms == 0 && bossRooms == 0) {
                            return;
                        }
                        if (down == NORMAL_ROOM) {
                            if (right == NON_ROOM && up == NON_ROOM && left == NON_ROOM) {
                                switch (generationId) {
                                    case 0:
                                        layout[y][x] = ITEM_ROOM;
                                        itemRooms--;
                                        break;
                                    case 1:
                                        layout[y][x] = BOSS_ROOM;
                                        bossRooms--;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else if (right == NORMAL_ROOM) {
                            if (down == NON_ROOM && up == NON_ROOM && left == NON_ROOM) {
                                switch (generationId) {
                                    case 0:
                                        layout[y][x] = ITEM_ROOM;
                                        itemRooms--;
                                        break;
                                    case 1:
                                        layout[y][x] = BOSS_ROOM;
                                        bossRooms--;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else if (up == NORMAL_ROOM) {
                            if (right == NON_ROOM && down == NON_ROOM && left == NON_ROOM) {
                                switch (generationId) {
                                    case 0:
                                        layout[y][x] = ITEM_ROOM;
                                        itemRooms--;
                                        break;
                                    case 1:
                                        layout[y][x] = BOSS_ROOM;
                                        bossRooms--;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else if (left == NORMAL_ROOM) {
                            if (right == NON_ROOM && up == NON_ROOM && down == NON_ROOM) {
                                switch (generationId) {
                                    case 0:
                                        layout[y][x] = ITEM_ROOM;
                                        itemRooms--;
                                        break;
                                    case 1:
                                        layout[y][x] = BOSS_ROOM;
                                        bossRooms--;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
                if (bossRooms == 0 && itemRooms == 0) {
                    return;
                }
            }
        }
        System.out.println(bossRooms + ", " + itemRooms);
        if (bossRooms > 0 || itemRooms > 0) {
            generateSpecialRooms();
        }
    }

    private void validate() {
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                if (layout[y][x] == FLOOR_BOSS_ROOM) {
                    layout[y][x + 1] = NON_ROOM;
                    layout[y - 1][x] = NON_ROOM;
                    layout[y][x - 1] = NON_ROOM;
                }
                if (layout[y][x] != NON_ROOM) {
                    if (layout[y + 1][x] == NON_ROOM && layout[y][x + 1] == NON_ROOM && layout[y - 1][x] == NON_ROOM && layout[y][x - 1] == NON_ROOM) {
                        layout[y][x] = NON_ROOM;
                    }
                }
            }
        }
    }

    public long generateRandomSeed() {
        return rng.nextLong();
    }
}

package com.deepwelldevelopment.dungeonrun.engine.prefab;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PrefabLoader {

    String directory;

    public PrefabLoader(String directory) {
        this.directory = directory;
    }

    public Prefab loadPrefab(String name) throws FileNotFoundException {
        String path = directory + "/" + name;
        Image image = new ImageIcon(path + ".png").getImage();
        File gridFile = new File(path + ".drp");
        File entityFile = new File(path + ".dre");
        Scanner gridScanner = new Scanner(gridFile);
        Scanner entityScanner = new Scanner(entityFile);
        int [][] grid = new int[50][50];
        int [][] entityGrid = new int[50][50];
        for (int y = 0; y < grid[0].length; y++) {
            for (int x = 0; x < grid.length; x++) {
                grid[x][y] = gridScanner.nextInt();
            }
        }
        for (int y = 0; y < entityGrid[0].length; y++) {
            for (int x = 0; x < entityGrid.length; x++) {
                entityGrid[x][y] = entityScanner.nextInt();
            }
        }
        return new Prefab(image, grid, entityGrid);
    }

    public ArrayList<Prefab> loadPrefabsForFloor(int floor) {
        ArrayList<Prefab> prefabs = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("res/prefabs/floor" + floor + "prefabs.drg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            while (scanner.hasNext()) {
                try {
                    prefabs.add(loadPrefab(scanner.next()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return prefabs;
    }
}

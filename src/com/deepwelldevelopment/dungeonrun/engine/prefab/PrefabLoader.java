package com.deepwelldevelopment.dungeonrun.engine.prefab;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
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
                entityGrid[x][y] = gridScanner.nextInt();
            }
        }
        return new Prefab(image, grid, entityGrid);
    }
}

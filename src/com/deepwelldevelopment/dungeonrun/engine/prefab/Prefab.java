package com.deepwelldevelopment.dungeonrun.engine.prefab;

import java.awt.*;

public class Prefab {

    Image visual;
    int[][] gridData;
    int[][] entityGrid;

    public Prefab(Image visual, int[][] gridData, int[][] entityGrid) {
        this.visual = visual;
        this.gridData = gridData;
        this.entityGrid = entityGrid;
    }
}

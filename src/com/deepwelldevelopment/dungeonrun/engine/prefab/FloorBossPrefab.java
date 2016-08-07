package com.deepwelldevelopment.dungeonrun.engine.prefab;

import com.deepwelldevelopment.dungeonrun.engine.DungeonRun;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.boss.BossGuardianOfTheShrine;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import java.awt.*;

public class FloorBossPrefab extends Prefab {

    public FloorBossPrefab(Image visual, int[][] gridData, int[][] entityGrid) {
        super(visual, gridData, entityGrid);

        for (int y = 0; y < entityGrid.length; y++) {
            for (int x = 0; x < entityGrid[0].length; x++) {
                entityGrid[y][x] = -1;
            }
        }
        switch (Run.instance.getFloorIndex()) {
            case 0:
                BossGuardianOfTheShrine toAdd = new BossGuardianOfTheShrine(DungeonRun.library.getScreenWidth() / 2 - visual.getWidth(null) / 2, 0);
                toAdd.registerAis();
                specialEntities.add(toAdd);
                break;
            default:
                break;
        }
    }
}

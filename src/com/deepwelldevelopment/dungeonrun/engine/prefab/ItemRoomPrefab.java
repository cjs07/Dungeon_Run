package com.deepwelldevelopment.dungeonrun.engine.prefab;

import com.deepwelldevelopment.dungeonrun.engine.DungeonRun;
import com.deepwelldevelopment.dungeonrun.engine.game.Room;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.item.EntityItemPedestal;
import com.deepwelldevelopment.dungeonrun.engine.game.item.Item;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class ItemRoomPrefab extends Prefab {

    public ItemRoomPrefab(Image visual, int[][] gridData, int[][] entityGrid) {
        super(visual, gridData, entityGrid);
        for (int y = 0; y < this.entityGrid.length; y++) {
            for (int x = 0; x < this.entityGrid[0].length; x++) {
                entityGrid[y][x] = -1;
            }
        }
    }

    @Override
    public Room toRoom() {
        Room room = super.toRoom();
        try {
            room.addEntity(new EntityItemPedestal(Item.items.remove(0), new Hitbox(0, 0, 64, 64)).setX(DungeonRun.library.getScreenWidth() / 2).setY(DungeonRun.library.getScreenHeight() / 2));
        } catch (IndexOutOfBoundsException e) {
            room.addEntity(new EntityItemPedestal(Item.emergencyItem, new Hitbox(0, 0, 64, 64)).setX(DungeonRun.library.getScreenWidth() / 2).setY(DungeonRun.library.getScreenHeight() / 2));
        }
        return room;
    }
}

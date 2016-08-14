package com.deepwelldevelopment.dungeonrun.engine.game.entity.item.pickup;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.item.EntityItem;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class EntityItemPickup extends EntityItem {

    Hitbox hitbox;

    public EntityItemPickup(Image image, int x, int y, Hitbox hitbox) {
        super(image, x, y);

        this.hitbox = hitbox;
    }

    public void onPickup() {

    }

    public Hitbox getHitbox() {
        return hitbox;
    }
}

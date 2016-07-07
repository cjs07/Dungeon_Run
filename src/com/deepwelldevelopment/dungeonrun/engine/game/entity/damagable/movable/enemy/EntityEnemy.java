package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityMovable;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class EntityEnemy extends EntityMovable {

    public EntityEnemy(int id, Image image, int x, int y, double hp, Hitbox hitbox) {
        super(id, image, x, y, hp, hitbox);
    }

}

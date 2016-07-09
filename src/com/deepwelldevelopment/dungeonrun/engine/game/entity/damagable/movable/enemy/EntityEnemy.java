package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityMovable;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class EntityEnemy extends EntityMovable {

    public static final int SPIDER_ID = 0;

    int enemyId;

    public EntityEnemy(int id, Image image, int x, int y, double hp, Hitbox hitbox, int enemyId) {
        super(id, image, x, y, hp, hitbox);

        this.enemyId = enemyId;
    }

    public int getEnemyId() {
        return enemyId;
    }
}

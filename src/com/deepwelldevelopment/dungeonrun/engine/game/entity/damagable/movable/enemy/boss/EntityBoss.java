package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.boss;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.EntityEnemy;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class EntityBoss extends EntityEnemy {

    String name;

    public EntityBoss(int id, Image image, int x, int y, double hp, Hitbox hitbox, int enemyId, String name) {
        super(id, image, x, y, hp, hitbox, enemyId);

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

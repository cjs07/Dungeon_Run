package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class EntityDamagable extends Entity {

    double hp;
    Hitbox hitbox;

    public EntityDamagable(int id, Image image, int x, int y, double hp, Hitbox hitbox) {
        super(id, image, x, y);

        this.hp = hp;
        this.hitbox = hitbox;

    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void damage(double amount) {
        hp -= amount;
    }
}

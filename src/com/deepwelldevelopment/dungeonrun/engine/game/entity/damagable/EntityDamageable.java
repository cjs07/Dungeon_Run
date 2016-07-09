package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class EntityDamageable extends Entity {

    double hp;
    Hitbox hitbox;

    public EntityDamageable(int id, Image image, int x, int y, double hp, Hitbox hitbox) {
        super(id, image, x, y);

        this.hp = hp;
        this.hitbox = hitbox;

    }

    public double getHp() {
        return hp;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void damage(double amount) {
        hp -= amount;
        if (hp <= 0) {
            destroy();
        }
    }

    @Override
    public Entity setX(int x) {
        super.setX(x);
        hitbox.setX(x);
        return this;
    }

    @Override
    public Entity setY(int y) {
        super.setY(y);
        hitbox.setY(y);
        return this;
    }
}

package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.EntityDamageable;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class EntityMovable extends EntityDamageable {

    int dx;
    int dy;

    public EntityMovable(int id, Image image, int x, int y, double hp, Hitbox hitbox) {
        super(id, image, x, y, hp, hitbox);
    }

    public void update() {
        x += dx;
        y += dy;

        getHitbox().setX(x);
        getHitbox().setY(y);
    }

    public EntityMovable setDx(int dx) {
        this.dx = dx;
        return this;
    }

    public EntityMovable setDy(int dy) {
        this.dy = dy;
        return this;
    }

    /*@Override
    public Entity setX(int x) {
        //super.setX(x);
        this.x = x-image.getWidth(null)/2;
        getHitbox().setX(this.x);
        return this;
    }

    @Override
    public Entity setY(int y) {
        //super.setY(y);
        this.y = y-image.getWidth(null)/2;
        getHitbox().setY(this.y);
        return this;
    }
    */
}

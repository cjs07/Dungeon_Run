package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.EntityDamagable;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class EntityMovable extends EntityDamagable {

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
}

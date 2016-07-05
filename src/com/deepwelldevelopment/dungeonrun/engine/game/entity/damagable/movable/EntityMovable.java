package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.EntityDamagable;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

public class EntityMovable extends EntityDamagable {

    int x;
    int y;

    int dx;
    int dy;

    public EntityMovable(int id, double hp, Hitbox hitbox, int x, int y) {
        super(id, hp, hitbox);
        this.x = x;
        this.y = y;
    }

    public void update() {
        x += dx;
        y += dy;
    }
}

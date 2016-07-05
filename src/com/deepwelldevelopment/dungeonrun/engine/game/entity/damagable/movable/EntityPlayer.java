package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable;

import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

public class EntityPlayer extends EntityMovable {

    public EntityPlayer(int id, double hp, Hitbox hitbox, int x, int y) {
        super(id, hp, hitbox, x, y);
    }

    @Override
    public void damage(double amount) {

    }
}

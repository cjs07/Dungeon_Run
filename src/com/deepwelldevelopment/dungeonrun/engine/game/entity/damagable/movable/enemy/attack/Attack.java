package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.attack;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;

public abstract class Attack {

    protected Entity source;

    public Attack(Entity source) {
        this.source = source;
    }

    public abstract void execute();
}

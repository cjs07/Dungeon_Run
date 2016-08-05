package com.deepwelldevelopment.dungeonrun.engine.game.entity.ai;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;

public abstract class AI {

    protected Entity owner;

    public AI (Entity owner) {
        this.owner = owner;
    }

    public abstract void update();
}

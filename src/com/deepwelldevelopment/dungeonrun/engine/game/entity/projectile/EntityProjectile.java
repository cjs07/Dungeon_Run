package com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

public class EntityProjectile extends Entity {

    Entity source;
    Hitbox hitbox;

    public EntityProjectile(int id, Entity source, Hitbox hitbox) {
        super(id);
        this.source = source;
        this.hitbox = hitbox;
    }

    public Entity getSource() {
        return source;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }
}

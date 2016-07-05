package com.deepwelldevelopment.dungeonrun.engine.physics;

import com.deepwelldevelopment.dungeonrun.engine.game.Room;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.EntityDamagable;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;

import java.util.ArrayList;

public class PhysicsManager {

    ArrayList<Entity> entities;

    public PhysicsManager(Room room) {
        entities = room.getEntities();
    }

    public void tick() {
        for (Entity e : entities) {
            if (e instanceof EntityDamagable) {
                EntityDamagable ed = (EntityDamagable) e;
                Hitbox targetBox = ed.getHitbox();
                for (Entity source : entities) {
                    if (source instanceof EntityProjectile) {
                        EntityProjectile ep = (EntityProjectile) source;
                        if (ep.getSource() != ed) { //shots are not hitting entity that fired them
                            Hitbox sourceBox = ep.getHitbox();

                        }
                    }
                }
            }
        }
    }
}

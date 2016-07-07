package com.deepwelldevelopment.dungeonrun.engine.physics;

import com.deepwelldevelopment.dungeonrun.engine.game.Room;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.EntityDamagable;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;

import java.util.ArrayList;

public class PhysicsManager {

    public PhysicsManager(Room room) {
    }

    public void tick(ArrayList<Entity> entities) {
        for (Entity e : entities) {
            if (e instanceof EntityDamagable) {
                EntityDamagable ed = (EntityDamagable) e;
                Hitbox targetBox = ed.getHitbox();
                for (Entity source : entities) {
                    if (source instanceof EntityProjectile) {
                        EntityProjectile ep = (EntityProjectile) source;
                        if (ep.getSource() != ed) { //shots are not hitting entity that fired them
                            Hitbox sourceBox = ep.getHitbox();
                            if (targetBox.intersects(sourceBox.toRect())) {
                                System.out.println("collision");
                            }
                        }
                    }
                }
            }
        }
    }
}

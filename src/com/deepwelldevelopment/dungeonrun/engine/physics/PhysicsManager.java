package com.deepwelldevelopment.dungeonrun.engine.physics;

import com.deepwelldevelopment.dungeonrun.engine.game.Room;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.EntityDoor;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.EntityDamageable;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.EntityEnemy;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.item.EntityItemPedestal;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import java.util.ArrayList;

public class PhysicsManager {

    Room room;

    public PhysicsManager(Room room) {
        this.room = room;
    }

    public void tick(ArrayList<Entity> entities) {
        entities.add(Run.instance.getPlayer());
        for (Entity target : entities) {
            if (target instanceof EntityDamageable) {
                EntityDamageable targetDamageable = (EntityDamageable) target;
                Hitbox targetBox = targetDamageable.getHitbox();
                for (Entity source : entities) {
                    if (source instanceof EntityProjectile) {
                        EntityProjectile projectileSource = (EntityProjectile) source;
                        if (projectileSource.getSource() != targetDamageable) { //shots are not hitting entity that fired them
                            Hitbox sourceBox = projectileSource.getHitbox();
                            if (targetBox.intersects(sourceBox)) {
                                System.out.println("projectile collision");
                                source.destroy();
                                if (projectileSource.getSource() == Run.instance.getPlayer()) {
                                    targetDamageable.damage(Run.instance.getDamage());
                                } else {
                                    if (targetDamageable instanceof EntityPlayer) {
                                        EntityPlayer player = (EntityPlayer) targetDamageable;
                                    }
                                }
                            }
                        }
                    } else if (source instanceof EntityEnemy) {
                        EntityEnemy sourceEnemy = (EntityEnemy) source;
                        Hitbox sourceBox = sourceEnemy.getHitbox();
                        if (targetBox.intersects(sourceBox)) {
                            if (target instanceof EntityPlayer) {
                                EntityPlayer targetPlayer = (EntityPlayer) target;
                                System.out.println("contact damage");
                                targetPlayer.damage(1);
                            }
                        }
                    } else if (source instanceof EntityItemPedestal) {
                        EntityItemPedestal sourceItemPedestal = (EntityItemPedestal)source;
                        Hitbox sourceBox = sourceItemPedestal.getHitbox();
                        if (targetBox.intersects(sourceBox)) {
                            if (target instanceof EntityPlayer) {
                                sourceItemPedestal.pickupItem();
                            }
                        }
                    } else if (source instanceof EntityDoor) {
                        EntityDoor sourceDoor = (EntityDoor) source;
                        Hitbox sourceBox = sourceDoor.getHitbox();
                        if (target instanceof EntityPlayer) {
                            if (sourceBox.contains(targetBox)) {
                                if (sourceDoor.isOpen()) {
                                    Run.instance.getCurrentFloor().moveRoom(sourceDoor.getDirection());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

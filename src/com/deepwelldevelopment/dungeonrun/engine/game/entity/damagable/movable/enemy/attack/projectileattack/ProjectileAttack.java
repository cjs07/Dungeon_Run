package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.attack.projectileattack;

import com.deepwelldevelopment.dungeonrun.engine.game.Floor;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.attack.Attack;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;

public class ProjectileAttack extends Attack {

    private EntityProjectile[] createdProjectiles;
    private AttackPattern pattern;

    ProjectileAttack(Entity source, int numberOfProjectiles, AttackPattern pattern) {
        super(source);
        createdProjectiles = new EntityProjectile[numberOfProjectiles];
        this.pattern = pattern;

        for (int i = 0; i < createdProjectiles.length; i++) {
            createdProjectiles[i] = new EntityProjectile(source, new Hitbox(source.getX(), source.getY(), 16, 16), new ImageIcon("res/assets/shot.png").getImage(), source.getX(), source.getY(), 200);
        }
    }

    @Override
    public void execute() {
        execute(Run.instance.getGenerator().generateInt(4));
    }

    public void execute(int direction) {
        for (int i = 0; i < createdProjectiles.length; i++) {
            createdProjectiles[i] = new EntityProjectile(source, new Hitbox(source.getCenterX(), source.getCenterY(), 16, 16), new ImageIcon("res/assets/shot.png").getImage(), source.getCenterX(), source.getCenterY(), 200);
        }
        switch (direction) {
            case Floor.LEFT:
                for (int i = 0; i < createdProjectiles.length; i++) {
                    createdProjectiles[i].setDx(-pattern.movementModifier).setDy(pattern.movementModifier - i);
                }
                break;
            case Floor.UP:
                for (int i = 0; i < createdProjectiles.length; i++) {
                    createdProjectiles[i].setDx(pattern.movementModifier - i).setDy(-pattern.movementModifier);
                }
                break;
            case Floor.RIGHT:
                for (int i = 0; i < createdProjectiles.length; i++) {
                    createdProjectiles[i].setDx(pattern.movementModifier).setDy(pattern.movementModifier - i);
                }
                break;
            case Floor.DOWN:
                for (int i = 0; i < createdProjectiles.length; i++) {
                    createdProjectiles[i].setDx(pattern.movementModifier - i).setDy(-pattern.movementModifier);
                }
                break;
            default:
                break;
        }

        Run.instance.getCurrentFloor().getCurrentRoom().addEntities(createdProjectiles);
    }

    enum AttackPattern {
        doubleTight(0),
        doubleSpread(1),
        tripleShot(1);

        public int movementModifier;

        AttackPattern(int movementModifier) {
            this.movementModifier = movementModifier;
        }
    }
}

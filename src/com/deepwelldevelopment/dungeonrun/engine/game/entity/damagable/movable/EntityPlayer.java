package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;

public class EntityPlayer extends EntityMovable {

    boolean invincible;
    int iFrames;
    int iFramesTarget;

    public EntityPlayer(int id, double hp, Hitbox hitbox, int x, int y) {
        super(id, new ImageIcon("res/player.png").getImage(), x, y, hp, hitbox);
        iFramesTarget = 60;
    }

    @Override
    public void update() {
        super.update();

        if (invincible) {
            System.out.println("invincibility frame");
            iFrames++;
            if (iFrames >= iFramesTarget) {
                invincible = false;
            }
        }
    }

    @Override
    public void damage(double amount) {
        if (!invincible) {
            Run.instance.damagePlayer((int) amount);
            iFrames = 0;
            invincible = true;
        }
    }

    public EntityProjectile fireShot(int direction) {
        int x = Run.instance.getPlayer().getX();
        int y = Run.instance.getPlayer().getY();
        switch (direction) {
            case 0:
                return new EntityProjectile(Run.instance.getPlayer(), new Hitbox(x, y, 16, 16), x, y, Run.instance.getRange()).setDx(-2).setDy(0);
            case 1:
                return new EntityProjectile(Run.instance.getPlayer(), new Hitbox(x, y, 16, 16), x, y, Run.instance.getRange()).setDx(0).setDy(-2);
            case 2:
                return new EntityProjectile(Run.instance.getPlayer(), new Hitbox(x, y, 16, 16), x, y, Run.instance.getRange()).setDx(2).setDy(0);
            case 3:
                return new EntityProjectile(Run.instance.getPlayer(), new Hitbox(x, y, 16, 16), x, y, Run.instance.getRange()).setDx(0).setDy(2);
            default:
                break;
        }
        return null;
    }
}

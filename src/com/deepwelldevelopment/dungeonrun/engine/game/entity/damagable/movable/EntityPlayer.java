package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;
import java.awt.*;

public class EntityPlayer extends EntityMovable {

    Image image;

    public EntityPlayer(int id, double hp, Hitbox hitbox, int x, int y) {
        super(id, hp, hitbox, x, y);

        image = new ImageIcon("res/player.png").getImage();
    }

    @Override
    public void damage(double amount) {

    }

    public Image getImage() {
        return image;
    }

    public EntityProjectile fireShot(int direction) {
        System.out.println("shot fired");
        switch (direction) {
            case 0:
                return new EntityProjectile(Run.instance.getPlayer(), new Hitbox(), Run.instance.getPlayer().getX(), Run.instance.getPlayer().getY()).setDx(-1).setDy(0);
            case 1:
                return new EntityProjectile(Run.instance.getPlayer(), new Hitbox(), Run.instance.getPlayer().getX(), Run.instance.getPlayer().getY()).setDx(0).setDy(-1);
            case 2:
                return new EntityProjectile(Run.instance.getPlayer(), new Hitbox(), Run.instance.getPlayer().getX(), Run.instance.getPlayer().getY()).setDx(1).setDy(0);
            case 3:
                return new EntityProjectile(Run.instance.getPlayer(), new Hitbox(), Run.instance.getPlayer().getX(), Run.instance.getPlayer().getY()).setDx(0).setDy(1);
            default:
                break;
        }
        return null;
    }
}

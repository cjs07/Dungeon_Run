package com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import javax.swing.*;
import java.awt.*;

public class EntityProjectile extends Entity {

    Entity source;
    Hitbox hitbox;
    double range;

    int startX;
    int startY;

    int dx;
    int dy;

    public EntityProjectile(Entity source, Hitbox hitbox, Image image, int x, int y, double range) {
        super(image, x, y);
        this.source = source;
        this.hitbox = hitbox;
        this.range = range;

        startX = x;
        startY = y;
    }

    public EntityProjectile(Entity source, Hitbox hitbox, int x, int y, double range) {
        super(x, y);
        this.source = source;
        this.hitbox = hitbox;
        this.range = range;

        startX = x;
        startY = y;

        if (source instanceof EntityPlayer) {
            image = new ImageIcon("res/playershot.png").getImage();
        } else {
            image = new ImageIcon("res/assets/shot.png").getImage();
        }
    }

    public Entity getSource() {
        return source;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public EntityProjectile setDx(int dx) {
        this.dx = dx;
        return this;
    }

    public EntityProjectile setDy(int dy) {
        this.dy = dy;
        return this;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;

        hitbox.setX(x);
        hitbox.setY(y);

        int xTraveled = x - startX;
        int yTraveled = y - startY;
        double traveled = Math.sqrt((xTraveled * xTraveled) + (yTraveled * yTraveled));

        System.out.println(traveled);

        if (traveled >= range) {
            destroy();
        }
    }
}

package com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import javax.swing.*;
import java.awt.*;

public class EntityProjectile extends Entity {

    Entity source;
    Hitbox hitbox;

    int dx;
    int dy;

    public EntityProjectile(Entity source, Hitbox hitbox, Image image, int x, int y) {
        super(image, x, y);
        this.source = source;
        this.hitbox = hitbox;
    }

    public EntityProjectile(Entity source, Hitbox hitbox, int x, int y) {
        super(x, y);
        this.source = source;
        this.hitbox = hitbox;

        if (source instanceof EntityPlayer) {
            image = new ImageIcon("res/playershot.png").getImage();
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
    }
}

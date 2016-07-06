package com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import javax.swing.*;
import java.awt.*;

public class EntityProjectile extends Entity {

    Entity source;
    Hitbox hitbox;
    Image image;

    int x;
    int y;
    int dx;
    int dy;

    public EntityProjectile(Entity source, Hitbox hitbox, Image image, int x, int y) {
        this.source = source;
        this.hitbox = hitbox;
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public EntityProjectile(Entity source, Hitbox hitbox, int x, int y) {
        this.source = source;
        this.hitbox = hitbox;
        this.x = x;
        this.y = y;

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

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public EntityProjectile setX(int x) {
        this.x = x;
        return this;
    }

    public EntityProjectile setY(int y) {
        this.y = y;
        return this;
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

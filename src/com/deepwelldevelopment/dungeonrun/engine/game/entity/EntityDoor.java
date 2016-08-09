package com.deepwelldevelopment.dungeonrun.engine.game.entity;

import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;

public class EntityDoor extends Entity {

    private Hitbox hitbox;
    private boolean isOpen;
    private int direction;

    public EntityDoor(Image image, int x, int y, Hitbox hitbox, int direction) {
        super(image, x, y);

        this.hitbox = hitbox;
        isOpen = false;
        this.direction = direction;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getDirection() {
        return direction;
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    @Override
    public Entity setX(int x) {
        this.x = x;
        hitbox.setX(x);
        return this;
    }

    @Override
    public Entity setY(int y) {
        this.y = y;
        hitbox.setY(y);
        return this;
    }
}

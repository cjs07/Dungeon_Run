package com.deepwelldevelopment.dungeonrun.engine.physics;

import java.awt.*;

public class Hitbox extends Rectangle {

    public Hitbox(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Hitbox(Rectangle r) {
        super(r);

        x = (int) r.getX();
        y = (int) r.getY();
        width = (int) r.getWidth();
        height = (int) r.getHeight();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle toRect() {
        return new Rectangle(x, y, width, height);
    }
}

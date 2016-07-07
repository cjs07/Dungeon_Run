package com.deepwelldevelopment.dungeonrun.engine.game.entity;

import java.awt.*;
import java.util.ArrayList;

public class Entity {

    public static ArrayList<Entity> gameEntities;

    int id;
    protected Image image;
    protected int x;
    protected int y;

    public Entity(int id, Image image, int x, int y) {
        this.id = id;
        this.image = image;
        this.x = x;
        this.y = y;

        //register in global entity dictionary
        gameEntities.add(id, this);
    }

    public Entity(Image image, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public Entity(int x, int y) {
        this.x = x;
        this. y = y;
    }

    public static void initializeEntityLibrary() {
        gameEntities = new ArrayList<>();
    }

    public void update() {
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

    public Entity setX(int x) {
        this.x = x;
        return this;
    }

    public Entity setY(int y) {
        this.y = y;
        return this;
    }
}

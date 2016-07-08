package com.deepwelldevelopment.dungeonrun.engine.game.entity;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.EnemySpider;

import java.awt.*;
import java.util.ArrayList;

public class Entity {

    public static ArrayList<Entity> gameEntities;
    protected Image image;
    protected int x;
    protected int y;
    int id;
    boolean destroy;

    public Entity(int id, Image image, int x, int y) {
        this.id = id;
        this.image = image;
        this.x = x;
        this.y = y;

        //register in global entity dictionary
        gameEntities.add(this);
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
        new EnemySpider();
    }

    public void update() {
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public Entity setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Entity setY(int y) {
        this.y = y;
        return this;
    }

    public void destroy() {
        destroy = true;
    }

    public boolean destroyed() {
        return destroy;
    }
}

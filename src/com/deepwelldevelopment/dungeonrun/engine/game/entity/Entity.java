package com.deepwelldevelopment.dungeonrun.engine.game.entity;

import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.util.ArrayList;

public class Entity {

    public static ArrayList<Entity> gameEntities;

    float hp;
    float damage;
    float speed;

    float x;
    float y;
    float dx;
    float dy;
    Hitbox hitbox;

    public Entity(float hp, float damage, float speed) {

        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
    }

    public Entity(float hp, float damage, float speed, float x, float y) {

        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public Entity(float hp, float damage, float speed, float x, float y, Hitbox hitbox) {
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.hitbox = hitbox;
    }

    public void update() {
        //update ai control
        x += dx;
        y += dy;
    }
}

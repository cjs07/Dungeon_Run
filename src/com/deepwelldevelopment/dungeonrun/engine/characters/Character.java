package com.deepwelldevelopment.dungeonrun.engine.characters;

public class Character {

    public int hp;
    public float damage;
    public int fireDelay;
    public float fireRate;
    public float accuracy;
    public float speed;
    public float range;
    public float luck;

    public Character(int hp, float damage, int fireDelay, float fireRate, float accuracy, float speed, float range, float luck) {
        this.hp = hp;
        this.damage = damage;
        this.fireDelay = fireDelay;
        this.fireRate = fireRate;
        this.accuracy = accuracy;
        this.speed = speed;
        this.range = range;
        this.luck = luck;
    }
}

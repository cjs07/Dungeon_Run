package com.deepwelldevelopment.dungeonrun.engine.run;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;

import java.util.Random;

public class Run {

    public Character character;

    int hp;
    float damage;
    int fireDelay;
    float fireRate;
    float accuracy;
    float speed;
    float range;
    float luck;

    Generator generator;

    int floorTo = 1;


    public Run(Character character) {
        this.character = character;
        hp = character.hp;
        damage = character.damage;
        fireDelay = character.fireDelay;
        fireRate = character.fireRate;
        accuracy = character.accuracy;
        speed = character.speed;
        range = character.range;
        luck = character.luck;

        generator = new Generator(new Random());
    }

    public Run(int hp, float damage, int fireDelay, float fireRate, float accuracy, float speed, float range, float luck) {
        this.hp = hp;
        this.damage = damage;
        this.fireDelay = fireDelay;
        this.fireRate = fireRate;
        this.accuracy = accuracy;
        this.speed = speed;
        this.range = range;
        this.luck = luck;
    }

    public void generate() {
        generator.generateRun();
    }
}

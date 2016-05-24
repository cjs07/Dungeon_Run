package com.deepwelldevelopment.dungeonrun.engine.run;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;

public class Run {

    Character character;

    int hp;
    float damage;
    int fireDelay;
    float fireRate;
    float accuracy;
    float speed;
    float range;
    float luck;


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
}

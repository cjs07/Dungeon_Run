package com.deepwelldevelopment.dungeonrun.engine.run;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.game.Floor;
import com.deepwelldevelopment.dungeonrun.engine.prefab.Prefab;
import com.deepwelldevelopment.dungeonrun.engine.prefab.PrefabLoader;

import java.util.ArrayList;
import java.util.Random;

public class Run {

    public static Run instance;

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
    PrefabLoader prefabLoader;
    ArrayList<Prefab> prefabsForFloor;
    int floorTo = 1;

    Floor[] floors;
    int floorIndex;

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
        prefabLoader = new PrefabLoader("res/prefabs");

        prefabsForFloor = prefabLoader.loadPrefabsForFloor(1);

        instance = this;
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

    public Generator getGenerator() {
        return generator;
    }

    public ArrayList<Prefab> getPrefabsForFloor() {
        return prefabsForFloor;
    }

    public Floor getCurrentFloor() {
        return floors[floorIndex];
    }

    public void generate() {
        floors = generator.generateRun(this);
        floorIndex = 0;
    }
}

package com.deepwelldevelopment.dungeonrun.engine.run;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.game.Floor;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.prefab.Prefab;
import com.deepwelldevelopment.dungeonrun.engine.prefab.PrefabLoader;
import com.deepwelldevelopment.dungeonrun.gui.GamePanel;

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

    EntityPlayer player;

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

        player = new EntityPlayer(0, hp, new Hitbox(), GamePanel.width/2, GamePanel.height/2);
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

    public EntityPlayer getPlayer() {
        return player;
    }

    public int getHp() {
        return hp;
    }

    public float getDamage() {
        return damage;
    }

    public int getFireDelay() {
        return fireDelay;
    }

    public float getFireRate() {
        return fireRate;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public float getSpeed() {
        return speed;
    }

    public float getRange() {
        return range;
    }

    public float getLuck() {
        return luck;
    }

    public void updatePlayer(EntityPlayer player) {
        this.player = player;
    }

    public void generate() {
        floors = generator.generateRun(this);
        floorIndex = 0;
    }
}

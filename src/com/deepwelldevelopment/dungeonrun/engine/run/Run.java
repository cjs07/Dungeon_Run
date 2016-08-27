package com.deepwelldevelopment.dungeonrun.engine.run;

import com.deepwelldevelopment.dungeonrun.engine.DungeonRun;
import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.game.Floor;
import com.deepwelldevelopment.dungeonrun.engine.game.Room;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityPlayer;
import com.deepwelldevelopment.dungeonrun.engine.game.item.Item;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.prefab.Prefab;
import com.deepwelldevelopment.dungeonrun.engine.prefab.PrefabLoader;
import com.deepwelldevelopment.dungeonrun.gui.GamePanel;

import java.util.ArrayList;
import java.util.Random;

public class Run {

    public static Run instance;

    public Character character;
    int floorTo = 1;
    GamePanel gamePanel;
    private int hp;
    private float damage;
    private int fireDelay;
    private float fireRate;
    private float accuracy;
    private float speed;
    private float range;
    private float luck;
    private Generator generator;
    private PrefabLoader prefabLoader;
    private ArrayList<Prefab> prefabsForFloor;
    private EntityPlayer player;
    private Floor[] floors;
    private int floorIndex;
    private ArrayList<Item> items;
    private int keys;
    private int bossKeys;

    public Run(Character character, GamePanel gamePanel) {
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

        this.gamePanel = gamePanel;

        instance = this;

        int x = DungeonRun.library.getScreenWidth()/2;
        int y = DungeonRun.library.getScreenHeight()/2;
        player = new EntityPlayer(0, hp, new Hitbox(x, y, 64, 64), x, y);
        items = new ArrayList<>();
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

    public void damagePlayer(int amount) {
        hp -= amount;
        for (Item i : items) {
            i.onPlayerDamaged();
        }
    }

    public void addItem(Item item) {
        items.add(item);
        item.onPickup();
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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getFireDelay() {
        return fireDelay;
    }

    public void setFireDelay(int fireDelay) {
        this.fireDelay = fireDelay;
    }

    public float getFireRate() {
        return fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getLuck() {
        return luck;
    }

    public void setLuck(float luck) {
        this.luck = luck;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void updatePlayer(EntityPlayer player) {
        this.player = player;
    }

    public int getFloorIndex() {
        return floorIndex;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }

    public void setBossKeys(int bossKeys) {
        this.bossKeys = bossKeys;
    }

    public void addKey() {
        keys++;
    }

    public void addKeys(int amount) {
        keys += amount;
    }

    public void addBossKey() {
        bossKeys++;

        if (bossKeys >= 3) {
            getCurrentFloor().unlockFloorBoss();
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void generate() {
        floors = generator.generateRun(this);
        floorIndex = 0;
    }

    public void end() {
        character = null;
        generator = null;
        prefabLoader = null;
        prefabsForFloor = null;
        Entity.gameEntities.remove(player);
        player = null;
        for (Floor f : floors) {
            Room[][] rooms = f.getLayout();
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms[0].length; j++) {
                    if (rooms[i][j] != null) {
                        rooms[i][j].destroy();
                    }
                }
            }
        }
        instance = null;
    }
}

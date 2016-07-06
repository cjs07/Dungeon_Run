package com.deepwelldevelopment.dungeonrun.engine.characters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Character {

    public static Character[] characters = new Character[10];
    public int id;
    public String name;
    public int hp;
    public float damage;
    public int fireDelay;
    public float fireRate;
    public float accuracy;
    public float speed;
    public float range;
    public float luck;

    public Character(int id, String name, int hp, float damage, int fireDelay, float fireRate, float accuracy,
                     float speed, float range, float luck) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.fireDelay = fireDelay;
        this.fireRate = fireRate;
        this.accuracy = accuracy;
        this.speed = speed;
        this.range = range;
        this.luck = luck;

        characters[id] = this;
    }

    public Character(int id, String name, int hp, float damage, int fireDelay, float accuracy, float speed, float range, float luck) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.fireDelay = fireDelay;
        this.accuracy = accuracy;
        this.speed = speed;
        this.range = range;
        this.luck = luck;

        characters[id] = this;
    }

    public static void initCharacters() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("res/class.drc"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner != null && scanner.hasNext()) {
            String[] data = new String[9];
            for (int i = 0; i < data.length; i++) {
                data[i] = scanner.next();
                if (i==8) {
                    characters[Integer.parseInt(data[0])] = new Character(Integer.parseInt(data[0]), data[1],
                            Integer.parseInt(data[2]), Float.parseFloat(data[3]), Integer.parseInt(data[4]),
                            Float.parseFloat(data[5]), Float.parseFloat(data[6]), Float.parseFloat(data[7]), Float.parseFloat(data[8]));
                }
            }
        }
    }
}

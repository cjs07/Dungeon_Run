package com.deepwelldevelopment.dungeonrun.engine.game.item;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Item {

    public static ArrayList<Item> items;

    int id;
    String name;
    String pickupQuote;
    Image image;
    int hpMod;
    float damageMod;
    int fireDelayMod;
    float accuracyMod;
    float speedMod;
    float rangeMod;
    float luckMod;

    float damageMultiplier;
    float fireDelayMultiplier;
    boolean multipliersStack;

    public Item(int id, String name, String pickupQuote, Image image) {
        this.id = id;
        this.name = name;
        this.pickupQuote = pickupQuote;
        this.image = image;
    }

    public Item(int id, String name, String pickupQuote, Image image, int hpMod, float damageMod, int fireDelayMod, float accuracyMod, float speedMod, float rangeMod, float luckMod) {
        this.id = id;
        this.name = name;
        this.pickupQuote = pickupQuote;
        this.image = image;
        this.hpMod = hpMod;
        this.damageMod = damageMod;
        this.fireDelayMod = fireDelayMod;
        this.accuracyMod = accuracyMod;
        this.speedMod = speedMod;
        this.rangeMod = rangeMod;
        this.luckMod = luckMod;
        items.add(id, this);
    }

    public Item(int id, String name, String pickupQuote, Image image, int hpMod, float damageMultiplier, int fireDelayMod, float accuracyMod, float speedMod, float rangeMod, float luckMod, boolean multipliersStack) {

        this.id = id;
        this.name = name;
        this.pickupQuote = pickupQuote;
        this.image = image;
        this.hpMod = hpMod;
        this.fireDelayMod = fireDelayMod;
        this.accuracyMod = accuracyMod;
        this.speedMod = speedMod;
        this.rangeMod = rangeMod;
        this.luckMod = luckMod;
        this.damageMultiplier = damageMultiplier;
        this.multipliersStack = multipliersStack;
        items.add(id, this);
    }

    public Item(int id, String name, String pickupQuote, Image image, int hpMod, float damageMod, float fireDelayMultiplier, float accuracyMod, float speedMod, float rangeMod, float luckMod, boolean multipliersStack) {
        this.id = id;
        this.name = name;
        this.pickupQuote = pickupQuote;
        this.image = image;
        this.hpMod = hpMod;
        this.damageMod = damageMod;
        this.accuracyMod = accuracyMod;
        this.speedMod = speedMod;
        this.rangeMod = rangeMod;
        this.luckMod = luckMod;
        this.fireDelayMultiplier = fireDelayMultiplier;
        this.multipliersStack = multipliersStack;
        items.add(id, this);
    }

    public static void initializeItems() {
        items = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("res/items/basicitemshunter.dri"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            while (scanner.hasNext()) {

                String[] params = new String[11];
                for (int i = 0; i < 11; i++) {
                    String next = scanner.nextLine();
                    if (!Objects.equals(next, "")) {
                        params[i] = next;
                    } else {
                        i--;
                    }
                }
                new Item(Integer.parseInt(params[0]), params[1], params[2], new ImageIcon(params[3]).getImage(), Integer.parseInt(params[4]), Float.parseFloat(params[5]), Integer.parseInt(params[6]), Float.parseFloat(params[7]), Float.parseFloat(params[8]), Float.parseFloat(params[9]), Float.parseFloat(params[10]));
            }
            scanner.close();
        }
    }

    public void onPickup() {
        Run r = Run.instance;
        r.setHp(r.getHp()+hpMod);
        r.setDamage(r.getDamage()+damageMod);
        r.setFireDelay(r.getFireDelay()+fireDelayMod);
        r.setAccuracy(r.getAccuracy()+accuracyMod);
        r.setSpeed(r.getSpeed()+speedMod);
        r.setRange(r.getRange()+rangeMod);
        r.setLuck(r.getLuck()+luckMod);
    }

    public void onShotFired(EntityProjectile shot) {

    }

    public void onPlayerDamaged() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPickupQuote() {
        return pickupQuote;
    }

    public Image getImage() {
        return image;
    }

    public int getHpMod() {
        return hpMod;
    }

    public float getDamageMod() {
        return damageMod;
    }

    public int getFireDelayMod() {
        return fireDelayMod;
    }

    public float getAccuracyMod() {
        return accuracyMod;
    }

    public float getSpeedMod() {
        return speedMod;
    }

    public float getRangeMod() {
        return rangeMod;
    }

    public float getLuckMod() {
        return luckMod;
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public float getFireDelayMultiplier() {
        return fireDelayMultiplier;
    }

    public boolean isMultipliersStack() {
        return multipliersStack;
    }
}

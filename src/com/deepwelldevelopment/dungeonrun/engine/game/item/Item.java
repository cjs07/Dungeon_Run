package com.deepwelldevelopment.dungeonrun.engine.game.item;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.projectile.EntityProjectile;

import java.awt.*;
import java.util.ArrayList;

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
    }

    public static void initializeItems() {

    }

    public void onShotFired(EntityProjectile shot) {

    }

    public void onPlayerDamaged() {

    }


}

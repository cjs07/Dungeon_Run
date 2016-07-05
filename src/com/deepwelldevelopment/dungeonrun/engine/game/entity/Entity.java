package com.deepwelldevelopment.dungeonrun.engine.game.entity;

import java.util.ArrayList;

public class Entity {

    public static ArrayList<Entity> gameEntities;

    int id;

    public Entity(int id) {
        this.id = id;

        //register in global entity dictionary
        gameEntities.add(id, this);
    }

    public void update() {
    }
}

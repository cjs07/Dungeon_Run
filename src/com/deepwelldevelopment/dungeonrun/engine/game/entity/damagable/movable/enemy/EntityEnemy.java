package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.ai.AI;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityMovable;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import java.awt.*;
import java.util.ArrayList;

public class EntityEnemy extends EntityMovable {

    public static final int SPIDER_ID = 0;

    int enemyId;
    ArrayList<AI> ais;

    public EntityEnemy(int id, Image image, int x, int y, double hp, Hitbox hitbox, int enemyId) {
        super(id, image, x, y, hp, hitbox);

        this.enemyId = enemyId;
        ais = new ArrayList<>();
    }

    public void addAI(AI ai) {
        ais.add(ai);
    }

    public void registerAis() {}

    @Override
    public void update() {
        super.update();
        ais.forEach(AI::update);
    }

    public int getEnemyId() {
        return enemyId;
    }
}

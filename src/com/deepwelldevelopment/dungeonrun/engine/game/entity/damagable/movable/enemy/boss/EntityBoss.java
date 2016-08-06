package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.boss;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.EntityEnemy;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.attack.Attack;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EntityBoss extends EntityEnemy {

    String name;

    ArrayList<Attack> attacks;
    Random random;

    public EntityBoss(int id, Image image, int x, int y, double hp, Hitbox hitbox, int enemyId, String name) {
        super(id, image, x, y, hp, hitbox, enemyId);

        this.name = name;

        attacks = new ArrayList<>();
        random = new Random(Run.instance.getGenerator().generateRandomSeed());
    }

    public void doAttack() {
        int index = random.nextInt(attacks.size());
        attacks.get(index).execute();
    }

    public void addAttack(Attack attack) {
        attacks.add(attack);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

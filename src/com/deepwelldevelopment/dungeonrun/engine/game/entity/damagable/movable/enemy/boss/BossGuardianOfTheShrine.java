package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.boss;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.attack.projectileattack.ProjectileAttackTripleShot;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.boss.ai.BossWalkAI;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import javax.swing.*;

public class BossGuardianOfTheShrine extends EntityBoss {
    public BossGuardianOfTheShrine(int x, int y) {
        super(100, new ImageIcon("res/assets/boss/gurdianoftheshrine.png").getImage(), x, y, 150, new Hitbox(x, y, 128, 128), 100, "Gurdian of the Shrine");
        addAttack(new ProjectileAttackTripleShot(this));
    }

    @Override
    public void registerAis() {
        addAI(new BossWalkAI(this));
    }
}

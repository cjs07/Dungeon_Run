package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.attack.projectileattack;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;

public class ProjectileAttackTripleShot extends ProjectileAttack {

    public ProjectileAttackTripleShot(Entity source) {
        super(source, 3, AttackPattern.tripleShot);
    }
}

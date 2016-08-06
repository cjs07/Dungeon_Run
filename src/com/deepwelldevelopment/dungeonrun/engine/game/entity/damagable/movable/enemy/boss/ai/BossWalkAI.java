package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.boss.ai;

import com.deepwelldevelopment.dungeonrun.engine.game.Floor;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.ai.AI;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityMovable;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.boss.EntityBoss;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import java.util.Random;

public class BossWalkAI extends AI {

    Random random;

    int framesToMove;
    int pauseFrames;

    boolean canAttack;

    public BossWalkAI(Entity owner) {
        super(owner);

        random = new Random(Run.instance.getGenerator().generateRandomSeed());

        framesToMove = 0;
        pauseFrames = 0;
        canAttack = false;
    }

    @Override
    public void update() {
        if (pauseFrames > 0) {
            ((EntityMovable) owner).setDx(0).setDy(0);
            pauseFrames--;

            if (canAttack) {
                ((EntityBoss) owner).doAttack();
                canAttack = false;
            }
        }

        if (framesToMove <= 0 && pauseFrames <= 0) {
            framesToMove = random.nextInt(11) + 30;
            switch (random.nextInt(4)) {
                case Floor.LEFT:
                    ((EntityMovable) owner).setDx(-2).setDy(0);
                    break;
                case Floor.UP:
                    ((EntityMovable) owner).setDx(0).setDy(2);
                    break;
                case Floor.RIGHT:
                    ((EntityMovable) owner).setDx(2).setDy(0);
                    break;
                case Floor.DOWN:
                    ((EntityMovable) owner).setDx(0).setDy(-2);
                    break;
                default:
                    break;
            }
        } else {
            framesToMove--;
            if (framesToMove <= 0) {
                pauseFrames = random.nextInt(10) + 1;
                canAttack = true;
            }
        }
    }
}

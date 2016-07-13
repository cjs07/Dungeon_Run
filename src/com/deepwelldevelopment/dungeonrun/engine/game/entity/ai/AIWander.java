package com.deepwelldevelopment.dungeonrun.engine.game.entity.ai;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityMovable;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import java.util.Random;

public class AIWander extends AI {

    Entity target;
    int maxMove;

    Random wanderCalc;

    public AIWander(Entity owner, Entity target, int maxMove){
        super(owner);

        this.target = target;
        this.maxMove = maxMove;

        wanderCalc = new Random(Run.instance.getGenerator().generateRandomSeed());
    }

    @Override
    public void update() {
        int dx;
        int dy;

        EntityMovable entityMovable = ((EntityMovable) owner);

        dx = wanderCalc.nextInt(maxMove*2) - maxMove;
        dy = wanderCalc.nextInt(maxMove*2) - maxMove;

        entityMovable.setDx(dx);
        entityMovable.setDy(dy);
    }
}

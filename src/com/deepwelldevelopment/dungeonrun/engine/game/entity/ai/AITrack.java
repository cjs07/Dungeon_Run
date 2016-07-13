package com.deepwelldevelopment.dungeonrun.engine.game.entity.ai;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.EntityMovable;

public class AITrack extends AI{

    Entity target;
    double speed;

    public AITrack(Entity owner, Entity target, double speed) {
        super(owner);

        this.target = target;
        this.speed = speed;
    }

    @Override
    public void update() {
        boolean xNegative;
        boolean yNegative;
        int dx;
        int dy;

        EntityMovable entityMovable = ((EntityMovable) owner);

        xNegative = ((target.getX() - owner.getX()) < 0);
        yNegative = ((target.getY() - owner.getY()) < 0);

        dx = (int) speed;
        dy = (int) speed;

        if (xNegative) {
            dx = -dx;
        }

        if (yNegative) {
            dy = -dy;
        }

        entityMovable.setDx(dx);
        entityMovable.setDy(dy);
    }
}

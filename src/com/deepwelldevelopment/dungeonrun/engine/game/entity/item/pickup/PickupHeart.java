package com.deepwelldevelopment.dungeonrun.engine.game.entity.item.pickup;

import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;

public class PickupHeart extends EntityItemPickup {

    private boolean isHalf;

    public PickupHeart(int x, int y, boolean isHalf) {
        super(isHalf ? new ImageIcon("res/assets/halfheart_drop.png").getImage() : new ImageIcon("res/assets/fullheart_drop.png").getImage(), x, y, new Hitbox(x, y, 32, 32));
        this.isHalf = isHalf;
    }

    @Override
    public void onPickup() {
        Run.instance.setHp(Run.instance.getHp() + (isHalf ? 1 : 2));
    }
}

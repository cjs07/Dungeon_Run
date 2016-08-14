package com.deepwelldevelopment.dungeonrun.engine.game.entity.item.pickup;

import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;

public class PickupKey extends EntityItemPickup {

    public PickupKey(int x, int y) {
        super(new ImageIcon("res/assets/key_drop.png").getImage(), x, y, new Hitbox(x, y, 32, 32));
    }

    @Override
    public void onPickup() {
        Run.instance.addKey();
    }
}

package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable;

import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;

import javax.swing.*;
import java.awt.*;

public class EntityPlayer extends EntityMovable {

    Image image;

    public EntityPlayer(int id, double hp, Hitbox hitbox, int x, int y) {
        super(id, hp, hitbox, x, y);

        image = new ImageIcon("res/player.png").getImage();
    }

    @Override
    public void damage(double amount) {

    }

    public Image getImage() {
        return image;
    }
}

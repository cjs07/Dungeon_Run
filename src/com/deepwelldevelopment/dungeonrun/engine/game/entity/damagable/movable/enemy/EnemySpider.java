package com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.ai.AITrack;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;

public class EnemySpider extends EntityEnemy {
    public EnemySpider() {
        super(1, new ImageIcon("res/assets/enemy/spider.png").getImage(), 0, 0, 5, new Hitbox(0, 0, 32, 32), 0);
    }

    @Override
    public void registerAis() {
        addAI(new AITrack(this, Run.instance.getPlayer(), 2));
    }
}

package com.deepwelldevelopment.dungeonrun.engine.game.entity.item;

import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.item.Item;
import com.deepwelldevelopment.dungeonrun.engine.game.item.itempool.ItemPool;
import com.deepwelldevelopment.dungeonrun.engine.physics.Hitbox;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;

public class EntityItemPedestal extends Entity{

    ItemPool itemPool;
    Item item;

    Hitbox hitbox;

    public EntityItemPedestal(ItemPool itemPool, Hitbox hitbox) {
        super(new ImageIcon("res/assets/itempedestal.png").getImage(), 0, 0);

        this.itemPool = itemPool;
        item = itemPool.getItems().remove(0);
        this.hitbox = hitbox;
    }

    public EntityItemPedestal(Item item, Hitbox hitbox) {
        super(new ImageIcon("res/assets/itempedestal.png").getImage(), 0, 0);

        this.item = item;
        this.hitbox = hitbox;
    }

    public void pickupItem() {
        if (item != null) {
            Run.instance.addItem(item);
            item = null;
        }
    }

    public Item getItem() {
        return item;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public Entity setX(int x) {
        super.setX(x);
        hitbox.setX(x);
        return this;
    }

    @Override
    public Entity setY(int y) {
        super.setY(y);
        hitbox.setY(y);
        return this;
    }
}

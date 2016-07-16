package com.deepwelldevelopment.dungeonrun.gui;

import com.deepwelldevelopment.dungeonrun.engine.DungeonRun;
import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.game.item.Item;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, Runnable{

    public static GamePanel instance;

    public boolean isActive;

    boolean paused;

    GameFrame frame;
    Run run;

    Image fullHeart;
    Image halfHeart;

    int framesSinceShot;
    boolean canFire;

    boolean itemPickup;
    Item pickup;
    int pickupFrames;

    public GamePanel(GameFrame frame) {
        this.frame = frame;
        run = null;
        fullHeart = new ImageIcon("res/fullheart.png").getImage();
        halfHeart = new ImageIcon("res/halfheart.png").getImage();
        instance = this;
    }

    public void startRun(Run run) {
        this.run = run;
        Character c = this.run.character;
        System.out.print(c.id + c.name + c.hp + c.damage + c.fireDelay + c.fireRate + c.accuracy + c.speed + c.range + c.luck);
        DungeonRun.library.setDoorOffset(run.getPlayer().getImage().getWidth(null));
        run.generate();
        paused = false;
        canFire = true;
        new Thread(this).start();

        Collections.shuffle(Item.items, new Random(run.getGenerator().generateRandomSeed()));
    }

    public void itemPickup(Item item) {
        itemPickup = true;
        pickup = item;
        pickupFrames = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (run.getHp() % 2 == 0) {
            for (int i = 0; i < run.getHp(); i+=2) {
                g.drawImage(fullHeart, i*fullHeart.getWidth(null), 10, null);
            }
        } else {
            for (int i = 0; i < run.getHp()-1; i+=2) {
                g.drawImage(fullHeart, i*fullHeart.getWidth(null), 10, null);
            }
            g.drawImage(halfHeart, (((run.getHp()-1)/2)*fullHeart.getWidth(null)) + (2*fullHeart.getWidth(null)), 10, null);
        }

        run.getCurrentFloor().getCurrentRoom().draw(this, g);

        if (itemPickup) {
            String itemName = pickup.getName();
            String pickupQuote = pickup.getPickupQuote();
            setFont(new Font("Times New Roman", Font.BOLD, 36));
            g.setColor(Color.WHITE);
            FontMetrics fm = getFontMetrics(getFont());
            g.drawString(itemName, ((getWidth()/2)-(fm.stringWidth(itemName)/2)), (getHeight()/2)-(getFont().getSize()/2));
            setFont(new Font("Times New Roman", Font.PLAIN, 24));
            //fm = getFontMetrics(getFont());
            g.drawString(pickupQuote, ((getWidth()/2)-(fm.stringWidth(pickupQuote)/2)), (getHeight()/2)+(getFont().getSize()/2));
            pickupFrames++;
            if (pickupFrames > 240) {
                itemPickup = false;
                pickup = null;
                pickupFrames = 0;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                run.getPlayer().setDy((int) -run.getSpeed());
                break;
            case KeyEvent.VK_A:
                run.getPlayer().setDx((int) -run.getSpeed());
                break;
            case KeyEvent.VK_S:
                run.getPlayer().setDy((int) run.getSpeed());
                break;
            case KeyEvent.VK_D:
                run.getPlayer().setDx((int) run.getSpeed());
                break;
            case KeyEvent.VK_UP:
                if (canFire) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(1));
                    framesSinceShot = 0;
                    canFire = false;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (canFire) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(3));
                    framesSinceShot = 0;
                    canFire = false;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (canFire) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(0));
                    framesSinceShot = 0;
                    canFire = false;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (canFire) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(2));
                    framesSinceShot = 0;
                    canFire = false;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                Run.instance.setHp(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S) {
            run.getPlayer().setDy(0);
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D) {
            run.getPlayer().setDx(0);
        }
    }

    @Override
    public void run() {
        while (true) {
            if (!paused) {
                run.getCurrentFloor().getCurrentRoom().update();
                Run.instance = run;
                framesSinceShot++;
                canFire = framesSinceShot >= 2 * run.getFireDelay();
                repaint();
                revalidate();
                if (Run.instance.getHp() <= 0) { //player is dead
                    isActive = false;
                    frame.gop.isActive = true;
                    frame.removeKeyListener(this);
                    frame.addKeyListener(frame.gop);
                    frame.setContentPane(frame.gop);
                    frame.gop.setPreviousClass(Run.instance.character.id);
                    Run.instance.end();
                    return;
                }
                try {
                    Thread.sleep(1000/60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

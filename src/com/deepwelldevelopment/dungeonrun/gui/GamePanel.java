package com.deepwelldevelopment.dungeonrun.gui;

import com.deepwelldevelopment.dungeonrun.engine.DungeonRun;
import com.deepwelldevelopment.dungeonrun.engine.animation.Animation;
import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.Entity;
import com.deepwelldevelopment.dungeonrun.engine.game.entity.damagable.movable.enemy.boss.EntityBoss;
import com.deepwelldevelopment.dungeonrun.engine.game.item.Item;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, Runnable{

    public static GamePanel instance;

    boolean isActive;

    private boolean paused;

    private GameFrame frame;
    private Run run;

    private Image fullHeart;
    private Image halfHeart;

    private int framesSinceShot;
    private boolean canFire;

    private boolean itemPickup;
    private Item pickup;
    private int pickupFrames;

    private JProgressBar bossHealth;
    private boolean inBossFight;
    private EntityBoss bossFightBoss;

    private Animation testAnimation;

    private InputManager inputManager;

    GamePanel(GameFrame frame) {
        this.frame = frame;
        run = null;
        fullHeart = new ImageIcon("res/fullheart.png").getImage();
        halfHeart = new ImageIcon("res/halfheart.png").getImage();
        instance = this;
        inBossFight = false;

        setLayout(new BorderLayout());
        bossHealth = new JProgressBar(JProgressBar.VERTICAL);

        add(bossHealth, BorderLayout.WEST);

        bossHealth.setVisible(true);

        testAnimation = new Animation("res/assets/playerwalksheet.png", 64, 128, 4);
        testAnimation.start();

        inputManager = new InputManager();
    }

    void startRun(Run run) {
        this.run = run;
        Character c = this.run.character;
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

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (run.getHp() % 2 == 0) {
            for (int i = 0; i < run.getHp(); i+=2) {
                g.drawImage(fullHeart, i*fullHeart.getWidth(null), 10, null);
            }
        } else {
            for (int i = 0; i < run.getHp()-1; i+=2) {
                g.drawImage(fullHeart, i*fullHeart.getWidth(null), 10, null);
            }
            g.drawImage(halfHeart, (run.getHp() - 1) * fullHeart.getWidth(null), 10, null);
        }

        run.getCurrentFloor().getCurrentRoom().draw(g);

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

        testAnimation.draw(getWidth() / 2, getHeight() / 2, g);
    }

    public void startBossFight() {
        inBossFight = true;
        //TODO: BOSS FIGHT SCREEN TITLE ANIMATION THING
        bossHealth.setVisible(true);
        ArrayList<Entity> roomEntities = Run.instance.getCurrentFloor().getCurrentRoom().getEntities();
        bossFightBoss = (EntityBoss) (roomEntities.stream().filter(e -> e instanceof EntityBoss).toArray())[0];
    }

    public void endBossFight() {
        inBossFight = false;
        //bossHealth.setVisible(false);
        bossFightBoss = null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_W) {
            inputManager.setW(true);
        }
        if (i == KeyEvent.VK_A) {
            inputManager.setA(true);
        }
        if (i == KeyEvent.VK_S) {
            inputManager.setS(true);
        }
        if (i == KeyEvent.VK_D) {
            inputManager.setD(true);
        }
        if (i == KeyEvent.VK_UP) {
            inputManager.setUp(true);
        }
        if (i == KeyEvent.VK_DOWN) {
            inputManager.setDown(true);
        }
        if (i == KeyEvent.VK_LEFT) {
            inputManager.setLeft(true);
        }
        if (i == KeyEvent.VK_RIGHT) {
            inputManager.setRight(true);
        }
        if (i == KeyEvent.VK_ESCAPE) {
            Run.instance.setHp(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_W) {
            inputManager.setW(false);
        }
        if (i == KeyEvent.VK_A) {
            inputManager.setA(false);
        }
        if (i == KeyEvent.VK_S) {
            inputManager.setS(false);
        }
        if (i == KeyEvent.VK_D) {
            inputManager.setD(false);
        }
        if (i == KeyEvent.VK_UP) {
            inputManager.setUp(false);
        }
        if (i == KeyEvent.VK_DOWN) {
            inputManager.setDown(false);
        }
        if (i == KeyEvent.VK_LEFT) {
            inputManager.setLeft(false);
        }
        if (i == KeyEvent.VK_RIGHT) {
            inputManager.setRight(false);
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
                inputManager.update();
                if (inBossFight) {
                    bossHealth.setValue((int) bossFightBoss.getHealth());
                }
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

    private class InputManager {

        private boolean isW;
        private boolean isA;
        private boolean isS;
        private boolean isD;
        private boolean isUp;
        private boolean isDown;
        private boolean isLeft;
        private boolean isRight;

        void update() {
            if (isW && isS) {
                run.getPlayer().setDy(0);
            } else {
                if (isW) {
                    run.getPlayer().setDy((int) -run.getSpeed());
                } else if (isS) {
                    run.getPlayer().setDy((int) run.getSpeed());
                } else {
                    run.getPlayer().setDy(0);
                }
            }
            if (isA && isD) {
                run.getPlayer().setDx(0);
            } else {
                if (isA) {
                    run.getPlayer().setDx((int) -run.getSpeed());
                } else if (isD) {
                    run.getPlayer().setDx((int) run.getSpeed());
                } else {
                    run.getPlayer().setDx(0);
                }
            }

            if (!isW && !isA && !isS && !isD) {
                run.getPlayer().setDx(0);
                run.getPlayer().setDy(0);
            }

            if (canFire) {
                if (isUp && isDown) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(1));
                    framesSinceShot = 0;
                    canFire = false;
                } else if (isUp) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(1));
                    framesSinceShot = 0;
                    canFire = false;
                } else if (isDown) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(3));
                    framesSinceShot = 0;
                    canFire = false;
                } else if (isLeft && isRight) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(0));
                    framesSinceShot = 0;
                    canFire = false;
                } else if (isLeft) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(0));
                    framesSinceShot = 0;
                    canFire = false;
                } else if (isRight) {
                    run.getCurrentFloor().getCurrentRoom().addEntity(run.getPlayer().fireShot(2));
                    framesSinceShot = 0;
                    canFire = false;
                }
            }
        }

        public boolean isW() {
            return isW;
        }

        public void setW(boolean w) {
            isW = w;
        }

        public boolean isA() {
            return isA;
        }

        public void setA(boolean a) {
            isA = a;
        }

        public boolean isS() {
            return isS;
        }

        public void setS(boolean s) {
            isS = s;
        }

        public boolean isD() {
            return isD;
        }

        public void setD(boolean d) {
            isD = d;
        }

        public boolean isUp() {
            return isUp;
        }

        public void setUp(boolean up) {
            isUp = up;
        }

        public boolean isDown() {
            return isDown;
        }

        public void setDown(boolean down) {
            isDown = down;
        }

        public boolean isLeft() {
            return isLeft;
        }

        public void setLeft(boolean left) {
            isLeft = left;
        }

        public boolean isRight() {
            return isRight;
        }

        public void setRight(boolean right) {
            isRight = right;
        }
    }
}

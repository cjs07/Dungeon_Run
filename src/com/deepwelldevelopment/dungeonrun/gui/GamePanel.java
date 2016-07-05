package com.deepwelldevelopment.dungeonrun.gui;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener, Runnable{

    public static int width;
    public static int height;

    public boolean isActive;

    boolean paused;

    Thread gameThread = new Thread(this);

    GameFrame frame;
    Run run;

    Image fullHeart;
    Image halfHeart;

    public GamePanel(GameFrame frame) {
        this.frame = frame;
        run = null;
        fullHeart = new ImageIcon("res/fullheart.png").getImage();
        halfHeart = new ImageIcon("res/halfheart.png").getImage();
    }

    public void startRun(Run run) {
        this.run = run;
        Character c = this.run.character;
        System.out.print(c.id + c.name + c.hp + c.damage + c.fireDelay + c.fireRate + c.accuracy + c.speed + c.range + c.luck);
        run.generate();
        paused = false;
        gameThread.start();
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (run.getHp() % 2 == 0) {
            for (int i = 0; i < run.getHp(); i+=2) {
                g.drawImage(fullHeart, i*fullHeart.getWidth(null), 20, null);
            }
        } else {
            for (int i = 0; i < run.getHp()-1; i+=2) {
                g.drawImage(fullHeart, i*fullHeart.getWidth(null), 20, null);
            }
            g.drawImage(halfHeart, (((run.getHp()-1)/2)*fullHeart.getWidth(null)) + (2*fullHeart.getWidth(null)), 20, null);
        }

        run.getCurrentFloor().getCurrentRoom().draw(this, g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                Run.instance.getPlayer().setDx(0).setDy(-1);
                break;
            case KeyEvent.VK_A:
                Run.instance.getPlayer().setDx(-1).setDy(0);
                break;
            case KeyEvent.VK_S:
                Run.instance.getPlayer().setDx(0).setDy(1);
                break;
            case KeyEvent.VK_D:
                Run.instance.getPlayer().setDx(1).setDy(0);
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_RIGHT:
                break;
            case KeyEvent.VK_ESCAPE:
                break;
            default:
                break;
        }
        System.out.println("key pressed: " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_D) {
            run.getPlayer().setDx(0).setDy(0);
        }
    }

    @Override
    public void run() {
        while (true) {
            if (!paused) {
                run.getCurrentFloor().getCurrentRoom().update();
                repaint();
                revalidate();
                System.out.println("frame completed: " + System.currentTimeMillis());
                try {
                    Thread.sleep(1000/60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

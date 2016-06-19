package com.deepwelldevelopment.dungeonrun.gui;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener, Runnable{

    public boolean isActive;

    boolean paused;

    GameFrame frame;
    Run run;

    public GamePanel(GameFrame frame) {
        this.frame = frame;
        run = null;
    }

    public void startRun(Run run) {
        this.run = run;
        Character c = this.run.character;
        System.out.print(c.id + c.name + c.hp + c.damage + c.fireDelay + c.fireRate + c.accuracy + c.speed + c.range + c.luck);
        run.generate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_A:
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_D:
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            if (!paused) {
                repaint();
                revalidate();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

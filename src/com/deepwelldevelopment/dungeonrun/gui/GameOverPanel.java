package com.deepwelldevelopment.dungeonrun.gui;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOverPanel extends JPanel implements KeyListener, Runnable {

    public boolean isActive;

    Thread thraad = new Thread(this);

    GameFrame frame;
    int previousClass;

    public GameOverPanel(GameFrame frame) {
        this.frame = frame;

        thraad.start();
    }

    public void setPreviousClass(int previousClass) {
        this.previousClass = previousClass;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Time New Roman", Font.BOLD, 24));
        g.drawString("you dead", getWidth() / 2, getHeight() / 2);
        g.drawString("press space to restart", getWidth() / 8, 3 * (getHeight() / 4));
        g.drawString("press escape to quit", 5 * (getWidth() / 8), 3 * (getHeight() / 4));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                isActive = false;
                frame.gp.isActive = true;
                frame.removeKeyListener(this);
                frame.addKeyListener(frame.gp);
                frame.setContentPane(frame.gp);
                frame.gp.startRun(new Run(Character.characters[previousClass], frame.gp));
                break;
            case KeyEvent.VK_ESCAPE:
                isActive = false;
                frame.mmp.isActive = true;
                frame.removeKeyListener(this);
                frame.addKeyListener(frame.mmp);
                frame.setContentPane(frame.mmp);
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
            if (isActive) {
                repaint();
                revalidate();
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

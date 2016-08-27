package com.deepwelldevelopment.dungeonrun.gui;

import com.deepwelldevelopment.dungeonrun.engine.characters.Character;
import com.deepwelldevelopment.dungeonrun.engine.run.Run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClassSelectPanel extends JPanel implements KeyListener, Runnable {

    public boolean isActive;

    Thread thread = new Thread(this);

    GameFrame frame;

    //assets
    Image[] classSprites;

    int selectedClass;

    public ClassSelectPanel(GameFrame frame) {
        this.frame = frame;

        classSprites = new Image[10];
        classSprites[0] = new ImageIcon("res/huntericon.png").getImage();
        for (int i = 0; i < classSprites.length; i++) {
            classSprites[i] = new ImageIcon("res/huntericon.png").getImage();
        }
        selectedClass = 0;

        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(23, 165, 25, 88));
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int i = selectedClass; i < classSprites.length; i++) {
            g.drawImage(classSprites[i], ((getWidth()/2)-(classSprites[i].getWidth(null))+(classSprites[i].getWidth(null)*(i-selectedClass))),
                    (getHeight()/2)-(classSprites[i].getHeight(null)/2), null);
        }

        for (int i = selectedClass-1; i >= 0; i--) {
            g.drawImage(classSprites[i], ((getWidth()/2)-(classSprites[i].getWidth(null))-(classSprites[i].getWidth(null)*i)),
                    (getHeight()/2)-(classSprites[i].getHeight(null)/2), null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (isActive) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT) {
                if (selectedClass > 0) {
                    selectedClass--;
                }
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                if (selectedClass < classSprites.length) {
                    selectedClass++;
                }
            } else if (keyCode == KeyEvent.VK_ENTER) {
                isActive = false;
                frame.gp.isActive = true;
                frame.removeKeyListener(this);
                frame.addKeyListener(frame.gp);
                frame.setContentPane(frame.gp);
                frame.gp.startRun(new Run(Character.characters[selectedClass], frame.gp));
            } else if (keyCode == KeyEvent.VK_ESCAPE) {
                isActive = false;
                frame.mmp.isActive = true;
                frame.removeKeyListener(this);
                frame.addKeyListener(frame.mmp);
                frame.setContentPane(frame.mmp);
            }
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
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

package com.deepwelldevelopment.dungeonrun.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainMenuPanel extends JPanel implements KeyListener, Runnable {

    public boolean isActive;

    Thread thread = new Thread(this);

    GameFrame frame;

    //assets
    Image cursor;
    Image newGame;
    Image loadGame;
    Image options;

    int cursorLocation;

    public MainMenuPanel(GameFrame frame) {
        cursor = new ImageIcon("res/cursor.png").getImage();
        newGame = new ImageIcon("res/newgame.png").getImage();
        loadGame = new ImageIcon("res/loadgame.png").getImage();
        options = new ImageIcon("res/options.png").getImage();
        cursorLocation = 0;
        this.frame = frame;
        this.frame.addKeyListener(this);
        thread.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(23, 165, 25, 88));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(newGame, (getWidth()/2)-(newGame.getWidth(null)/2),
                (getHeight()/2)-(newGame.getHeight(null)/2)-newGame.getHeight(null), null);
        g.drawImage(loadGame, (getWidth()/2)-(loadGame.getWidth(null)/2),
                (getHeight()/2)-(loadGame.getHeight(null)/2), null);
        g.drawImage(options, (getWidth()/2)-(options.getWidth(null)/2),
                (getHeight()/2)-(options.getHeight(null)/2)+options.getHeight(null), null);

        switch (cursorLocation) {
            case 0:
                g.drawImage(cursor, ((getWidth()/2)-(newGame.getWidth(null)/2)) - (cursor.getWidth(null)),
                        (getHeight()/2)-(cursor.getHeight(null)/2)-newGame.getHeight(null), null);
                break;
            case 1:
                g.drawImage(cursor, ((getWidth()/2)-(newGame.getWidth(null)/2)) - (cursor.getWidth(null)),
                        (getHeight()/2)-(cursor.getHeight(null)/2), null);
                break;
            case 2:
                g.drawImage(cursor, ((getWidth()/2)-(newGame.getWidth(null)/2)) - (cursor.getWidth(null)),
                        (getHeight()/2)-(cursor.getHeight(null)/2)+options.getHeight(null), null);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (isActive) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_DOWN) {
                if (!(cursorLocation >= 2)) {
                    cursorLocation++;
                }
            } else if (keyCode == KeyEvent.VK_UP) {
                if (!(cursorLocation <= 0)) {
                    cursorLocation--;

                }
            } else if (keyCode == KeyEvent.VK_ENTER) {
                isActive = false;
                frame.removeKeyListener(this);
                frame.addKeyListener(frame.csp);
                switch (cursorLocation) {
                    case 0:
                        frame.csp.isActive = true;
                        frame.setContentPane(frame.csp);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            } else if (keyCode == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
            System.out.println("key pressed");
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
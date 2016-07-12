package com.deepwelldevelopment.dungeonrun.gui;

import com.deepwelldevelopment.dungeonrun.engine.DungeonRun;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public MainMenuPanel mmp;
    ClassSelectPanel csp;
    GamePanel gp;
    GameOverPanel gop;

    public GameFrame() {
        super("Dungeon Run");
        setSize(800, 600);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        DungeonRun.library.setScreenWidth(getWidth());
        DungeonRun.library.setScreenHeight(getHeight());
        mmp = new MainMenuPanel(this);
        csp = new ClassSelectPanel(this);
        gp = new GamePanel(this);
        gop = new GameOverPanel(this);
        mmp.isActive = true;
        setContentPane(mmp);
    }
}

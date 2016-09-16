package com.deepwelldevelopment.dungeonrun.engine.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private Image[] frames;

    private int currentFrame;
    private boolean paused;

    public Animation(String path, int frameX, int frameY, int frames) {
        Image image = new ImageIcon(path).getImage();
        BufferedImage spriteSheet = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        spriteSheet.getGraphics().drawImage(image, 0, 0, null);

        this.frames = new Image[frames];

        int i = 0;
        for (int x = 0; x < spriteSheet.getWidth(); x += frameX) {
            for (int y = 0; y < spriteSheet.getHeight(); y += frameY) {
                this.frames[i] = spriteSheet.getSubimage(x, y, frameX, frameY);
                i++;
            }
        }
    }

    public void start() {
        currentFrame = 0;
        paused = false;
    }

    public void restart() {
        currentFrame = 0;
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public void draw(int x, int y, Graphics g) {
        if (!paused) {
            g.drawImage(frames[currentFrame], x, y, null);
            currentFrame++;
            if (currentFrame == frames.length) {
                currentFrame = 0;
            }
        }
    }
}

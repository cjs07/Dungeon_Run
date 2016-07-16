package com.deepwelldevelopment.dungeonrun.lib;

public class Library {

    final int wallOffset = 6;

    int screenWidth;
    int screenHeight;
    int doorOffset;

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getDoorOffset() {
        return doorOffset;
    }

    public void setDoorOffset(int doorOffset) {
        this.doorOffset = doorOffset;
    }

    public int getWallOffset() {
        return wallOffset;
    }
}

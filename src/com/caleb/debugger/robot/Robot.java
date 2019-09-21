package com.caleb.debugger.robot;

//TODO: javadocs

import com.caleb.debugger.utils.CoordinateUtils;
import com.caleb.debugger.utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */
public class Robot {

    // 75 pixels = 18 inches for a 600x600 pixel field
    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;

    private BufferedImage robotImg;

    // x and y are ranged from -72 to 72
    private int x;
    private int y;

    /**
     *
     * @param x
     * @param y
     */
    public Robot(int x, int y) {
        //TODO: adjust x and y for field positioning instead of pixels
        robotImg = ImageLoader.loadImage("robot.png");
        this.x = x;
        this.y = y;
    }

    /**
     *
     */
    public void update() {

    }

    /**
     *
     * @param g
     */
    public void render(Graphics g) {
        // convert positions to pixel position, and subtract half of the width/height
        // to change from center coordinates to top-left corner coordinates
        final int adjX = CoordinateUtils.posToPixel(x) - (WIDTH / 2);
        final int adjY = CoordinateUtils.posToPixel(y) - (HEIGHT / 2);
        g.drawImage(robotImg, adjX, adjY, WIDTH, HEIGHT, null);
    }

}

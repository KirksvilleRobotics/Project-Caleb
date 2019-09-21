package com.caleb.debugger.robot;

import com.caleb.debugger.utils.CoordinateUtils;
import com.caleb.debugger.utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Robot
 *
 * This class represents the robot drawn on the screen based on the
 * positions given by the physical robot. It stores x, y, and
 * velocity values based on physical field inches, and it is drawn
 * with a size equivalent to 18x18 inches with respect to a 600x600 pixel
 * screen / field
 */
public class Robot {

    // 75 pixels = 18 inches for a 600x600 pixel field
    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;

    private BufferedImage robotImg;

    // x and y are ranged from -72 to 72
    private double x;
    private double y;

    // vx and vy are meant to be velocity in inches/second
    private double vx;
    private double vy;

    /**
     * Constructor for the robot
     * This constructor sets the position of the robot, loads the
     * image stored as res/robot.png, and it sets the x and y
     * velocities to 0
     * @param x horizontal position of the robot in inches from [-72, 72]
     * @param y vertical position of the robot in inches from [-72, 72]
     */
    public Robot(double x, double y) {
        robotImg = ImageLoader.loadImage("robot.png");
        this.x = x;
        this.y = y;
        vx = 0.0;
        vy = 0.0;
    }

    /**
     * This method updates the position based on the velocity
     * assuming it gets called 60 times per second
     */
    public void update() {
        // since the robot updates 60 times per second, divide the per-second velocity by 60
        x += (vx / 60.0);
        y += (vy / 60.0);
    }

    /**
     * This method draws the robot each frame based on the x and y
     * position. It converts the positions in inches to screen pixels
     * the draws the BufferedImage
     * Note: the position represents the center of the robot, not the
     * top left corner.
     *
     * @param g instance of java.awt.Graphics, meant to be from
     *          the Field's graphics made each render call
     */
    public void render(Graphics g) {
        // convert positions to pixel position, and subtract half of the width/height
        // to change from center coordinates to top-left corner coordinates
        final double adjX = CoordinateUtils.posToPixel(x) - (WIDTH / 2.0);
        final double adjY = CoordinateUtils.posToPixel(y) - (HEIGHT / 2.0);
        g.drawImage(robotImg, (int)adjX, (int)adjY, WIDTH, HEIGHT, null);
    }

}

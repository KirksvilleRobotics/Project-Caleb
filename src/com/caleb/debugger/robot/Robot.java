package com.caleb.debugger.robot;

import com.caleb.debugger.graphics.Info;
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

    private Info info;

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

    private double theta;
    private double rotVel;

    /**
     * Constructor for the robot
     * This constructor sets the position of the robot, loads the
     * image stored as res/robot.png, and it sets the x and y
     * velocities to 0
     * @param x horizontal position of the robot in inches from [-72, 72]
     * @param y vertical position of the robot in inches from [-72, 72]
     * @param info instance of Info needed to update position on
     *             info bar
     */
    public Robot(double x, double y, Info info) {
        robotImg = ImageLoader.loadImage("robot.png");
        this.info = info;
        this.x = x;
        this.y = y;
        vx = 0.0;
        vy = 0.0;
        // Theta and rotVel are both in degrees
        theta = 0.0;
        // rotVel is the rotation velocity in degrees per second
        rotVel = 0.0;
    }

    /**
     * This method updates the position based on the velocity
     * assuming it gets called 60 times per second
     */
    public void update() {
        // since the robot updates 60 times per second, divide the per-second velocity by 60
        x += (vx / 60.0);
        y += (vy / 60.0);
        theta += (rotVel / 60.0);
        if(theta >= 360.0) {
            theta -= 360.0;
        }
        // round x and y to nearest hundredth
        double roundX = Math.round(x * 100) / 100.0;
        double roundY = Math.round(y * 100) / 100.0;
        double roundTheta = Math.round(theta * 100) / 100.0;
        info.updateXPosition(Double.toString(roundX));
        info.updateYPosition(Double.toString(roundY));
        info.updateTheta(Double.toString(roundTheta));
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

        // Cast to graphics2d for rotation
        Graphics2D g2 = (Graphics2D) g;
        // Add half width and height back for g2.rotate() so it rotates around the center
        g2.rotate(Math.toRadians(theta), adjX + (WIDTH / 2.0), adjY + (HEIGHT / 2.0));
        g2.drawImage(robotImg, (int)adjX, (int)adjY, WIDTH, HEIGHT, null);
        // Rotate back so it doesn't screw up other rendering, only rotate to draw the robot
        g2.rotate(Math.toRadians(-theta), adjX, adjY);
    }

}

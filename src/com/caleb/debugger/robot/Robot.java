package com.caleb.debugger.robot;

import com.caleb.debugger.Main;
import com.caleb.debugger.graphics.Info;
import com.caleb.debugger.utils.CoordinateUtils;
import com.caleb.debugger.utils.ImageLoader;
import com.caleb.logging.LogFileOut;

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
public class Robot implements Runnable {

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

        new Thread(this, "Robot").start();

    }

    public void run() {
        /*move(0.0, 50.0, 20.0);
        waitForNextMovement();
        move(-90.0, 0.0, 20.0);
        waitForNextMovement();
        move(0.0, -80.0, 20.0);
        waitForNextMovement();
        move(100.0, 100.0, 20.0);
        waitForNextMovement();
        move(-20, -80, 20.0);*/
    }

    /**
     * This method waits for a robot movement to be done which
     * should be called in between move() or goToPosition() calls
     *
     * Note: This method should only be called on this thread,
     * or a thread besides the main thread or else it will
     * disrupt the window loop
     */
    private void waitForNextMovement() {
        while(vx != 0.0 && vy != 0.0 && rotVel != 0.0) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method updates the position based on the velocity
     * assuming it gets called 60 times per second
     */
    public void update() {
        // since the robot updates 60 times per second, divide the per-second velocity by 60
        x += (vx / Main.getTickRate());
        y += (vy / Main.getTickRate());
        theta += (rotVel / Main.getTickRate());
        if(theta >= 360.0) {
            theta -= 360.0;
        }
        // round x and y to nearest hundredth
        double roundX = Math.round(x * 10) / 10.0;
        double roundY = Math.round(y * 10) / 10.0;
        double roundTheta = Math.round(theta * 10) / 10.0;
        info.updateXPosition(Double.toString(roundX));
        info.updateYPosition(Double.toString(roundY));
        info.updateTheta(Double.toString(roundTheta));
    }

    /**
     * This method draws the robot each frame based on the x and y
     * position. It converts the positions in inches to screen pixels
     * the draws the BufferedImage
     *
     * Note: the position represents the center of the robot, not the
     * top left corner.
     *
     * @param g instance of java.awt.Graphics, meant to be from
     *          the Field's graphics made each render call
     */
    public void render(Graphics g) {
        // convert positions to pixel position, and subtract half of the width/height
        // to change from center coordinates to top-left corner coordinates
        final double adjX = CoordinateUtils.posToPixel(x, false) - (WIDTH / 2.0);
        final double adjY = CoordinateUtils.posToPixel(y, true) - (HEIGHT / 2.0);

        // Cast to graphics2d for rotation
        Graphics2D g2 = (Graphics2D) g;
        // Add half width and height back for g2.rotate() so it rotates around the center
        g2.rotate(Math.toRadians(theta), adjX + (WIDTH / 2.0), adjY + (HEIGHT / 2.0));
        g2.drawImage(robotImg, (int)adjX, (int)adjY, WIDTH, HEIGHT, null);
        // Rotate back so it doesn't screw up other rendering, only rotate to draw the robot
        g2.rotate(Math.toRadians(-theta), adjX, adjY);
    }

    /**
     * This function is mainly just for simulation of this
     * application. It isn't accurate enough to be trusted for
     * logging or real robot movement.
     *
     * Note: This method should only be called on this thread,
     * or a thread besides the main thread or else it will
     * disrupt the window loop
     *
     * @param xDest x-coordinate destination for the robot on [-72, 72]
     * @param yDest y-coordinate destination for the robot on [-72, 72]
     * @param speed speed for robot movement, and rotation in
     *              inches per second
     */
    public void goToPosition(double xDest, double yDest, double speed) {
        // Create a new thread so the position check loops don't interfere with the window loop
        // Get the distance between the x's and the y's
        double dx = xDest - x;
        double dy = yDest - y;

        // Calculate the needed angle of rotation
        double thetaDest = (double)Math.round(Math.toDegrees(Math.atan2(dx, dy)));
        double dTheta = thetaDest - theta;

        if(dTheta > 180) {
            thetaDest -= 360;
        } else if(dTheta < -180) {
            thetaDest += 360;
        }
        System.out.println(thetaDest);
        System.out.println(theta);
        int direction = 1;
        if(thetaDest < theta) {
            direction = -1;
        }

        rotVel = speed * 9 * direction; // 9 is width of the robot (in inches) / 2
        // Check if the rotation is complete 1000 times per second
        while(Math.round(theta * 10) / 10.0 != thetaDest) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        rotVel = 0.0;

        // Leave some time in between rotation and movement
        try {
            Thread.sleep(300);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        double distance = Math.sqrt((dx * dx) + (dy * dy));

        // Treat x and y velocity as a normalized vector and
        // multiply it by speed
        vx = (dx / distance) * speed;
        vy = (dy / distance) * speed;
        System.out.println("Target x velocity: " + vx);
        System.out.println("Target y velocity: " + vy);

        // check if the movement is done (to the nearest 10th) 1000 times per second
        while(Math.round(x * 10) / 10.0 != xDest || Math.round(y * 10) / 10.0 != yDest) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        vx = 0.0;
        vy = 0.0;

        // sleep 300 millis to pause in between commands
        try {
            Thread.sleep(300);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function does the exact same as goToPosition(),
     * but it moves to a destination based on its current
     * position
     *
     * Note: This method should only be called on this thread,
     * or a thread besides the main thread or else it will
     * disrupt the window loop
     *
     * @param dx number of inches to move horizontally
     * @param dy number of inches to move vertically
     * @param speed movement speed in inches per second
     */
    public void move(double dx, double dy, double speed) {
        goToPosition((double)Math.round(x + dx), (double)Math.round(y + dy), speed);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }
}

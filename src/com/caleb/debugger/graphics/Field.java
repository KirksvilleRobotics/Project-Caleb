package com.caleb.debugger.graphics;

import com.caleb.debugger.utils.CoordinateUtils;
import com.caleb.debugger.utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Field
 *
 * This class represents the field for the robot challenge.
 * It uses the java.awt.Canvas class to handle all the rendering, and
 * it needs an image file of the same dimensions as the field to draw
 *
 */
public class Field {

    private Canvas canvas;
    private BufferedImage fieldImg;

    LinkedList<LocationMarker> pastLocations;

    private int cycle;

    private com.caleb.debugger.robot.Robot robot;

    /**
     * Constructor for the field class
     *
     * This constructor creates a canvas, and it initializes the
     * BufferedImage to draw the field
     * @param width field width in pixels
     * @param height field height in pixels
     * @param window instance of Window class just used to pass info
     *               into the robot class
     */
    public Field(int width, int height, Window window) {
        cycle = 0;
        pastLocations = new LinkedList<>();
        fieldImg = ImageLoader.loadImage("field.png");
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));

        robot = new com.caleb.debugger.robot.Robot(50, -30, window.getInfo());
    }

    /**
     * This method gets called by the window 60 times per second
     * to update any logic within the field, mostly the robot
     */
    public void update() {
        cycle++;
        for(LocationMarker marker : pastLocations) {
            marker.update();
        }
        if(cycle == 2) {
            cycle = 0;
            pastLocations.add(new LocationMarker(
                    (int) CoordinateUtils.posToPixel(robot.getX(), false),
                    (int) CoordinateUtils.posToPixel(robot.getY(), true)));
        }
        robot.update();
    }

    /**
     * This function is to be called each frame inside of a window
     * loop. It gets a BufferStrategy from the canvas, and uses the
     * BufferStrategy's getDrawGraphics() method to draw the field
     */
    public void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.drawImage(fieldImg, 0, 0, null);
        for(LocationMarker marker : pastLocations) {
            marker.render(g);
        }
        robot.render(g);

        g.dispose();
        bs.show();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}

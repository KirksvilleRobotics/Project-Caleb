package com.caleb.debugger.graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

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

    /**
     * Constructor for the field class
     *
     * This constructor creates a canvas, and it initializes the
     * BufferedImage to draw the field
     * @param width field width in pixels
     * @param height field height in pixels
     */
    public Field(int width, int height) {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
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

        //TODO: get image of field, initialize the BufferedImage, and render that instead
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        g.dispose();
        bs.show();
    }

}

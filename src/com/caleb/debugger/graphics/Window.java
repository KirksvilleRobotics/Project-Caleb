package com.caleb.debugger.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Window
 *
 * This class represents the window for the application. The window
 * for this project consists of two main sections: the field, and
 * the info section
 */
public class Window {
    private JFrame frame;
    private JPanel contentPane;

    /**
     * Constructor for the window
     *
     * This constructor initializes the JFrame, creates the
     * contentPane, and adds the application's components
     * @param title String representing the title of the window
     * @param width window width in pixels
     * @param height window height in pixels
     */
    public Window(String title, int width, int height) {
        frame = new JFrame(title);

        contentPane = (JPanel) frame.getContentPane();
        contentPane.setPreferredSize(new Dimension(width, height));
        contentPane.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Render
     *
     * This method updates the graphical display of the window and
     * of all its components. It is meant to be called within a
     * loop each frame
     */
    public void render() {

    }
}

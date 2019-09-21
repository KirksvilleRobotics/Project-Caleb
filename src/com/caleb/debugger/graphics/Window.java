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

    private Field field;
    private Info info;

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

        contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(width, height));
        contentPane.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        info = new Info(200, 600);
        field = new Field(600, 600, this);

        contentPane.add(field.getCanvas());
        contentPane.add(info.getPanel());

        frame.setContentPane(contentPane);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * This method updates any logic within the window
     */
    public void update() {
        field.update();
    }

    public void updateElapsedTime(long elapsedTime) {
        double timeDouble = (elapsedTime) / 1000.0;
        info.updateTime(Double.toString(timeDouble));
    }

    /**
     * This method updates the graphical display of the window and
     * of all its components. It is meant to be called within a
     * loop each frame
     */
    public void render() {
        field.render();
    }

    public Info getInfo() {
        return info;
    }
}

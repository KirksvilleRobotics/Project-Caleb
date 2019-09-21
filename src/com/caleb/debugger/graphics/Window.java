package com.caleb.debugger.graphics;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;
    private JPanel contentPane;

    public Window(String title, int width, int height) {
        frame = new JFrame(title);

        contentPane = (JPanel) frame.getContentPane();
        contentPane.setPreferredSize(new Dimension(width, height));
        contentPane.setLayout(null);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

package com.caleb.debugger.graphics;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

//TODO Get rid of magic numbers

/**
 * Info
 *
 * This class displays info relating to the robot's current position
 * and time that has elapsed. It uses JPanels and JLabels to achieve this.
 * Data displayed:
 * - RobotX
 * - RobotY
 * - RobotTheta
 * - TimeElapsed
 */
public class Info {
    private JPanel panel;

    private JLabel xPosition;
    private JLabel yPosition;
    private JLabel theta;
    private JLabel time;

    /**
     * This method sets up the JPanel and JLabels for the display.
     * It creates labels to store descriptors and data for the
     * four stored values.
     *
     * @param width width of the Info panel
     * @param height height of the Info panel
     */
    public Info(int width, int height) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        panel.setLayout(null);

        JLabel xLabel = new JLabel("Robot X: ");
        xLabel.setBounds(25, 25, 100, 25);
        xLabel.setVerticalAlignment(SwingConstants.TOP);
        xLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(xLabel);

        JLabel yLabel = new JLabel("Robot Y: ");
        yLabel.setBounds(25, 50, 100, 25);
        yLabel.setVerticalAlignment(SwingConstants.TOP);
        yLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(yLabel);

        JLabel thetaLabel = new JLabel("Robot Angle: ");
        thetaLabel.setBounds(25, 75, 100, 25);
        thetaLabel.setVerticalAlignment(SwingConstants.TOP);
        thetaLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(thetaLabel);

        JLabel timeLabel = new JLabel("Time Elapsed: ");
        timeLabel.setBounds(25, 100, 100, 25);
        timeLabel.setVerticalAlignment(SwingConstants.TOP);
        timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(timeLabel);

        xPosition = new JLabel("12.0");
        xPosition.setBounds(125, 25, 50, 25);
        xPosition.setVerticalAlignment(SwingConstants.TOP);
        xPosition.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(xPosition);

        yPosition = new JLabel();
        yPosition.setBounds(125, 50, 50, 25);
        yPosition.setVerticalAlignment(SwingConstants.TOP);
        yPosition.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(yPosition);

        theta = new JLabel();
        theta.setBounds(125, 75, 50, 25);
        theta.setVerticalAlignment(SwingConstants.TOP);
        theta.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(theta);

        time = new JLabel();
        time.setBounds(125, 100, 50, 25);
        time.setVerticalAlignment(SwingConstants.TOP);
        time.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(time);
    }

    /**
     * This method updates all four of the displayed values
     *
     * @param robotX current x-coordinate of the robot
     * @param robotY current y-coordinate of the robot
     * @param robotTheta current theta heading of the robot
     * @param timeElapsed current time elapsed since the start of debugging
     */
    public void updateData(String robotX, String robotY, String robotTheta, String timeElapsed) {
        xPosition.setText(robotX);
        yPosition.setText(robotY);
        theta.setText(robotTheta);
        time.setText(timeElapsed);
    }

    /**
     * This method updates the x-position display
     *
     * @param robotX current x-coordinate of the robot
     */
    public void updateXPosition(String robotX) {
        xPosition.setText(robotX);
    }

    /**
     * This method updates the y-position display
     *
     * @param robotY current y-coordinate of the robot
     */
    public void updateYPosition(String robotY) {
        yPosition.setText(robotY);
    }

    /**
     * This method updates the theta display
     *
     * @param robotTheta current theta heading of the robot
     */
    public void updateTheta(String robotTheta) {
        theta.setText(robotTheta);
    }

    /**
     * This method update the elapsed time display
     *
     * @param timeElapsed current time elapsed since the start of debugging
     */
    public void updateTime(String timeElapsed) {
        time.setText(timeElapsed);
    }

    /**
     * This method returns the JPanel that everything is built on
     *
     * @return the JPanel object of the info bar
     */
    public JPanel getPanel() {
        return panel;
    }
}

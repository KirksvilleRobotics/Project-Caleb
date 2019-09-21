package com.caleb.debugger.graphics;

import javax.swing.*;
import java.awt.*;

//TODO JavaDoc

/**
 *
 */
public class Info {
    private JPanel panel;

    private JLabel xPosition;
    private JLabel yPosition;
    private JLabel theta;
    private JLabel time;

    /**
     *
     * @param width
     * @param height
     */
    public Info(int width, int height) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
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
        xPosition.setHorizontalAlignment(SwingConstants.RIGHT);
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
     *
     * @param robotX
     * @param robotY
     * @param robotTheta
     * @param timeElapsed
     */
    public void updateData(String robotX, String robotY, String robotTheta, String timeElapsed) {
        xPosition.setText(robotX);
        yPosition.setText(robotY);
        theta.setText(robotTheta);
        time.setText(timeElapsed);
    }

    /**
     *
     * @param robotX
     */
    public void updateXPosition(String robotX) {
        xPosition.setText(robotX);
    }

    /**
     *
     * @param robotY
     */
    public void updateYPosition(String robotY) {
        yPosition.setText(robotY);
    }

    /**
     *
     * @param robotTheta
     */
    public void updateTheta(String robotTheta) {
        theta.setText(robotTheta);
    }

    /**
     *
     * @param timeElapsed
     */
    public void updateTime(String timeElapsed) {
        time.setText(timeElapsed);
    }

    /**
     *
     * @return
     */
    public JPanel getPanel() {
        return panel;
    }
}

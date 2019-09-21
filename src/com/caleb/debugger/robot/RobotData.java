package com.caleb.debugger.robot;

/**
 * RobotData
 *
 * This class stores robot data that is received from the physical
 * robot. It stores:
 * - x
 * - y
 * - theta
 */
public class RobotData {
    private double x;
    private double y;
    private double theta;

    /**
     * Creates an instance of RobotData to store information
     * @param x the robot's x
     * @param y the robot's y
     * @param theta the robot's theta
     */
    public RobotData(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    /**
     * Gets the robot's x location in inches. Will be in the range:
     * [-72.0, 72.0]
     *
     * @return robot's x
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the robot's y location in inches. Will be in the range:
     * [-72.0, 72.0]
     *
     * @return robot's y
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the robot's theta in degrees. Will be in the range:
     * [0.0, 360.0)
     *
     * @return robot's theta
     */
    public double getTheta() {
        return theta;
    }
}

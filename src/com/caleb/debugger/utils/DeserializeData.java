package com.caleb.debugger.utils;

import com.caleb.debugger.robot.RobotData;

import java.nio.ByteBuffer;

/**
 * DeserializeData
 *
 * This util class deserializes a packet of data in
 * the form of a byte array. It uses java.nio.ByteBuffer to
 * convert byte arrays into doubles.
 */
public class DeserializeData {
    private static final int PACKET_SIZE = 24;

    private DeserializeData() {}

    /**
     * This method deserializes a byte array from the server and
     * returns a RobotData object.
     *
     * @param data a byte array of size 24. The first eight bytes are
     *             to store x, the next 8 to store y, and the final
     *             8 to store theta.
     * @return a RobotData object that stores x, y, and theta
     */
    public static RobotData deserializeData(byte[] data) {
        if(data.length == PACKET_SIZE) {
            byte[] byteX = new byte[8];
            System.arraycopy(data, 0, byteX, 0, 8);
            double x = ByteBuffer.wrap(byteX).getDouble();

            byte[] byteY = new byte[8];
            System.arraycopy(data, 8, byteY, 0, 8);
            double y = ByteBuffer.wrap(byteY).getDouble();

            byte[] byteTheta = new byte[8];
            System.arraycopy(data, 16, byteTheta, 0, 8);
            double theta = ByteBuffer.wrap(byteTheta).getDouble();

            return new RobotData(x, y, theta);
        }

        return null;
    }
}

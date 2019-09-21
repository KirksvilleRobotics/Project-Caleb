package com.caleb.client;

import java.nio.ByteBuffer;

//TODO JavaDoc
/**
 *
 */
public class SerializeData {
    private SerializeData() {}

    /**
     *
     * @param robotX
     * @param robotY
     * @param robotTheta
     * @return
     */
    public static byte[] serialzeData(double robotX, double robotY, double robotTheta) {
        byte[] byteX = new byte[8];
        ByteBuffer.wrap(byteX).putDouble(robotX);

        byte[] byteY = new byte[8];
        ByteBuffer.wrap(byteY).putDouble(robotY);

        byte[] byteTheta = new byte[8];
        ByteBuffer.wrap(byteTheta).putDouble(robotTheta);

        byte[] data = new byte[24];
        System.arraycopy(byteX, 0, data, 0, 8);
        System.arraycopy(byteY, 0, data, 8, 8);
        System.arraycopy(byteTheta, 0, data, 16, 8);

        return data;
    }
}

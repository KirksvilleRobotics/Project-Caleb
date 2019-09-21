package com.caleb.client;

import java.io.IOException;
import java.net.*;

import static com.caleb.client.SerializeData.serialzeData;

//TODO JavaDoc
/**
 *
 */
public class Debugger {
    private InetAddress ip;
    private DatagramSocket socket;
    private int port;

    /**
     *
     * @param address
     * @param port
     * @throws UnknownHostException
     * @throws SocketException
     */
    public Debugger(String address, int port) throws UnknownHostException, SocketException {
        ip = InetAddress.getByName(address);
        socket = new DatagramSocket();
        this.port = port;
    }

    /**
     *
     * @param robotX
     * @param robotY
     * @param robotTheta
     */
    public void send(double robotX, double robotY, double robotTheta) {
        byte[] data = serialzeData(robotX, robotY, robotTheta);

        Thread send = new Thread(() -> {
            DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "Send");
        send.start();
    }
}

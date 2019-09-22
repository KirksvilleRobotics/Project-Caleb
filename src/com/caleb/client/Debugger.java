package com.caleb.client;

import java.io.IOException;
import java.net.*;

import static com.caleb.client.SerializeData.serializeData;

/**
 * Debugger
 *
 * This class is the core of a client side FTC debugging utility.
 * To use this class, you must set up a connection to the server
 * side of the debugging software by defining an ip address and a
 * port for the socket. After that, you can send packets of data
 * the .send() function. Each packet contains the robot's x, y, and
 * theta.
 */
public class Debugger {
    private InetAddress ip;
    private DatagramSocket socket;
    private int port;

    /**
     * Sets up a connection to the debugger
     *
     * @param address ip address of the debugging software
     * @param port port for the socket to be created on
     * @throws UnknownHostException
     * @throws SocketException
     */
    public Debugger(String address, int port) throws UnknownHostException, SocketException {
        ip = InetAddress.getByName(address);
        socket = new DatagramSocket();
        this.port = port;
    }

    /**
     * This function sends a UDP data packet to the debugging
     * software. It uses the function serializeData() to create
     * a byte array of robotX, robotY, and robotTheta. Packets are
     * sent on their own thread.
     *
     * @param robotX robot's current x coordinate
     * @param robotY robot's current y coordinate
     * @param robotTheta robot's current theta
     */
    public void send(double robotX, double robotY, double robotTheta) {
        byte[] data = serializeData(robotX, robotY, robotTheta);

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

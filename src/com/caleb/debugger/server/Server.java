package com.caleb.debugger.server;

import com.caleb.debugger.robot.RobotData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static com.caleb.debugger.utils.DeserializeData.deserializeData;

/**
 * Server
 *
 * This class is a server that receives data from the physical robot.
 * It uses the UDP packet protocol and deserializes the packet into
 * a RobotData object. The info that is received is:
 * - robot x coordinate
 * - robot y coordinate
 * - robot theta
 */
public class Server implements Runnable {
    private DatagramSocket socket;
    private boolean running = false;

    /**
     * This method creates a new socket on the given port and starts
     * the thread that the server runs on.
     *
     * @param port the port number to create the socket on
     */
    public Server(int port) {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        new Thread(this, "Server").start();
    }

    @Override
    public void run() {
        running = true;
        receive();
    }

    /**
     * This method receives data from the client. It first receives a
     * packet and then deserialzes the packet into a RobotData
     * object.
     */
    private void receive() {
        //TODO pass deserialized data to the Robot Class
        //deserializeData(robotData);
        Thread receive = new Thread(() -> {
            while (running) {
                byte[] data = new byte[24];
                DatagramPacket packet = new DatagramPacket(data, data.length);

                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //TODO pass deserialized data to the Robot Class
                RobotData robotData = deserializeData(packet.getData());
            }
        }, "Receive");
        receive.start();
    }
}

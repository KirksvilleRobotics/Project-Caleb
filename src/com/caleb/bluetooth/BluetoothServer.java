package com.caleb.bluetooth;

import com.caleb.client.SerializeData;
import com.caleb.debugger.robot.RobotData;
import com.caleb.debugger.utils.DeserializeData;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.IOException;
import java.io.InputStream;

//TODO: testing, and javadocs

public class BluetoothServer implements Runnable {

    // 0c15f6a2-e4bb-11e9-81b4-2a2ae2dbcce4
    private static final UUID SERVER_UUID = new UUID("0c15f6a2e4bb11e981b42a2ae2dbcce4", false);
    private static final String SERVER_ADDRESS = "btspp://localhost:" + SERVER_UUID.toString() + ";name=BluetoothServer";

    private LocalDevice localDevice;
    private RobotData currentPosition = null;

    private StreamConnection connection;
    private StreamConnectionNotifier notifier;

    private Thread thread;

    private InputStream inputStream;

    private boolean running;

    public BluetoothServer() {
        running = false;
        try {
            localDevice = LocalDevice.getLocalDevice();
            localDevice.setDiscoverable(DiscoveryAgent.GIAC);
            notifier = (StreamConnectionNotifier) Connector.open(SERVER_ADDRESS);
        } catch(BluetoothStateException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        thread = new Thread(this, "BluetoothServer");
    }

    public void run() {
        try {
            connection = notifier.acceptAndOpen();
            inputStream = connection.openInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(running) {
            try {
                if (inputStream.available() > 0) {
                    byte[] data = new byte[inputStream.available()];
                    currentPosition = DeserializeData.deserializeData(data);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start() {
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public RobotData getCurrentPosition() {
        return currentPosition;
    }
}

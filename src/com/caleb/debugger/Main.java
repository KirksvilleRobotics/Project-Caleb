package com.caleb.debugger;

import com.caleb.bluetooth.BluetoothServer;
import com.caleb.debugger.graphics.Window;

/**
 * Main
 *
 * This is the main class for the application. Most of the logic
 * is within run(), update(), and render() where the window loop
 * keeps the application running frame by frame
 */
public class Main {

    private static final int TICK_RATE = 60;

    private Window window;
    //private BluetoothServer bluetoothServer;

    private Thread thread;
    private boolean running;

    /**
     * Main constructor
     *
     * Initializes the window
     */
    public Main() {
        window = new Window("CALEB", 800, 600);
        //bluetoothServer = new BluetoothServer();
    }

    /**
     * Runnable method used for the thread
     * this runnable method starts the window loop, and it contains
     * most of the application's logic within it
     */
    public void run() {
        //bluetoothServer.start();
        long lastTime = System.nanoTime();
        double ns = 1000000000.0 / TICK_RATE;
        double delta = 0.0;
        final long initialTime = System.currentTimeMillis();
        long timer = System.currentTimeMillis();

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 0) {
                update();
                window.updateElapsedTime(timer - initialTime);
                delta--;
            }
            if(running) {
                render();
            }
            if(System.currentTimeMillis() - timer >= 10) {
                timer += 10;
            }
        }
        stop();
        //bluetoothServer.stop();
    }

    /**
     * Updates the window each frame
     */
    private void update() {
        window.update();
    }

    /**
     * Redraws the window each frame
     */
    private void render() {
        window.render();
    }


    /**
     * This method starts the application by setting running to true,
     * it initializes the thread, and starts it
     */
    private void start() {
        running = true;
        thread = new Thread(this::run);
        thread.start();
    }

    /**
     * This method stops the application by setting running to false,
     * and it stops the thread
     */
    private void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getTickRate() {
        return TICK_RATE;
    }

    public static void main(String[] args) {
        new Main().start();
    }
}

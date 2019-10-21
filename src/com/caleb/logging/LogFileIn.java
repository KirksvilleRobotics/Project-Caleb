package com.caleb.logging;

import java.io.*;

public class LogFileIn {

    private BufferedReader reader;
    private double currentPosX;
    private double currentPosY;
    private double currentAngle;
    private double elapsedTime;

    public LogFileIn(String fileName) {
        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] data = line.split(" ");
        currentPosX = Double.parseDouble(data[0]);
        currentPosY = Double.parseDouble(data[1]);
        currentAngle = Double.parseDouble(data[2]);
        elapsedTime = Double.parseDouble(data[3]);
    }

    public double getX() {
        return currentPosX;
    }

    public double getY() {
        return currentPosY;
    }

    public double getAngle() {
        return currentAngle;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

}

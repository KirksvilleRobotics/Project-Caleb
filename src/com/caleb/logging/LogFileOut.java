package com.caleb.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFileOut {

    private BufferedWriter writer;

    public LogFileOut() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd HH mm");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        try {
            this.writer = new BufferedWriter(new FileWriter(new File(dtf.format(now) + ".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addEntry(double x, double y, double theta, double elapsedTime) {
        String line = "" + x + ' ' + y + ' ' + theta + ' ' + elapsedTime;
        try {
            writer.write(line);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

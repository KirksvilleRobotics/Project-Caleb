package com.caleb.debugger.graphics;

import com.caleb.debugger.utils.CoordinateUtils;

import java.awt.*;

public class LocationMarker {

    private int red;
    private int green;
    private int blue;

    private int x;
    private int y;

    public LocationMarker(int x, int y) {
        this.x = x;
        this.y = y;
        red = 255;
        green = 0;
        blue = 0;
    }

    public void update() {
        if(red == 255 && green == 0 && blue != 255) {
            blue++;
        } else if(red != 255 && green == 255 && blue == 0) {
            red++;
        } else if(red == 0 && green != 255 && blue == 255) {
            green++;
        } else if(red == 255 && green > 0 && blue == 0) {
            green--;
        } else if(red > 0 && green == 0 && blue == 255) {
            red--;
        } else if(red == 0 && green == 255 && blue > 0) {
            blue--;
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(red, green, blue));
        g.fillArc(x, y, 5, 5, 0, 360);
    }

}

package com.caleb.debugger.utils;

/**
 * CoordinateUtils
 *
 * This is a static util class meant to change values between
 * pixel positions on the screen and between the position in inches
 * of the field coordinate system
 */
public class CoordinateUtils {

    private static final int MAX_POS = 144;
    private static final int MAX_PIXEL = 600;


    private CoordinateUtils(){
    }

    /**
     * This is a static utility method which converts a position
     * in pixels to a field position in inches
     *
     * @param pixelPosition screen position in pixels
     * @return position in inches for the field coordinate system [-72, 72]
     */
    public static int pixelToPos(int pixelPosition) {
        return (MAX_POS * pixelPosition) / MAX_PIXEL - (MAX_PIXEL / 2);
    }

    /**
     * This static utility method is opposite to the one above:
     * it converts a field position in inches to a screen position
     * in pixels for drawing
     *
     * @param fieldPosition position in inches for the field coordinate system [-72, 72]
     * @return screen position in pixels
     */
    public static int posToPixel(int fieldPosition) {
        fieldPosition += 72; // convert range of [-72, 72] to [0, 144]
        return (MAX_PIXEL * fieldPosition) / MAX_POS;
    }

}

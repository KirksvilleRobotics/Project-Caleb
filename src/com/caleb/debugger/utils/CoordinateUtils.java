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
     * @param negate determine whether or not to negate the field
     *               position before calculation, only negate if
     *               it is a y-value
     * @return position in inches for the field coordinate system [-72, 72]
     */
    public static double pixelToPos(int pixelPosition, boolean negate) {
        if(negate) pixelPosition = -pixelPosition;
        return (double)(MAX_POS * pixelPosition) / MAX_PIXEL - (MAX_PIXEL / 2.0);
    }

    /**
     * This static utility method is opposite to the one above:
     * it converts a field position in inches to a screen position
     * in pixels for drawing
     *
     * @param fieldPosition position in inches for the field
     *                      coordinate system [-72, 72]
     * @param negate determine whether or not to negate the field
     *               position before calculation, only negate if
     *               it is a y-value
     * @return screen position in pixels
     */
    public static int posToPixel(double fieldPosition, boolean negate) {
        if(negate) fieldPosition = -fieldPosition;
        fieldPosition += 72.0; // convert range of [-72, 72] to [0, 144]
        int roundedFieldPosition = (int) Math.round(fieldPosition);
        return (MAX_PIXEL * roundedFieldPosition) / MAX_POS;
    }

}

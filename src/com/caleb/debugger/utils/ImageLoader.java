package com.caleb.debugger.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Image Loader
 *
 * This class is a static util that is used to load images. It takes
 * in a path and returns a Buffered Image. Null is returned if no
 * image is found at the given path.
 */

public class ImageLoader {

    private ImageLoader() {}

    /**
     * Loads a BufferedImage from a given path.
     *
     * @param path relative or absolute path of an image
     * @return     BufferedImage object of the given image path
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

package edu.wpi.first.wpijavacv;


import org.bytedeco.javacpp.opencv_core;

import java.awt.*;

/**
 * A class of colors used for drawing function
 *
 * @author Greg Granito
 */
public class WPIColor {
    public static final WPIColor BLACK = new WPIColor(opencv_core.CvScalar.BLACK, Color.BLACK);
    public static final WPIColor BLUE = new WPIColor(opencv_core.CvScalar.BLUE, Color.BLUE);
    public static final WPIColor CYAN = new WPIColor(opencv_core.CvScalar.CYAN, Color.CYAN);
    public static final WPIColor GRAY = new WPIColor(opencv_core.CvScalar.GRAY, Color.GRAY);
    public static final WPIColor GREEN = new WPIColor(opencv_core.CvScalar.GREEN, Color.GREEN);
    public static final WPIColor MAGENTA = new WPIColor(opencv_core.CvScalar.MAGENTA, Color.MAGENTA);
    public static final WPIColor ONE = new WPIColor(opencv_core.CvScalar.ONE);
    public static final WPIColor ONEHALF = new WPIColor(opencv_core.CvScalar.ONEHALF);
    public static final WPIColor RED = new WPIColor(opencv_core.CvScalar.RED, Color.RED);
    public static final WPIColor WHITE = new WPIColor(opencv_core.CvScalar.WHITE, Color.WHITE);
    public static final WPIColor YELLOW = new WPIColor(opencv_core.CvScalar.YELLOW, Color.YELLOW);
    public static final WPIColor ZERO = new WPIColor(opencv_core.CvScalar.ZERO);

    private opencv_core.CvScalar scalar;
    private Color color;

    WPIColor(opencv_core.CvScalar scalar) {
        this.scalar = scalar;
    }

    WPIColor(opencv_core.CvScalar scalar, Color color) {
        this(scalar);
        this.color = color;
    }

    /**
     * Creates a new WPIColor with the specified rgb values
     *
     * @param red   red value, 0 - 255
     * @param green green value, 0 - 255
     * @param blue  blue value, 0 - 255
     */

    public WPIColor(int red, int green, int blue) {
        this(new opencv_core.CvScalar(blue, green, red, 0));
//        this(CV_RGB(red, green, blue));
    }

    public WPIColor(Color color) {
        this(color.getRed(), color.getGreen(), color.getBlue());
//        this(CV_RGB(color.getRed(), color.getGreen(), color.getBlue()), color);
    }

    public opencv_core.CvScalar toCvScalar() {
        return scalar;
    }

    public Color toColor() {
        if (color == null) {
            color = new Color((int) scalar.red(), (int) scalar.green(), (int) scalar.blue());
        }
        return color;
    }
}

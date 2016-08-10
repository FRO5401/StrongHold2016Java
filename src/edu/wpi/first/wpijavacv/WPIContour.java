package edu.wpi.first.wpijavacv;

import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.opencv_imgproc.*;

/**
 * This is a class that represents contours, it can only be obtained from
 * the findContours() method of WpiBinaryImage
 *
 * @author Greg Granito
 */
public class WPIContour extends WPIDisposable {

    opencv_core.CvSeq contours;
    opencv_core.CvRect rect;

    public WPIContour(opencv_core.CvSeq contours) {
        this.contours = contours;
//        memStorage = contours.storage();

    }

    public opencv_core.CvSeq getCVSeq() {
        return contours;
    }

    /**
     * @return the height of the bounding rectangle of the contour
     */
    public int getHeight() {
        if (rect == null || rect.isNull())
            rect = cvBoundingRect(contours, 1);

        return rect.height();
    }

    /**
     * @return the width of the bounding rectangle of the contour
     */
    public int getWidth() {
        if (rect == null || rect.isNull())
            rect = cvBoundingRect(contours, 1);

        return rect.width();
    }

    /**
     * @return the x coord of the top left corner of the bounding rectangle
     */
    public int getX() {
        if (rect == null || rect.isNull())
            rect = cvBoundingRect(contours, 1);

        return rect.x();
    }

    /**
     * @return the y coord of the top left corner of the bounding rectangle
     */
    public int getY() {
        if (rect == null || rect.isNull())
            rect = cvBoundingRect(contours, 1);

        return rect.y();
    }

    /**
     * @param percentAccuracy the percentage the perimeter of the polygon can be off
     *                        the perimeter of the contour. The higher the value, the fewer points the polygon
     *                        will have. A value of 4-5 is recommended.
     * @return the approximated WpiPolygon
     */
    public synchronized WPIPolygon approxPolygon(double percentAccuracy) {
        WPIPolygon polygon = new WPIPolygon(cvApproxPoly(contours, contours.header_size(), contours.storage(), CV_POLY_APPROX_DP, percentAccuracy, 0));
        if (getPool() != null) {
            getPool().addToPool(polygon);
        }
        return polygon;
    }

    /**
     * @return the perimeter of the contour
     */
    public int getLength() {
        return (int) cvContourPerimeter(contours);
    }

    public void disposed() {
        contours.deallocate();
    }
}

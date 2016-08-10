package edu.wpi.first.wpijavacv;


import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.opencv_core.CV_WHOLE_SEQ;
import static org.bytedeco.javacpp.opencv_core.cvCvtSeqToArray;
import static org.bytedeco.javacpp.opencv_imgproc.*;

/**
 * A class that represents a polygon, can be obtained from approxPolygon()
 * in WpiContour
 *
 * @author Greg Granito
 */
public class WPIPolygon extends WPIDisposable {

    opencv_core.CvSeq polygon;
    opencv_core.CvRect boundingRect;

    WPIPolygon(opencv_core.CvSeq data) {
        polygon = data;
    }

    opencv_core.CvSeq getCVSeq() {
        return polygon;
    }

    /**
     * @return an array of WpiPoints of the vertices of the polygon
     */
    public WPIPoint[] getPoints() {
        opencv_core.CvPoint points = new opencv_core.CvPoint(getNumVertices());
        WPIPoint[] wpiPoints = new WPIPoint[getNumVertices()];
        cvCvtSeqToArray(polygon, points.position(0), CV_WHOLE_SEQ);
        for (int j = 0; j < getNumVertices(); j++) {

            wpiPoints[j] = new WPIPoint(points.position(j).x(), points.position(j).y());

        }
        return wpiPoints;
    }

    /**
     * @return the width of the bounding rectangle of the polygon
     */
    public int getWidth() {
        if (boundingRect == null) {

            boundingRect = cvBoundingRect(polygon, 0);
        }
        return boundingRect.width();
    }

    /**
     * @return the height of the bounding rectangle of the polygon
     */
    public int getHeight() {
        if (boundingRect == null) {
            boundingRect = cvBoundingRect(polygon, 0);
        }
        return boundingRect.height();
    }

    /**
     * @return the x coord of the top left corner of the bounding
     * rectangle of the polygon
     */
    public int getX() {
        if (boundingRect == null) {
            boundingRect = cvBoundingRect(polygon, 0);
        }
        return boundingRect.x();
    }

    /**
     * @return the y coord of the top left corner of the bounding
     * rectangle of the polygon
     */
    public int getY() {
        if (boundingRect == null) {
            boundingRect = cvBoundingRect(polygon, 0);
        }
        return boundingRect.y();
    }

    /**
     * @return the number of vertices in the polygon
     */
    public int getNumVertices() {
        return polygon.total();


    }

    /**
     * @return whether or not the polygon is convex
     */
    public boolean isConvex() {
        return cvCheckContourConvexity(polygon) != 0;
    }

    /**
     * @return the area in pixels of the polygon
     */
    public int getArea() {
        return Math.abs((int) cvContourArea(polygon, CV_WHOLE_SEQ, -1));
    }

    /**
     * @return the perimeter in pixels of the polygon
     */
    public int getPerimeter() {
        return (int) cvArcLength(polygon, CV_WHOLE_SEQ, -1);
    }

    public void disposed() {
        polygon.deallocate();
    }
}

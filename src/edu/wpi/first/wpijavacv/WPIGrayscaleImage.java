package edu.wpi.first.wpijavacv;


import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;

import static org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_imgproc.*;

/**
 * A grayscale image
 *
 * @author Greg Granito
 */
public class WPIGrayscaleImage extends WPIImage {
    ToIplImage toIplImage;

    WPIGrayscaleImage(IplImage imageSrc) {
        super(imageSrc);
    }

    public WPIGrayscaleImage(WPIColorImage colorImage) {
        super(colorImage.getIplImage());
        toIplImage = new ToIplImage();
        Mat mat = toIplImage.convertToMat(toIplImage.convert(getIplImage()));
        Mat binMat = new Mat(mat);
        switch (mat.channels()) {
            case 1:
                mat.copyTo(binMat);
                break;
            case 3:
                cvtColor(mat, binMat, 6);
                break;
            case 4:
                cvtColor(mat, binMat, 6);
                break;
            default:
                throw new IllegalArgumentException("Invalid number of channels (must be 1, 3, or 4)");
        }
        this.image = toIplImage.convertToIplImage(toIplImage.convert(binMat));
    }

    /**
     * Returns a black and white image where every pixel that is higher (in the 0-255 scale) than the given threshold is <bold>white</bold>,
     * and everything below is <bold>black</bold>.
     *
     * @param threshold a value 0-255. if a pixel has a value below the theshold, it becomes black
     *                  if the pixel value is above or equal to the threshold, the pixel becomes white
     * @return a new {@link WPIBinaryImage} that represents the threshold
     */
    public WPIBinaryImage getThreshold(int threshold) {
        return getThreshold(threshold, 255);
    }

    public WPIBinaryImage getThreshold(int min, int max) {
        validateDisposed();
        IplImage bin = IplImage.create(image.cvSize(), 8, 1);
        cvThreshold(image, bin, min, max, CV_THRESH_BINARY);
        return new WPIBinaryImage(bin);
    }

    /**
     * Returns a black and white image where every pixel that is higher (in the 0-255 scale) than the given threshold is <bold>black</bold>,
     * and everything below is <bold>white</bold>.
     * <p>
     * In other words, this will return the inverted image of {@link WPIGrayscaleImage#getThreshold(int) getThreshold(...)} but is
     * more efficient than calling {@link WPIGrayscaleImage#getThreshold(int) getThreshold(...)}.{@link WPIBinaryImage#getInverse() getInverse()}
     *
     * @param threshold a value 0-255. if a pixel has a value below the theshold, it becomes black
     *                  if the pixel value is above or equal to the threshold, the pixel becomes white
     * @return a new {@link WPIBinaryImage} that represents the threshold
     */
    public WPIBinaryImage getThresholdInverted(int threshold) {
        return getThresholdInverted(threshold, 255);
    }

    public WPIBinaryImage getThresholdInverted(int min, int max) {
        validateDisposed();

        IplImage bin = IplImage.create(image.cvSize(), 8, 1);
        cvThreshold(image, bin, min, max, CV_THRESH_BINARY_INV);
        return new WPIBinaryImage(bin);
    }
}

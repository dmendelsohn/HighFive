import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.*;
import java.util.Arrays;

public class ImageProcessor{
    
    private static final Scalar lowerRed = new Scalar(98,42,53);
    
    private static final Scalar upperRed = new Scalar(186,87,105);
    
    // input: raw frame from the camera
    
    // output: frame with only the red pixels
    
    public Mat processFrame(Mat frame) {
	// Mat buffer = new Mat();
	Mat processedFrame = new Mat(frame.size(), CvType.CV_8UC3);
	// Imgproc.cvtColor(frame, buffer, Imgproc.COLOR_BGR2HSV); // convert to HSV
	//	Core.inRange(buffer, lowerRed, upperRed, processedFrame); // red filter
	for(int i = 0; i < frame.size().height; i++){
	    for(int j = 0; j < frame.size().width; j++){
		double[] pixel = frame.get(i,j);
	        if(pixel[2] > 1.7*pixel[1] && pixel[2] > 1.7*pixel[0]){
		    pixel[0] = 0;
		    pixel[1] = 0;
		    pixel[2] = 255;
		    processedFrame.put(i, j, pixel);
		}
	    }
	}
	return processedFrame;
    }
}

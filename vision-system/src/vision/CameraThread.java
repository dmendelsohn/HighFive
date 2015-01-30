package vision;

import vision.helper.PixelBuffer;
import java.io.IOException;

import org.opencv.core.*;
import org.opencv.highgui.*;

public class CameraThread extends Thread{

    boolean isRunning;
    int index;
    int count;
    Mat currentIm;

    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public CameraThread(int in){
	isRunning = false;
	index = in;
	count = 0;
	currentIm = new Mat();
	setPriority(Thread.MAX_PRIORITY);
    }
    
    public Mat getCurrentIm(){
	return currentIm;
    }

    public void run() {
	isRunning = true;

	VideoCapture camera = new VideoCapture();
	camera.open(index);

	while(isRunning){
	    long start = System.currentTimeMillis();
	    int numWaits = 0;
	    while(!camera.read(currentIm)) {
		numWaits++;
		try{
		    Thread.sleep(1);
		} catch(InterruptedException e){
		    e.printStackTrace();
		}
	    }
	    System.out.println("time for camera to provide image: " + (System.currentTimeMillis()-start) + "  numWaits: " + numWaits);
			
	    //currentIm = Utils.getPixelBufferFromMat(rawImage);
	    /*try{
		Utils.savePixelBufferToFilename("rawImage" + count + ".jpg", currentIm);
	    } catch(IOException e){
		e.printStackTrace();
		}*/
	    count++;
	}
    }

    public void delete(){
	isRunning = false;
    }
}

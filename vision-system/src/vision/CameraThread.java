package vision;

import vision.helper.PixelBuffer;
import java.io.IOException;

import org.opencv.core.*;
import org.opencv.highgui.*;

public class CameraThread extends Thread{

    boolean isRunning;
    int index;
    int count;
    PixelBuffer currentIm;

    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public CameraThread(int in){
	isRunning = false;
	index = in;
	count = 0;
	currentIm = null;
    }
    
    public PixelBuffer getCurrentIm(){
	return currentIm;
    }

    public void run() {
	isRunning = true;

	VideoCapture camera = new VideoCapture();
	camera.open(index);

	Mat rawImage = new Mat();
	
	while(isRunning){
	    System.out.println("count: " + count);
	    while(!camera.read(rawImage)) {
		try{
		    Thread.sleep(1);
		} catch(InterruptedException e){
		    e.printStackTrace();
		}
	    }
			
	    currentIm = Utils.getPixelBufferFromMat(rawImage);
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

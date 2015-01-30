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
    VideoCapture camera;

    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public CameraThread(int in){
	camera = new VideoCapture();
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


	camera.open(index);
	long last = System.currentTimeMillis();
	while(isRunning){
	    //long start = System.currentTimeMillis();
	    //int numWaits = 0;
	    camera.grab();
	    //System.out.println("grab time: " + (System.currentTimeMillis()-last));
	    //last = System.currentTimeMillis();
	    
	    /*for(int i = 0; i < 5; i++){
		camera.grab();
		System.out.println("time for grab " + i + ": " + (System.currentTimeMillis()-start));
		}*/
		/*try{
		    Thread.sleep(1);
		} catch(InterruptedException e){
		    e.printStackTrace();
		    }*/
	    //}
	    //camera.retrieve(currentIm);
	    /*while(!camera.read(currentIm)) {
		numWaits++;
		try{
		    Thread.sleep(1);
		} catch(InterruptedException e){
		    e.printStackTrace();
		}
		}*/
	    
	    //System.out.println("time for camera to provide image: " + (System.currentTimeMillis()-start) + "  numWaits: " + numWaits);
			
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

package vision;

import vision.helper.*;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import org.opencv.core.*;
import org.opencv.highgui.*;

public class Vision extends Thread{

    boolean isRunning;
    BlobInfo[] blobs;
    boolean debug;
    int index;

    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public Vision(int in){
	isRunning = false;
	blobs = new BlobInfo[0];
	debug = true;
	index = in;
    }

    public BlobInfo[] getBlobs(){
	return blobs;
    }

    public BlobInfo getClosestStack(){
	if(blobs.length == 0) return null;
	BlobInfo ret = blobs[0];
	for(BlobInfo b: blobs){
	    if(b.getBoundingRect().getYMin() < ret.getBoundingRect().getYMin()){
		ret = b;
	    }
	}
	return ret;
    }
    
    public void run() {
	isRunning = true;

	long start;
	long end;
	VideoCapture camera = new VideoCapture();
	camera.open(index);

	Mat rawImage = new Mat();
	
	while(isRunning){
	    long total_start = System.currentTimeMillis();
	    start = System.currentTimeMillis();
	    while(!camera.read(rawImage)) {
		try{
		    Thread.sleep(1);
		} catch(InterruptedException e){
		    e.printStackTrace();
		}
	    }
			
	    PixelBuffer pixelBuffer = Utils.getPixelBufferFromMat(rawImage);
	    int height = pixelBuffer.getHeight();
	    int width = pixelBuffer.getWidth();
	    end = System.currentTimeMillis();
	    if (debug) System.out.println("[Timing] getPixelBufferFromFilename() milli bench: " + (end-start));

	    // Filter into GameColors
	    start = System.currentTimeMillis();
	    byte[] game_color_data = FilterImage.filter(pixelBuffer);
	    end = System.currentTimeMillis();
	    if (debug) System.out.println("[Timing] filter milli bench: " + (end-start));

	    //Labeling
	    start = System.currentTimeMillis();
	    BlobLabelData blobLabelData = BlobLabeling.labelBlobs(game_color_data, height, width);
	    Map<Integer,Byte> labelToColor = blobLabelData.getLabelToColorMap();
	    int[] labels = blobLabelData.getLabelData();
	    end = System.currentTimeMillis();
	    if (debug) System.out.println("[Timing] Blob labeling logic milli bench: " + (end-start));

	    // Remove blobs that are noise (too small to be balls or walls)
	    start = System.currentTimeMillis();
	    int numBlobsRemoved = BlobAnalysis.removeSmallBlobs(blobLabelData);
	    end = System.currentTimeMillis();
	    if (debug) System.out.println("[Timing] Small blob removal milli bench: " + (end-start));
	    System.out.println("Removed " + numBlobsRemoved + " blobs.");
			
	    // Calculate bounding boxes for all blobs
	    start = System.currentTimeMillis();
	    blobs = BlobAnalysis.getAllBlobInfo(blobLabelData);
	    end = System.currentTimeMillis();
	    if (debug) System.out.println("[Timing] getAllBlobInfo() milli bench: " + (end-start));
		
	    long total_end = System.currentTimeMillis();
	    if (debug) System.out.println("[Timing] Total Main milli bench: " + (total_end-total_start));
	}
    }
}

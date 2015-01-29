package vision;

import vision.helper.*;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class VisionThread extends Thread{

    boolean isRunning;
    BlobInfo[] blobs;
    boolean debug;
    int index;
    int height;
    int width;
    int count;

    public VisionThread(int in){
	isRunning = false;
	blobs = new BlobInfo[0];
	height = 0;
	width = 0;
	debug = true;
	count = 0;
	index = in;
    }

    private boolean isBlock(BlobInfo b) {
	return (b.getGameColor() == Utils.GameColor.RED || b.getGameColor() == Utils.GameColor.GREEN);
    }

    public boolean isBlockSeen() {
	for (BlobInfo b : blobs) {
	    if (isBlock(b)) {
		return true;
	    }
	}
	return false; //No red or green blobs seen
    }

    // Returns null if there are no blocks seens
    public BlobInfo getNearestBlock() {
	BlobInfo nearestBlock = null;
	for (BlobInfo b : blobs) {
	    if (isBlock(b)) { //It's actually a block, not a wall
		if (nearestBlock == null) {
		    nearestBlock = b; 	// We have found the first actual block in the list
		} else if (b.getBoundingRect().getYMax() > nearestBlock.getBoundingRect().getYMax()) {
		    nearestBlock = b;	// This block is closer than the previously analyzed one
		}
	    }
	}
	return nearestBlock;
    }

    public double getDistanceToBlock(BlobInfo blob) {
	//Just uses y coordinate
	double scaledY = ((double)height - blob.getBoundingRect().getYMax())/height; //Use y min cause we care about the bottom of the block or stack
	return scaledY; //Ranges from 0 on bottom to 1 on top
    }

    public double getHeadingToBlock(BlobInfo blob) {
	BoundingRect rect = blob.getBoundingRect();
	int blobMidX = (rect.getXMin() + rect.getXMax()) / 2;
	int imageMidX = width/2;
	int blobXFromCenter = blobMidX - imageMidX;
	double scaledX = (double)blobXFromCenter/imageMidX; //This will range from -1 on the left to 1 on the right
	return scaledX;
    }

    public boolean isSolidColorStackSeen() {
	//TODO: implement if we need it
	return false;
    }

    public boolean isMixedColorStackSeen() {
	//TODO: implement if we need it
	return false;
    }	

    public double getDistanceToNearestBlock() {
	BlobInfo b = getNearestBlock();
	if(b == null){
	    return -1;
	}
	return getDistanceToBlock(b);
    }

    public double getHeadingToNearestBlock() {
	BlobInfo b = getNearestBlock();
	if(b == null){
	    return 0;
	}
	return getHeadingToBlock(b);
    }

    public BlobInfo[] getBlobs(){
	return blobs;
    }

    /*public BlobInfo getClosestStack(){
      BlobInfo ret = null;
      for(BlobInfo b: blobs){
      if(isBlock(b) && b.getBoundingRect().getYMin() < ret.getBoundingRect().getYMin()){
      ret = b;
      }
      }
      return ret;
      }*/
    
    public void run() {
	isRunning = true;

	long start;
	long end;

	CameraThread cam = new CameraThread(index);
	cam.start();
	while(cam.getCurrentIm()==null){
	    System.out.println("cam still initializing;");
	    try{
		Thread.sleep(10);
	    }catch(InterruptedException e){
		e.printStackTrace();
	    }
	}
	
	while(isRunning){
	    long total_start = System.currentTimeMillis();
	    start = System.currentTimeMillis();

	    PixelBuffer pixelBuffer = cam.getCurrentIm();
	    height = pixelBuffer.getHeight();
	    width = pixelBuffer.getWidth();
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
	    
	    /*
	    //Save Nice image
	    byte[] img_data = Utils.makeImageArrayFromLabelData(blobLabelData);
	    PixelBuffer pixelBufferOut = new PixelBuffer(img_data, height, width);
	    try{
	    Utils.savePixelBufferToFilename("processedImage" + count + ".jpg", pixelBufferOut);
	    } catch(IOException e){
	    e.printStackTrace();
	    }*/

	    count++;
			
	    long total_end = System.currentTimeMillis();
	    if (debug) System.out.println("[Timing] Total Main milli bench: " + (total_end-total_start));
	}
    }
}

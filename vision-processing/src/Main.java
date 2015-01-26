import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import org.opencv.core.*;
import org.opencv.highgui.*;

public class Main {

        static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

	public static void main(String[] args) {
		long total_start = System.currentTimeMillis();

		//Get some arguments
		if (args.length < 4) {
			System.out.println("Not enough arguments");
			System.exit(0);
		}
		String inputImageFilename = args[0];
		String outputInfoFilename = args[1];
		String outputImageFilename = args[2];
		boolean vis = Boolean.parseBoolean(args[3]);
		boolean debug = Boolean.parseBoolean(args[4]);

		long start;
		long end;
		try {
			start = System.currentTimeMillis();
			VideoCapture camera = new VideoCapture();
			camera.open(1);

			Mat rawImage = new Mat();
			
			while(!camera.read(rawImage)) {
			    try{
				Thread.sleep(1);
			    } catch(InterruptedException e){
				e.printStackTrace();
			    }
			}
			
			PixelBuffer pixelBuffer = Utils.getPixelBufferFromMat(rawImage);
			Utils.savePixelBufferToFilename("vision/rawImage.jpg", pixelBuffer);
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
			BlobInfo[] blobs = BlobAnalysis.getAllBlobInfo(blobLabelData);
			end = System.currentTimeMillis();
			if (debug) System.out.println("[Timing] getAllBlobInfo() milli bench: " + (end-start));

			// Save blob info to a txt file
			String output = Utils.getAnalysisOutput(height, width, blobs);
			Utils.saveStringToFilename(outputInfoFilename, output);
	

			if (vis) {
				//Augment image with bounding rects and output  augmented image to a file
				start = System.currentTimeMillis();
				byte[] img_data = Utils.makeImageArrayFromLabelData(blobLabelData);
				BlobAnalysis.drawAllBoundingRects(img_data, height, width, blobs);
				PixelBuffer pixelBufferOut = new PixelBuffer(img_data, height, width);
				end = System.currentTimeMillis();
				if (debug) System.out.println("[Timing] Drawing rectangles milli bench: " + (end-start));
			
				start = System.currentTimeMillis();
				Utils.savePixelBufferToFilename(outputImageFilename, pixelBufferOut);
				end = System.currentTimeMillis();
				if (debug) System.out.println("[Timing] savePixelBufferToFilename() milli bench: " + (end-start));
			}	
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		long total_end = System.currentTimeMillis();
		if (debug) System.out.println("[Timing] Total Main milli bench: " + (total_end-total_start));
	}
}

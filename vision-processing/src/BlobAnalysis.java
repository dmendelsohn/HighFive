import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class BlobAnalysis {

	//Returns number of blobs removed
	public static int removeSmallBlobs(BlobLabelData blobLabelData) {
		int numBlobsRemoved = 0;
		int[] labels = blobLabelData.getLabelData();
		Map<Integer, Integer> blobSizeMap = new HashMap<Integer, Integer>(); //Maps blob labels to the number of pixels in the blob
		for (int i = 0; i < labels.length; i++) {
			Integer count = blobSizeMap.get(labels[i]);
			if (count != null) {
				blobSizeMap.put(labels[i], count+1);
			} else {
				blobSizeMap.put(labels[i], 1);
			}
		}

		for (int i = 0; i < labels.length; i++) {
			if (blobSizeMap.get(labels[i]) < Utils.BLOB_SIZE_THRESHHOLD) { //If this pixel belongs to a dwarf blob
				labels[i] = 0;		// Remove the blob (use the background label)
			}
		}

		Map<Integer, Byte> labelToColor = blobLabelData.getLabelToColorMap();
		for (Map.Entry<Integer, Integer> entry : blobSizeMap.entrySet()) {
			if (entry.getValue() < Utils.BLOB_SIZE_THRESHHOLD) { //If this is a dwarf blob
				labelToColor.remove(entry.getKey());		// Remove this blob's label from the label to color map
				numBlobsRemoved++;
			}
		}
		return numBlobsRemoved;
	}

	public static BlobInfo[] getAllBlobInfo(BlobLabelData blobLabelData) {
		Map<Integer, BlobInfo> labelToInfo = new HashMap<Integer, BlobInfo>();
		int height = blobLabelData.getHeight();
		int width = blobLabelData.getWidth();
		int labels[] = blobLabelData.getLabelData();
		Map<Integer, Byte> labelToColor = blobLabelData.getLabelToColorMap();
		int curLabel;
		BlobInfo curBlobInfo;
		int offset = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				curLabel = labels[offset];
				if (curLabel != 0) { //Ignore background blob
					curBlobInfo = labelToInfo.get(curLabel);
					if (curBlobInfo != null) {
						curBlobInfo.addPoint(j, i);		// Add new point so the blob can (if needed) expand it's bounding rectangle
					} else { //Need to make a new blob and put it into the label to blob map
						curBlobInfo = new BlobInfo(curLabel, labelToColor.get(curLabel));
						labelToInfo.put(curLabel, curBlobInfo);
					}
				}
				offset++;
			}
		}
		//At this point, we just need to move our values from the labelToInfo map into an array
		int numBlobs = labelToInfo.size();
		BlobInfo[] allBlobInfo = new BlobInfo[numBlobs];
		int i = 0;
		for (Map.Entry<Integer,BlobInfo> entry : labelToInfo.entrySet()) {
			allBlobInfo[i] = entry.getValue();
			i++;
		}
		return allBlobInfo;
	}

	public static void drawAllBoundingRects(byte[] data, int height, int width, BlobInfo[] blobs) {
		for (BlobInfo blob : blobs) {
			drawBoundingRect(data, height, width, blob);
		}
	}

	public static void drawBoundingRect(byte[] data, int height, int width, BlobInfo blob) {
		byte[] rgb = Utils.getByteArrayFromGameColor(blob.getGameColor());
		BoundingRect rect = blob.getBoundingRect();
		drawVerticalLine(data, height, width, rgb, rect.getXMin(), rect.getYMin(), rect.getYMax());
		drawVerticalLine(data, height, width, rgb, rect.getXMax(), rect.getYMin(), rect.getYMax());
		drawHorizontalLine(data, height, width, rgb, rect.getXMin(), rect.getXMax(), rect.getYMin());
		drawHorizontalLine(data, height, width, rgb, rect.getXMin(), rect.getXMax(), rect.getYMax());
	}

	public static void drawVerticalLine(byte[] data, int height, int width, byte[] rgb, int x, int yMin, int yMax) {
		int offset = 3*(yMin*width + x);
		for (int y = yMin; y <= yMax; y++) {
			for (int j = 0; j < 3; j++) {
				data[offset+j] = rgb[j];
			}
			offset += 3*width;
		}
	}

	public static void drawHorizontalLine(byte[] data, int height, int width, byte[] rgb, int xMin, int xMax, int y) {
		int offset = 3*(y*width + xMin);
		for (int x = xMin; x <= xMax; x++) {
			for (int j = 0; j < 3; j++) {
				data[offset+j] = rgb[j];
			}
			offset+=3;
		}
	}

	public static void main(String[] args) {
		if (args.length < 5) {
			System.out.println("Need more args!");
			System.exit(0);
		}

		String inputLabelFilename = args[0];
		String inputInfoFilename = args[1];
		String outputLabelFilename = args[2];
		String outputImageFilename = args[3];
		String outputInfoFilename = args[4];
		int height = 0;
		int width = 0;
	
		try {
			int[] data = Utils.getIntegerArrayFromFilename(inputLabelFilename);
			String info = Utils.getStringFromFilename(inputInfoFilename);
			String[] lines = info.split("\\r?\\n");
			height = Integer.parseInt(lines[0]);
			width = Integer.parseInt(lines[1]);
			Map<Integer, Byte> labelToColor = new HashMap<Integer, Byte>();
			for (int i = 2; i < lines.length; i++) {
				String line = lines[i];
				String[] lineParts = line.split(" ");
				int label = Integer.parseInt(lineParts[0]);
				byte gameColorByte = (byte)Integer.parseInt(lineParts[1]);
				labelToColor.put(label, gameColorByte);
			}
			System.out.println("Now analyzing image with dimensions " + height +"x"+ width +", and size " + data.length);

			int[] labels = Utils.getIntegerArrayFromFilename(inputLabelFilename);
			BlobLabelData blobLabelData = new BlobLabelData(labels, labelToColor, height, width);
			
			// Remove blobs that are noise (too small to be balls or walls)
			int numBlobsRemoved = removeSmallBlobs(blobLabelData);
			System.out.println("Removed " + numBlobsRemoved + " blobs.");
			
			// Add bounding boxes to all blobs
			BlobInfo[] blobs = getAllBlobInfo(blobLabelData);

			//Output raw label data from within blobLabelData object
			Utils.saveIntegerArrayToFilename(outputLabelFilename, blobLabelData.getLabelData());

			//Output dimensions and blob specs to txt file	
			String output = Utils.getAnalysisOutput(height, width, blobs);
			Utils.saveStringToFilename(outputInfoFilename, output);
			
			//Augment image with bounding rects and output  augmented image to a file
			byte[] img_data = Utils.makeImageArrayFromLabelData(blobLabelData);
			drawAllBoundingRects(img_data, height, width, blobs);
			PixelBuffer pixelBuffer = new PixelBuffer(img_data, height, width);
			Utils.savePixelBufferToFilename(outputImageFilename, pixelBuffer);

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Badly formatted info file from Labeler");
		}
	}
}

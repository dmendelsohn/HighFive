import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class BlobLabeling {

	//Returns byte array of size 8
	//Bytes 2*n, 2*n+1 are gameColor and label for a neighbor.  Out of bounds is 0 for both.
	//0,1 are for West neighbor; 2,3 for Northwest; 4,5 for North; 6,7 for Northeast
	private static LabeledNeighbor[] getNeighbors(byte[] data, int[] labels, int height, int width, int height_coord, int width_coord, int offset) {
		//Note height and width fields are useful for determining borderline cases, offset is to avoid doing multiplication a zillion times
		LabeledNeighbor[] result = new LabeledNeighbor[4];
		if (width_coord > 0) { //West neighbor is in bounds
			result[0] = new LabeledNeighbor(data[offset-1], labels[offset-1]);	
		} else {
			result[0] = new LabeledNeighbor((byte)0,0);
		}
		if (width_coord > 0 && height_coord > 0) { //Northwest neighbor is in bounds
			result[1] = new LabeledNeighbor(data[offset-width-1], labels[offset-width-1]);
		} else {
			result[1] = new LabeledNeighbor((byte)0,0);
		}
		if (height_coord > 0) { // North neighbor is in bounds
			result[2] = new LabeledNeighbor(data[offset-width], labels[offset-width]);
		} else {
			result[2] = new LabeledNeighbor((byte)0,0);
		}
		if (height_coord > 0 && width_coord < width-1) { // Northeast neighbor is in bounds
			result[3] = new LabeledNeighbor(data[offset-width+1], labels[offset-width+1]);
		} else {
			result[3] = new LabeledNeighbor((byte)0,0);
		}
		return result;
	}

	private static int getMinMatchingLabel(LabeledNeighbor[] neighbors, byte currentColorValue) {
		int minMatchingLabel = Integer.MAX_VALUE;
		for (LabeledNeighbor neighbor : neighbors) {
			int label = neighbor.getLabel();
			if (label > 0 && label < minMatchingLabel) {
				minMatchingLabel = label;
			}
		}
		if (minMatchingLabel != Integer.MAX_VALUE) {
			return minMatchingLabel;
		} else {
			return 0;
		}
	}

	//This alters the data in place, replacing GameColor values with labels
	//Returns map from label numbers to GameColor values
	public static BlobLabelData labelBlobs(byte[] data, int height, int width) {
		Map<Integer, Byte> labelToColor = new HashMap<Integer,Byte>();
		LabelTracker labelTracker = new LabelTrackerImpl();
		int curlabel = 1;
		int offset;
		int[] labels = new int[data.length];

		//First pass
		offset = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (data[offset] == Utils.getByteFromGameColor(Utils.GameColor.NONE)) { //If it's a background pixel, use background label (0)
					labels[offset] = 0;
				} else {
					LabeledNeighbor[] neighbors = getNeighbors(data, labels, height, width, i, j, offset); //Get 8 byte array, with 2 bytes per "higher" neighbor
					byte currentColorValue = data[offset];
					int minMatchingLabel = getMinMatchingLabel(neighbors, currentColorValue);	//Find min label of color-matched pixel (0 if no color matches)
					if (minMatchingLabel == 0) { //No neighbors of same color, make new label
						labels[offset] = curlabel;  // Current label is assigned
						labelTracker.addNewLabel(curlabel, currentColorValue);	// Tell label tracker that there's a new label whose color value is currentColorValue
						curlabel++;				// Next new label will be higher by 1
					} else {
						labels[offset] = minMatchingLabel;  // Assign current pixel's label
						for (int k = 0; k < 4; k++) {
							if (neighbors[k].getColor() == currentColorValue && neighbors[k].getLabel() != minMatchingLabel) { // Need to record equivalence
								labelTracker.recordEquivalence(minMatchingLabel, neighbors[k].getLabel()); // LabelTracker object handles it for us
							}
						}
					}
				}
				offset++;
			}
		}

		//Second pass, reduce to minimal label values, and record label to color mappings that are in use
		offset = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (labels[offset] > 0) { //Don't bother with background pixels
					int minimizedLabel = labelTracker.findMinEquivalence(labels[offset]); // Find minimum equivalent label
					byte colorOfMinimizedLabel = labelTracker.valueOf(minimizedLabel); // What GameColor value does this labeled blob have?
					labels[offset] = minimizedLabel; //Replace label with minimum equivalent label
					labelToColor.put(minimizedLabel, colorOfMinimizedLabel);
				}
				offset++;
			}
		}
		labelToColor.put(0,(byte)0);		// Note association of 0 label to 0 color

		return new BlobLabelData(labels, labelToColor, height, width);
	}
	
	public static void main(String[] args) {
		long total_start = System.currentTimeMillis();

		if (args.length < 4) {
			System.out.println("Not enough arguments for blob labeling program");
			System.exit(0);
		}
		String inputRawFilename = args[0];
		String inputInfoFilename = args[1];
		String outputRawFilename = args[2];
		String outputInfoFilename = args[3];
		int height = 0;
		int width = 0;
	
		long start;
		long end;
		try {

			start = System.currentTimeMillis();
			byte[] data = Utils.getByteArrayFromFilename(inputRawFilename);
			String info = Utils.getStringFromFilename(inputInfoFilename);
			String[] lines = info.split("\\r?\\n");
			height = Integer.parseInt(lines[0]);
			width = Integer.parseInt(lines[1]);
			end = System.currentTimeMillis();
			System.out.println("Retrieving information from files, milli bench: " + (end-start));

			System.out.println("Now labeling image with dimensions " + height +"x"+ width +", and size " + data.length);

			start = System.currentTimeMillis();
			BlobLabelData blobLabelData = labelBlobs(data, height, width);
			Map<Integer,Byte> labelToColor = blobLabelData.getLabelToColorMap();
			int[] labels = blobLabelData.getLabelData();
			end = System.currentTimeMillis();
			System.out.println("Blob labeling logic milli bench: " + (end-start));

			start = System.currentTimeMillis();
			Utils.saveIntegerArrayToFilename(outputRawFilename, labels);
			String labelInfo = Utils.getLabelOutput(height, width, labelToColor);
			Utils.saveStringToFilename(outputInfoFilename, labelInfo);
			end = System.currentTimeMillis();
			System.out.println("Saving information into files, milli bench: " + (end-start));
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Badly formatted info file from Filter");
		}
		long total_end = System.currentTimeMillis();
		System.out.println("Total BlobLabeling milli bench: " + (total_end-total_start));
	}
}

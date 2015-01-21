import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class FilterImage {

	public static final int CHANNELS = 3;
	public static boolean isRedPixel(byte[] data, int offset) {
		double thresh = Utils.RED_THRESHHOLD;
		short r = Utils.toUnsigned(data[offset]);
		short g = Utils.toUnsigned(data[offset+1]);
		short b = Utils.toUnsigned(data[offset+2]);
		return (r > thresh * g && r > thresh * b);
	}

	public static boolean isGreenPixel(byte[] data, int offset) {
		double thresh = Utils.GREEN_THRESHHOLD;
		short r = Utils.toUnsigned(data[offset]);
		short g = Utils.toUnsigned(data[offset+1]);
		short b = Utils.toUnsigned(data[offset+2]);
		return (g > thresh * r && g > thresh * b);
	}

	public static boolean isBluePixel(byte[] data, int offset) {
		double thresh = Utils.BLUE_THRESHHOLD;
		short r = Utils.toUnsigned(data[offset]);
		short g = Utils.toUnsigned(data[offset+1]);
		short b = Utils.toUnsigned(data[offset+2]);
		return (b > thresh * g && b > thresh * r);
	}

	public static void makePixel(byte[] data, int offset, Utils.GameColor color) {
		byte[] rgb = Utils.getByteArrayFromGameColor(color);
		data[offset] = rgb[0];
		data[offset+1] = rgb[1];
		data[offset+2] = rgb[2];
	}

	//This function filters and alters the input mat, changing filtered cells to a GameColor value
	//If makePrettyCopy is true, a copy of the mat where the values are "pretty" represenations of those GameColors is returned
	//If makePrettyCopy is false, nothing is returned.  Either way, the working data is altered in place.
	public static byte[] filter(Mat mat) {
		int height = mat.rows();
		int width = mat.cols();
		byte[] data = new byte[mat.rows()*mat.cols()*CHANNELS];
		mat.get(0,0,data);
		System.out.println("Image dimensions: " + height + " by " + width);

		byte[] raw_data = new byte[mat.rows()*mat.cols()];
		
		int row_index;
		int index;
		Utils.GameColor color;

		for(int i = 0; i < height; i++) {
			row_index = i*width;
			for(int j = 0; j < width; j++) {
				// data is chilling at data[offset] for R, next two bytes for G and B
				index = row_index + j;
				if (isRedPixel(data, CHANNELS*index)) {
					color = Utils.GameColor.RED;
				} else if (isGreenPixel(data, CHANNELS*index)) {
					color = Utils.GameColor.GREEN;
				} else if (isBluePixel(data, CHANNELS*index)) {
					color = Utils.GameColor.BLUE;
				} else {
					color = Utils.GameColor.NONE;
				}
				makePixel(data, CHANNELS*index, color);
				raw_data[index] = Utils.getByteFromGameColor(color); //Raw data array just consists of an int corresponding to the GameColor enum value
			}
		}

		mat.put(0,0,data);  //but edited data back into the mat object
		return raw_data;
	}

	public static void main( String[] args ) {
		//Get some arguments
		if (args.length < 4) {
			System.out.println("Not enough arguments");
			System.exit(0);
		}
		String inputFilename = args[0];
		String outImgFilename = args[1];
		String outRawFilename = args[2];
		String outInfoFilename = args[3];

		try {
			System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
			Mat mat = Utils.getMatFromFilename(inputFilename);
		
			// Process mat	
			byte[] raw_data = filter(mat);

			Utils.saveMatToFilename(outImgFilename, mat);
			Utils.saveByteArrayToFilename(outRawFilename, raw_data);
			String infoOut = Utils.getFilterOutput(mat.rows(), mat.cols());
			Utils.saveStringToFilename(outInfoFilename, infoOut);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}

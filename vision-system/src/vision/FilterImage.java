package vision;

import vision.helper.*;

import java.io.IOException;

public class FilterImage {

	public static final int CHANNELS = 3;
	public static boolean isRedPixel(byte[] data, int offset) {
		double thresh = Utils.RED_THRESHHOLD;
		short b = Utils.toUnsigned(data[offset]);
		short g = Utils.toUnsigned(data[offset+1]);
		short r = Utils.toUnsigned(data[offset+2]);
		return (r > thresh * g && r > thresh * b);
	}

	public static boolean isGreenPixel(byte[] data, int offset) {
		double thresh = Utils.GREEN_THRESHHOLD;
		short b = Utils.toUnsigned(data[offset]);
		short g = Utils.toUnsigned(data[offset+1]);
		short r = Utils.toUnsigned(data[offset+2]);
		return (g > thresh * r && g > thresh * b);
	}

	public static boolean isBluePixel(byte[] data, int offset) {
		double thresh = Utils.BLUE_THRESHHOLD;
		short b = Utils.toUnsigned(data[offset]);
		short g = Utils.toUnsigned(data[offset+1]);
		short r = Utils.toUnsigned(data[offset+2]);
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
	public static byte[] filter(PixelBuffer pixelBuffer) {
		int height = pixelBuffer.getHeight();
		int width = pixelBuffer.getWidth();
		byte[] data = pixelBuffer.getData(); //BGR format

		byte[] raw_data = new byte[width*height];
		
		int index=0;
		int offset=0;
		Utils.GameColor color;

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				// data is chilling at data[offset] for R, next two bytes for G and B
				if (isRedPixel(data, offset)) {
					color = Utils.GameColor.RED;
				} else if (isGreenPixel(data, offset)) {
					color = Utils.GameColor.GREEN;
				} else if (isBluePixel(data, offset)) {
					color = Utils.GameColor.BLUE;
				} else {
					color = Utils.GameColor.NONE;
				}
				makePixel(data, offset, color);
				raw_data[index] = Utils.getByteFromGameColor(color); //Raw data array just consists of an int corresponding to the GameColor enum value
				index++;
				offset+=3;
			}
		}
		return raw_data;
	}

	public static void main( String[] args ) {
		long total_start = System.currentTimeMillis();

		//Get some arguments
		if (args.length < 4) {
			System.out.println("Not enough arguments");
			System.exit(0);
		}
		String inputFilename = args[0];
		String outImgFilename = args[1];
		String outRawFilename = args[2];
		String outInfoFilename = args[3];

		long start;
		long end;
		try {
			start = System.currentTimeMillis();
			PixelBuffer pixelBuffer = Utils.getPixelBufferFromFilename(inputFilename);
			end = System.currentTimeMillis();

			// Filter into GameColors
			start = System.currentTimeMillis();
			byte[] raw_data = filter(pixelBuffer);
			end = System.currentTimeMillis();

			start = System.currentTimeMillis();
			Utils.savePixelBufferToFilename(outImgFilename, pixelBuffer);
			end = System.currentTimeMillis();
	
			start = System.currentTimeMillis();
			Utils.saveByteArrayToFilename(outRawFilename, raw_data);
			end = System.currentTimeMillis();

			start = System.currentTimeMillis();
			String infoOut = Utils.getFilterOutput(pixelBuffer.getHeight(), pixelBuffer.getWidth());
			Utils.saveStringToFilename(outInfoFilename, infoOut);
			end = System.currentTimeMillis();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		long total_end = System.currentTimeMillis();
		System.out.println("Total FilterImage milli bench: " + (total_end-total_start));
	}
}

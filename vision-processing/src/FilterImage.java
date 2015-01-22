import java.io.IOException;

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
	public static byte[] filter(PixelBuffer pixelBuffer) {
		int height = pixelBuffer.getHeight();
		int width = pixelBuffer.getWidth();
		byte[] data = pixelBuffer.getData(); //BGR format
		System.out.println("Image dimensions: " + height + " by " + width);

		byte[] raw_data = new byte[width*height];
		
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
			PixelBuffer pixelBuffer = Utils.getPixelBufferFromFilename(inputFilename);
			
			// Filter into GameColors
			byte[] raw_data = filter(pixelBuffer);

			Utils.savePixelBufferToFilename(outImgFilename, pixelBuffer);
			Utils.saveByteArrayToFilename(outRawFilename, raw_data);
			String infoOut = Utils.getFilterOutput(pixelBuffer.getHeight(), pixelBuffer.getWidth());
			Utils.saveStringToFilename(outInfoFilename, infoOut);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import java.io.File;
import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class ColorFilter {
	public static final String INPUT_FILENAME = "../pics/Two.jpg";
	public static final String OUTPUT_FILENAME = "../pics/TwoFilter.jpg";

	public static final double RED_THRESHHOLD = 1.4;
	public static final double GREEN_THRESHHOLD = 1.22;
	public static final double BLUE_THRESHHOLD = 1.3;
	
	public enum Color { RED, GREEN, BLUE, BLACK}

	public static short toUnsigned(byte b) {
		short s = (short)b;
		if (s >= 0)
			return s;
		else
			return (short)(s+256);
	}

	// Behavior is undefined if input is out of range 0-255
	public static byte fromUnsigned(short s) {
		if (s < 128) {
			return (byte)s;
		} else {
			return (byte)(s-256);
		}
	}

	public static byte[] getByteArrayFromColor(Color color) {
		byte[] rgb = new byte[3];
		rgb[0] = 0;
		rgb[1] = 0;
		rgb[2] = 0;
		switch(color) {
			case RED:
				rgb[0] = -1;
				break;
			case GREEN:
				rgb[1] = -1;
				break;
			case BLUE:
				rgb[2] = -1;
				break;
			case BLACK:
				break;
			default:
				break;
		}
		return rgb;
	}

	public static boolean isRedPixel(byte[] data, int offset) {
		double thresh = RED_THRESHHOLD;
		short r = toUnsigned(data[offset]);
		short g = toUnsigned(data[offset+1]);
		short b = toUnsigned(data[offset+2]);
		return (r > thresh * g && r > thresh * b);
	}

	public static boolean isGreenPixel(byte[] data, int offset) {
		double thresh = GREEN_THRESHHOLD;
		short r = toUnsigned(data[offset]);
		short g = toUnsigned(data[offset+1]);
		short b = toUnsigned(data[offset+2]);
		return (g > thresh * r && g > thresh * b);
	}

	public static boolean isBluePixel(byte[] data, int offset) {
		double thresh = BLUE_THRESHHOLD;
		short r = toUnsigned(data[offset]);
		short g = toUnsigned(data[offset+1]);
		short b = toUnsigned(data[offset+2]);
		return (b > thresh * g && b > thresh * r);
	}

	public static void makePixel(byte[] data, int offset, Color color) {
		byte[] rgb = getByteArrayFromColor(color);
		data[offset] = rgb[0];
		data[offset+1] = rgb[1];
		data[offset+2] = rgb[2];
	}

	// This function find mostly red pixels and changes them to solid,bright red.  Other pixels are made black.
	public static void filter(Mat mat) {
		int height = mat.rows();
		int width = mat.cols();
		byte[] data = new byte[mat.rows()*mat.cols()*mat.channels()];
		mat.get(0,0,data);
		System.out.println("Image dimensions: " + height + " by " + width);
		
		int row_offset;
		int offset;
		Color color;
		for(int i = 0; i < height; i++) {
			row_offset = i*width*3;
			for(int j = 0; j < width; j++) {
				// data is chilling at data[offset] for R, next two bytes for G and B
				offset = row_offset+3*j;
				if (isRedPixel(data, offset)) {
					color = Color.RED;
				} else if (isGreenPixel(data, offset)) {
					color = Color.GREEN;
				} else if (isBluePixel(data, offset)) {
					color = Color.BLUE;
				} else {
					color = Color.BLACK;
				}
				makePixel(data, offset, color);
			}
		}

		mat.put(0,0,data);
		mat.convertTo(mat, CvType.CV_8U);
	}

	public static void main( String[] args ) { 
		try {
			System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

			//Read buffered image from file
			File input = new File(INPUT_FILENAME);
			BufferedImage image = ImageIO.read(input);	

			//Dump BufferedImage into mat
			byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
			mat.put(0, 0, data);
			Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGR2RGB);
		
			// Process mat	
			filter(mat);

			// Dump mat1 into BufferedImage
			byte[] data1 = new byte[mat.rows() * mat.cols() * (int)(mat.elemSize())];
			mat.get(0, 0, data1);
			BufferedImage image1 = new BufferedImage(mat.cols(),mat.rows(), BufferedImage.TYPE_3BYTE_BGR);
			image1.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data1);

			// Write BufferedImage to file
			File output = new File(OUTPUT_FILENAME);
			ImageIO.write(image1, "jpg", output);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}

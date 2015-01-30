package vision;

import vision.helper.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.Mat;
import org.opencv.core.Core;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Map;

import javax.imageio.ImageIO;

public class Utils {

    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

	public final static double RED_THRESHHOLD = 1.5;
	public final static double GREEN_THRESHHOLD = 1.1;
	public final static double BLUE_THRESHHOLD = 1.3;


	public final static int BLOB_SIZE_THRESHHOLD = 1000; //Ignore blobs smaller than 200 pixels

	public enum GameColor { NONE, RED, GREEN, BLUE }

	public static byte getByteFromGameColor(GameColor color) {
		switch(color) {
			case NONE:
				return 0;
			case RED:
				return 1;
			case GREEN:
				return 2;
			case BLUE:
				return 3;
			default:
				return 0;
		}
	}

	public static GameColor getGameColorFromByte(byte b) {
		switch(b) {
			case 0:
				return GameColor.NONE;
			case 1:
				return GameColor.RED;
			case 2:
				return GameColor.GREEN;
			case 3:
				return GameColor.BLUE;
			default:
				return GameColor.NONE;
		}
	}

	public static byte[] getByteArrayFromGameColor(GameColor color) {
		byte[] rgb = new byte[3];
		rgb[0] = 0;
		rgb[1] = 0;
		rgb[2] = 0;
		switch(color) {
			case RED:
				rgb[2] = -1;
				break;
			case GREEN:
				rgb[1] = -1;
				break;
			case BLUE:
				rgb[0] = -1;
				break;
			case NONE:
				break;
			default:
				break;
		}
		return rgb;
	}

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

	public static String getFilterOutput(int height, int width) {
		return ("" + height + "\n" + width + "\n");
	}

	public static String getLabelOutput(int height, int width, Map<Integer,Byte> labelToColor) {
		StringBuilder sb = new StringBuilder();
		sb.append(height).append("\n").append(width).append("\n");
		for (Map.Entry<Integer,Byte> entry : labelToColor.entrySet()) {
			sb.append(entry.getKey());
			sb.append(" ");
			sb.append(entry.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}

	public static String getAnalysisOutput(int height, int width, BlobInfo[] blobs) {
		StringBuilder sb = new StringBuilder();
		sb.append(height).append("\n").append(width).append("\n");
		for (BlobInfo blob : blobs) {
			sb.append(blob.toString()).append("\n");
		}
		return sb.toString();
	}

        public static PixelBuffer getPixelBufferFromMat(Mat image){
	        Mat2Image convert = new Mat2Image(BufferedImage.TYPE_3BYTE_BGR);
	        BufferedImage bufferedImage = convert.getImage(image);
		byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
		PixelBuffer pix = new PixelBuffer(pixels, bufferedImage.getHeight(), bufferedImage.getWidth());
		return pix;
	}

	//BEGINNING OF FILE IO FUNCTIONS
	public static PixelBuffer getPixelBufferFromFilename(String filename) throws IOException {
		File file = new File(filename);
		BufferedImage bufferedImage = ImageIO.read(file);
		System.out.println(bufferedImage.getType());
		System.out.println(BufferedImage.TYPE_3BYTE_BGR);
		System.out.println(BufferedImage.TYPE_INT_RGB);
		byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
		PixelBuffer pix = new PixelBuffer(pixels, bufferedImage.getHeight(), bufferedImage.getWidth());
		return pix;
	}

	public static void savePixelBufferToFilename(String filename, PixelBuffer pixelBuffer) throws IOException {
		int height = pixelBuffer.getHeight();
		int width = pixelBuffer.getWidth();
		byte[] data = pixelBuffer.getData();
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		bufferedImage.getRaster().setDataElements(0, 0, width, height, data);

		File output = new File(filename);
		ImageIO.write(bufferedImage, "jpg", output);
	}

	public static byte[] getByteArrayFromFilename(String filename) throws IOException {
		File input = new File(filename);
		byte[] data = new byte[(int) input.length()];
		FileInputStream fis = new FileInputStream(input);
		fis.read(data);
		fis.close();
		return data;
	}
	public static void saveByteArrayToFilename(String filename, byte[] data) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		fos.write(data);
		fos.close();
	}

	public static int[] getIntegerArrayFromFilename(String filename) throws IOException {
		File input = new File(filename);
		int numInts = (int)input.length()/4;
		int[] arr = new int[numInts];
		DataInputStream dis = new DataInputStream(new FileInputStream(input));
		for (int i = 0; i < numInts; i++) {
			arr[i] = dis.readInt();
		}
		return arr;
	}

	public static void saveIntegerArrayToFilename(String filename, int[] data) throws IOException {
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
		for (int datum : data) {
			dos.writeInt(datum);
		}
		dos.close();
	}

	public static String getStringFromFilename(String filename) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(filename));
    	try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public static void saveStringToFilename(String filename, String text) throws IOException {
		PrintWriter pw = new PrintWriter(filename);
		pw.print(text);
		pw.close();
	}
	// END OF FILE IO FUNCTINONS

	//Builds an image using label array and labelToColor mapping
	public static byte[] makeImageArrayFromLabelData(BlobLabelData blobLabelData) {
		int[] labels = blobLabelData.getLabelData();
		Map<Integer, Byte> labelToColor = blobLabelData.getLabelToColorMap();
		byte[] data = new byte[3*labels.length];
		int offset = 0;
		for (int i = 0; i < labels.length; i++) {
			int curLabel = labels[i];
			byte b = labelToColor.get(curLabel);
			GameColor gc = getGameColorFromByte(b);
			byte[] colorByteArray = getByteArrayFromGameColor(gc);
			for (int j = 0; j < 3; j++) {
				data[offset+j] = colorByteArray[j];
			}
			offset+=3;
		}
		return data;
	}
}

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import java.io.File;
import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class ProcessImage {
	public static void main( String[] args ) { 
		try {
			System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

			//Read buffered image from file
			File input = new File("../pics/One.jpg");
			BufferedImage image = ImageIO.read(input);	

			//Dump BufferedImage into mat
			byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
			mat.put(0, 0, data);

			// Create mat1 based on mat
			Mat mat1 = new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);
			Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

			// Dump mat1 into BufferedImage
			byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int)(mat1.elemSize())];
			mat1.get(0, 0, data1);
			BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
			image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

			// Write BufferedImage to file
			File ouptut = new File("../pics/grayscale.jpg");
			ImageIO.write(image1, "jpg", ouptut);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}

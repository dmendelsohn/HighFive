import org.opencv.core.*;
import org.opencv.highgui.*;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VisionTest {

    public static void main(String[] args) {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	ImageProcessor processor = new ImageProcessor();
	VideoCapture camera = new VideoCapture();
	camera.open(1);
	System.out.println("opened camera");
	
	int width = (int) (camera.get(Highgui.CV_CAP_PROP_FRAME_WIDTH));
	int height = (int) (camera.get(Highgui.CV_CAP_PROP_FRAME_HEIGHT));
	JLabel cameraPane = createWindow("Camera output", width, height);
	JLabel opencvPane = createWindow("OpenCV output", width, height);
	
	Mat rawImage = new Mat();
	Mat2Image rawImageConverter = new Mat2Image(BufferedImage.TYPE_3BYTE_BGR);
	Mat2Image processedImageConverter = new Mat2Image(BufferedImage.TYPE_3BYTE_BGR);
	int count = 0;
	while(true){
	    while(!camera.read(rawImage)){
		delay(1);
	    }
	    if(count%100==0){
		 Highgui.imwrite( "../raw_image_" +count/100 + ".jpg", rawImage);
	    }
	    System.out.println("processing image");
	    Mat processedImage = processor.processFrame(rawImage);
	    System.out.println(rawImage);
	    System.out.println(processedImage);

	    updateWindow(cameraPane, rawImage, rawImageConverter);
	    updateWindow(opencvPane, processedImage, processedImageConverter);
	    delay(50);
	    count++;
	}
    }
    
    private static JLabel createWindow(String name, int width, int height) {    
        JFrame imageFrame = new JFrame(name);
        imageFrame.setSize(width, height);
        imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel imagePane = new JLabel();
        imagePane.setLayout(new BorderLayout());
        imageFrame.setContentPane(imagePane);
        
        imageFrame.setVisible(true);
        return imagePane;
    }
    
    private static void updateWindow(JLabel imagePane, Mat mat, Mat2Image converter) {
    	int w = (int) (mat.size().width);
    	int h = (int) (mat.size().height);
    	if (imagePane.getWidth() != w || imagePane.getHeight() != h) {
    		imagePane.setSize(w, h);
    	}
    	BufferedImage bufferedImage = converter.getImage(mat);
    	imagePane.setIcon(new ImageIcon(bufferedImage));
    }


  public static void delay(int ms){
      try{
	  Thread.sleep(ms);
      } catch(Exception e){
	  //I dont give a fuck
      }
  }
}

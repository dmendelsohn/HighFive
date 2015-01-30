package subsystems;
import robot.RobotMap;

import vision.VisionThread;


public class Vision{
    VisionThread visionThread;

    public Vision(){
	System.out.println("Hello Vision!");
	visionThread = new VisionThread(0);
	visionThread.start();
    }

    public boolean senseTarget(){
	return visionThread.isBlockSeen();
    }

    public double getDistance(){
	return visionThread.getDistanceToNearestBlock();
    }
    public double howCentered(){
	//-1 for to left, 0 to middle, 1 to right , 2 if non-existent
	return visionThread.getHeadingToNearestBlock();
    }

	public double getAngle() {
		double xFraction = visionThread.getHeadingToNearestBlock();
		return 0.5*RobotMap.CAMERA_ANGLE*xFraction;
	}

    public void doNothing(){
	//Empty function body
    }
}

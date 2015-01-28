package subsystems;
import robot.RobotMap;

import vision.VisionThread;


public class Vision{
	VisionThread visionThread;

    public Vision(){
		System.out.println("Hello Vision!");
		visionThread = new VisionThread(0);
		visionThread.run();
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

	public void doNothing(){
		//Empty function body
	}
}

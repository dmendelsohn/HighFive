package robot;

import jmraa.Utils;

public class InputStateVariables{

    public double gyroAngle; 

    public int frontIRDist;
    public int rightBackIRDist;
    public int rightFrontIRDist;

    public String closerSide;

    public boolean seesTarget; //vision
    public double howCentered; //vision
    public double boxDistance; //vision

    public double photoReading;
    public String photoState;

    public InputStateVariables(InstantiatedSystems systems){
	//give values to different values using systems

	gyroAngle = systems.readGyroAngle();
	//System.out.println("gyro: " + gyroAngle);

	//leftEncoderCount = systems.readLeftEncoderCount();
	//rightEncoderCount = systems.readRightEncoderCount();
	//conveyorEncoderCount = systems.readConveyorEncoderCount();

	frontIRDist = systems.readFrontIRDist();
	rightBackIRDist = systems.readRightBackIRDist();
	rightFrontIRDist = systems.readRightFrontIRDist();

	System.out.println("front: " + frontIRDist);
	System.out.println("rightBack: " + rightBackIRDist);
	System.out.println("rightFront: " + rightFrontIRDist);
	
	photoReading = systems.colorSensor.read();
	//System.out.println("color reading:"+photoReading);
	
	if (photoReading < RobotMap.GREEN_RED_COLOR_BOUNDARY && photoReading >  RobotMap.NOTHING_GREEN_COLOR_BOUNDARY){
	    photoState = "green";
	}else if (photoReading > RobotMap.GREEN_RED_COLOR_BOUNDARY){
	    photoState = "red";
	}else{
	    photoState = "none";
	}	
	//System.out.println("color:" + photoState);
    }

}

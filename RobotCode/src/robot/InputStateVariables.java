package robot;

import jmraa.Utils;
import robot.Enums.*;

public class InputStateVariables{

    public double gyroAngle; 

    public int frontIRDist;
    public int rightBackIRDist;
    public int rightFrontIRDist;

    public boolean seesTarget; //vision
    public double howCentered; //vision
    public double boxDistance; //vision

    public double photoReading;

	public int[] lineReadings;  //Length 3 array of analog readings

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

	//	System.out.println("front: " + frontIRDist);
	//	System.out.println("rightBack: " + rightBackIRDist);
	//	System.out.println("rightFront: " + rightFrontIRDist);
	
	photoReading = systems.colorSensor.read();


	lineReadings = systems.getLineReadings();
    }

}

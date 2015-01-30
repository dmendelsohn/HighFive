package robot;

import jmraa.Utils;
import robot.Enums.*;

public class InputStateVariables{

    public double gyroAngle; 

    public int frontIRDist;
    public int rightBackIRDist;
    public int rightFrontIRDist;
    public int frontShortIRVal;

    public boolean seesTarget; //vision
    public double howCentered; //vision
    public double boxDistance; //vision
	public double targetAngle; //vision

    public double photoReading;
	public int[] lineReadings;  //Length 3 array of analog readings

	public boolean rightFrontContact;
	public boolean rightBackContact;

    public InputStateVariables(InstantiatedSystems systems){
		gyroAngle = systems.readGyroAngle();

		frontIRDist = systems.readFrontIRDist();
		rightBackIRDist = systems.readRightBackIRDist();
		rightFrontIRDist = systems.readRightFrontIRDist();
		frontShortIRVal = systems.readFrontShortIRVal();
	
		photoReading = systems.readPhotoVal();

		lineReadings = systems.getLineReadings();
	
		seesTarget = systems.vision.senseTarget();
		howCentered = systems.vision.howCentered();
		boxDistance = systems.vision.getDistance();
		targetAngle = systems.vision.getAngle();

		rightFrontContact = systems.getRightFrontContact();
		rightBackContact = systems.getRightBackContact();
	}

}

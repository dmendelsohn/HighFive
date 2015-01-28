package robot;

import jmraa.Utils;

public class InputStateVariables{

    public double gyroAngle; 

    //public double leftEncoderCount;
    //public double rightEncoderCount;
    //public double conveyorEncoderCount;

    public double frontUltraDist;
    public double leftBackUltraDist;
    public double leftFrontUltraDist;
    public double rightBackUltraDist;
    public double rightFrontUltraDist;

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

	//System.out.println("front");
	frontUltraDist = systems.readFrontUltraDist();
	//Utils.usleep(60);
	//System.out.println("left back");
        leftBackUltraDist = systems.readLeftBackUltraDist();
	//Utils.usleep(60);
	//System.out.println("left front");
	leftFrontUltraDist = systems.readLeftFrontUltraDist();
	//Utils.usleep(60);
	//System.out.println("right back");
	rightBackUltraDist = systems.readRightBackUltraDist();
	//Utils.usleep(60);
	//System.out.println("right front");
	rightFrontUltraDist = systems.readRightFrontUltraDist();

	System.out.println("front:"+ frontUltraDist);
	System.out.println("leftback:"+ leftBackUltraDist);
	System.out.println("leftfront:"+ leftFrontUltraDist);
	System.out.println("rightBack:"+ rightBackUltraDist);
	System.out.println("rightFront:"+ rightFrontUltraDist);

	if((leftBackUltraDist+leftFrontUltraDist)<(rightBackUltraDist+rightFrontUltraDist)){
	    closerSide = "left";
	}else{
	    closerSide = "right";
	}
	System.out.println(closerSide);

	photoReading = systems.colorSensor.read();
	if (photoReading < 400 && photoReading > 300){
	    photoState = "green";
	}else if (photoReading > 450){
	    photoState = "red";
	}else{
	    photoState = "none";
	}

	seesTarget = systems.vision.senseTarget();
	howCentered = systems.vision.howCentered();
	boxDistance = systems.vision.getDistance();
    }

}

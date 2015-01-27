package robot;

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

	//leftEncoderCount = systems.readLeftEncoderCount();
	//rightEncoderCount = systems.readRightEncoderCount();
	//conveyorEncoderCount = systems.readConveyorEncoderCount();

	frontUltraDist = systems.readFrontUltraDist();
        leftBackUltraDist = systems.readLeftBackUltraDist();
	leftFrontUltraDist = systems.readLeftFrontUltraDist();
	rightBackUltraDist = systems.readRightBackUltraDist();
	rightFrontUltraDist = systems.readRightFrontUltraDist();

	if((leftBackUltraDist+leftFrontUltraDist)<(rightBackUltraDist+rightFrontUltraDist)){
	    closerSide = "left";
	}else{
	    closerSide = "right";
	}

	photoReading = systems.colorSensor.read();
	if (photoReading < 400 && photoReading > 300){
	    photoState = "green";
	}else if (photoReading > 450){
	    photoState = "red";
	}else{
	    photoState = "none";
	}
    }

}

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
	System.out.println("gyro: " + gyroAngle);

	//leftEncoderCount = systems.readLeftEncoderCount();
	//rightEncoderCount = systems.readRightEncoderCount();
	//conveyorEncoderCount = systems.readConveyorEncoderCount();

	frontUltraDist = systems.readFrontUltraDist();
        leftBackUltraDist = systems.readLeftBackUltraDist();
	leftFrontUltraDist = systems.readLeftFrontUltraDist();
	rightBackUltraDist = systems.readRightBackUltraDist();
	rightFrontUltraDist = systems.readRightFrontUltraDist();

	/*System.out.println("front:"+ frontUltraDist);
	System.out.println("leftback:"+ leftBackUltraDist);
	System.out.println("leftfront:"+ leftFrontUltraDist);
	System.out.println("rightBack:"+ rightBackUltraDist);
	System.out.println("rightFront:"+ rightFrontUltraDist);*/

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
    }

}

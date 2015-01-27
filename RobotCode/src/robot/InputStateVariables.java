package robot;

public class InputStateVariables{

    public double gyroAngle; 

    //public double leftEncoderCount;
    //public double rightEncoderCount;
    //public double conveyorEncoderCount;

    public double leftBackUltraDist;
    public double leftFrontUltraDist;
    public double rightBackUltraDist;
    public double rightFrontUltraDist;

    public boolean seesTarget; //vision
    public double howCentered; //vision
    public double boxDistance; //vision

    public int photoState;//-1 if red, 0 for none, 1 if green

    public boolean feedLimitEngaged; 

    public InputStateVariables(InstantiatedSystems systems){
	//give values to different values using systems

	gyroAngle = systems.readGyroAngle();

	//leftEncoderCount = systems.readLeftEncoderCount();
	//rightEncoderCount = systems.readRightEncoderCount();
	//conveyorEncoderCount = systems.readConveyorEncoderCount();
	
        leftBackUltraDist = systems.readLeftBackUltraDist();
	leftFrontUltraDist = systems.readLeftFrontUltraDist();
	rightBackUltraDist = systems.readRightBackUltraDist();
	rightFrontUltraDist = systems.readRightFrontUltraDist();

    }

}

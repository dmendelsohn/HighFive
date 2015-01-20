package robot;

public class InputStateVariables{

	public double gyroAngle; 
	public double leftEncoderCount;
	public double rightEncoderCount;
	public double leftUltraDist;
	public double rightUltraDist;

	public boolean seesTarget; //vision
	public double howCentered; //vision
	public double boxDistance; //vision

	public boolean feedLimitEngaged; 

	public InputStateVariables(InstantiatedSystems systems){
		//give values to different values using systems

		gyroAngle = systems.readGyroAngle();

		leftEncoderCount = systems.readLeftEncoderCount();
		rightEncoderCount = systems.readRightEncoderCount();

		//feedLimitEngaged = systems.checkConveyorLimitSwitch();
	
		//leftUltraDist = systems.readLeftUltraDist();
		//rightUltraDist = systems.readRightUltraDist();
	}
}

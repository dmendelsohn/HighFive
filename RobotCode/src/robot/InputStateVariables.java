package robot;

public class InputStateVariables{

	public boolean seesTarget; //vision
	public double gyroAngle; //drivetrain
	public double howCentered; //vision
	public double boxDistance; //vision

	public boolean outerFeedLimitEngaged; //conveyor belt
	public boolean innerFeedLimitEngaged; 
	//sensing results 

	public InputStateVariables(InstantiatedSystems systems){
		//give values to different values using systems
	}
}

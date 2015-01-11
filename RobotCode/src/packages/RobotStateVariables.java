package packages;

public class RobotStateVariables{

	boolean hasFuckedStevensMom;
	//drive train
	double drivetrainLeftMotorSpeed;
	double drivetrainRightMotorSpeed;
	double drivetrainLeftEncoder;
	double drivetrainRightEncoder;
	double drivetrainGyroAngle;
	//hoppers
	int hopperPosition;
	boolean hopperLeftRelease;
	boolean hopperRightRelease;
	//conveyor belt
	double conveyorMotorSpeed;
	double conveyorEncoder;
	boolean conveyorLimitSwitch;
	//vision
	//camera stuff

	public RobotStateVariables(){
		hasFuckedStevensMom = true;
		System.out.println("Has fucked Steven's Mom = " + hasFuckedStevensMom);
	}

}

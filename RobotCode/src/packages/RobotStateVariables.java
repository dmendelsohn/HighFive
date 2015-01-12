package packages;

public class RobotStateVariables{

	public boolean hasFuckedStevensMom = true;
	//drive train
	public double drivetrainLeftMotorSpeed;
	public double drivetrainRightMotorSpeed;
	public double drivetrainLeftEncoder;
	public double drivetrainRightEncoder;
	public double drivetrainGyroAngle;
	//hoppers
	public int hopperPosition;
	public boolean hopperLeftRelease;
	public boolean hopperRightRelease;
	//conveyor belt
	public double conveyorMotorSpeed;
	public double conveyorEncoder;
	public boolean conveyorLimitSwitch;
	//vision
	//camera stuff

	public RobotStateVariables(){
		hasFuckedStevensMom = true;
		System.out.println("Has fucked Steven's Mom = " + hasFuckedStevensMom);
	}

}

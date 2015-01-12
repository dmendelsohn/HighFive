package packages;

public class OutputStateVariables{

	public boolean hasFuckedStevensMom = true;
	//drive train
	public double drivetrainLeftMotorSpeed;
	public double drivetrainRightMotorSpeed;

	//hoppers
	public int hopperPosition;
	public boolean hopperLeftRelease;
	public boolean hopperRightRelease;
	//conveyor belt
	public double conveyorMotorSpeed;


	//vision
	//camera stuff

	public OutputStateVariables(){
		hasFuckedStevensMom = true;
		System.out.println("Has fucked Steven's Mom = " + hasFuckedStevensMom);
	}

}

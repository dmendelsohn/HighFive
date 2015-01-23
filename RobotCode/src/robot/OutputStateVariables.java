package robot;
import java.util.ArrayList;

public class OutputStateVariables{

	public boolean hasFuckedStevensMom;

	public String drivetrainMethod;
	public String drivetrainPIDType;
	public double drivetrainSpeed;

	public String hopperMethod;
	public int hopperPosition = 0;
	public boolean leftHatchOpen = false;
	public boolean rightHatchOpen = false;

	public String conveyorMethod;
	public double conveyorSpeed;

	public String visionMethod;

	public boolean zeroGyro = false;

	public OutputStateVariables(){

		hasFuckedStevensMom = true;
		System.out.println("OutputVars");

	}

}

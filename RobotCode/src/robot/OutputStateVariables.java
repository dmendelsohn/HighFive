package robot;
import java.util.ArrayList;

public class OutputStateVariables{

	public boolean hasFuckedStevensMom;

	public String drivetrainMethod;
	public String drivetrainPIDType;
	public double drivetrainSpeed;

	public String hopperMethod;
	public int hopperPosition;
	public boolean leftHatchOpen;
	public boolean rightHatchOpen;

	public String conveyorMethod;
	public double conveyorSpeed;

	public String visionMethod;

	public OutputStateVariables(){

		hasFuckedStevensMom = true;
		System.out.println("OutputVars");

	}

}

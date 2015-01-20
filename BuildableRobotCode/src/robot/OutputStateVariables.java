package robot;
import java.util.ArrayList;

public class OutputStateVariables{

	public boolean hasFuckedStevensMom;

	public String drivetrainMethod;
        public ArrayList<Object> drivetrainMethodVals;

	public String hopperMethod;
	public ArrayList<Object> hopperMethodVals;

	public String conveyorMethod;
	public ArrayList<Object> conveyorMethodVals;

	public String visionMethod;
	public ArrayList<Object> visionMethodVals;

	public OutputStateVariables(){
		hasFuckedStevensMom = true;
		System.out.println("OutputVars");
		
		drivetrainMethodVals = new ArrayList<Object>();
		hopperMethodVals = new ArrayList<Object>();
		conveyorMethodVals = new ArrayList<Object>();
		visionMethodVals = new ArrayList<Object>();
	}

}

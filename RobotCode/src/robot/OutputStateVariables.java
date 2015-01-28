package robot;

public class OutputStateVariables{

    public boolean hasFuckedStevensMom;


    public String drivetrainMethod;
    public String drivetrainPIDType;
    public double drivetrainSpeed;
    public String pidSide;

    public String sorterMethod;
    public double sorterPosition;

    public String hopperMethod;
    public boolean hopperOpenLeft = false;
    public boolean hopperOpenRight = false;

    public String conveyorMethod;
    public double conveyorSpeed;
    
    public String visionMethod;

    public boolean zeroGyro = false;

    public OutputStateVariables(){

	hasFuckedStevensMom = true;
	System.out.println("OutputVars");

    }

}

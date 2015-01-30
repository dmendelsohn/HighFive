package robot;

import static robot.Enums.*;

public class OutputStateVariables{
    public DriveTrainMethod driveTrainMethod;
	public double driveTrainSpeed;
	public double driveTrainAngle;

    public SorterMethod sorterMethod;
    public SorterPosition sorterPosition;
    //public long sorterTime;

    public HopperMethod hopperMethod;
    public boolean hopperLeftOpen = false;
    public boolean hopperRightOpen = false;

    public ConveyorMethod conveyorMethod;
    public double conveyorSpeed;
    
    public boolean zeroGyro = false;

    public OutputStateVariables(){
		//Do nothing, fields must be set manually
    }

}

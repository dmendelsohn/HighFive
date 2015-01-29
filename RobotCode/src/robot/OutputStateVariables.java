package robot;

import static robot.Enums.*;

public class OutputStateVariables{
    public DriveTrainMethod driveTrainMethod;
    public DriveTrainPidType driveTrainPidType;
    public double driveTrainSpeed;
    public CloserSide pidSide;

    public SorterMethod sorterMethod;
    public SorterPosition sorterPosition;

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

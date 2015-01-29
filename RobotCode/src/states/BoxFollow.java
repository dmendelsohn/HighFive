package states;
import robot.*;

import static robot.Enums.*;

public class BoxFollow extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;
 
    public double drive_adjustment;
    public double left_drive_speed;
    public double right_drive_speed;

    public BoxFollow(){
	super();

	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	output.driveTrainPidType = DriveTrainPidType.GYROSCOPE;
	output.driveTrainSpeed = 0.3;
	
	output.sorterMethod = SorterMethod.DO_NOTHING;
	output.hopperMethod = HopperMethod.DO_NOTHING;
	output.conveyorMethod = ConveyorMethod.DO_NOTHING;

		
    }

    public OutputStateVariables run(InputStateVariables input){
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	//if(input.feedLimitEngaged){
/*	if(false){
	    return new ConveyorCapture();
	}else if(System.currentTimeMillis()-stateStartTime > 1500.){
	    return new BlindBoxFollow();
	} else{
	    return this;
	}*/
		return this;
    }
}

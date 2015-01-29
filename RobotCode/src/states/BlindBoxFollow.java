package states;
import robot.*;

import static robot.Enums.*;

public class BlindBoxFollow extends StateBase{
	
    public OutputStateVariables output;

    public BlindBoxFollow(){
	super();
	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	output.driveTrainPidType = DriveTrainPidType.GYROSCOPE;
	output.driveTrainSpeed = 0.3;

	output.hopperMethod = HopperMethod.DO_NOTHING;
	output.conveyorMethod = ConveyorMethod.DO_NOTHING;
	output.sorterMethod = SorterMethod.DO_NOTHING;

	stateStartTime = System.currentTimeMillis();
    }

    public OutputStateVariables run(InputStateVariables input){
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	//if(input.feedLimitEngaged){
/*	if(false){
	    return new ConveyorCapture();
	} else if(System.currentTimeMillis()-stateStartTime>4000.){
	    return new BoxSearch();
	    //} else if(input.seesTarget){
	}else if (false){
	    return new BoxFollow();
	} else {
	    return this;
	}*/
		return this;
    }
}


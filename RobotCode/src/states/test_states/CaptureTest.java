package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class CaptureTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public CaptureTest(){

	super();

	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	output.driveTrainPidType = DriveTrainPidType.GYROSCOPE;
	output.driveTrainSpeed = 0.2;

	output.conveyorMethod = ConveyorMethod.MOVE_BELT;
	output.conveyorSpeed = 0.1;

	output.sorterMethod = SorterMethod.DO_NOTHING;
	output.hopperMethod = HopperMethod.DO_NOTHING;

	output.zeroGyro = true;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
	output.hopperMethod = HopperMethod.DO_NOTHING;
	output.zeroGyro = false;

	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<15000){

	    output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	    output.driveTrainPidType = DriveTrainPidType.GYROSCOPE;
	    output.driveTrainSpeed = 0.2;

	    output.conveyorMethod = ConveyorMethod.MOVE_BELT;
	    output.conveyorSpeed = 0.2;

	}else{
	    output.driveTrainMethod = DriveTrainMethod.STOP;
	    output.conveyorMethod = ConveyorMethod.STOP_BELT;
	}

	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

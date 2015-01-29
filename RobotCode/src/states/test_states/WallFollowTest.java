package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class WallFollowTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public WallFollowTest(){
	super();
	output = new OutputStateVariables();
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE_TWO_INPUTS;
	output.driveTrainSpeed = 0.2;
	output.sorterMethod = SorterMethod.DO_NOTHING;
	output.hopperMethod = HopperMethod.DO_NOTHING;
	output.conveyorMethod = ConveyorMethod.DO_NOTHING;
    }

    public OutputStateVariables run(InputStateVariables input){
	if (System.currentTimeMillis()-stateStartTime>10000){
	    output.driveTrainMethod = DriveTrainMethod.STOP;
	}	
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

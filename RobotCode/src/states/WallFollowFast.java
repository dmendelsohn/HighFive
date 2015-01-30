package states;
import states.*;
import states.test_states.*;
import robot.*;

import static robot.Enums.*;

public class WallFollowFast extends StateBase{
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE_TWO_INPUTS;
	output.driveTrainSpeed = 0.3;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();	
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	if(input.isInHomeBase){
	    return new TurnOut();
	} else if(input.frontIRDist > RobotMap.FRONT_IR_UPPER_THRESHOLD){
	    return new WallTurnFast();
	} else {
	    return this;
	}
    }
}

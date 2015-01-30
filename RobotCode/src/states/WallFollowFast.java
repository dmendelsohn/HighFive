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
	output.driveTrainSpeed = 0.25;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();	
	System.out.println("in home base? " + input.isInHomeBase);
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	if(input.isInHomeBase){
	    return new TurnOut();
	} else if(input.frontIRDist > RobotMap.FRONT_IR_UPPER_THRESHOLD){
	    return new WallTurnFast();
	} else if(input.runTime > 170000){
	    return new TurnOut();
	} else {
	    return this;
	}
    }
}

package states;
import states.*;
import states.test_states.*;
import robot.*;

import static robot.Enums.*;

public class WallFollow extends StateBase{
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE_TWO_INPUTS;
	output.driveTrainSpeed = 0.175;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();	
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	if(input.frontIRDist > RobotMap.FRONT_IR_UPPER_THRESHOLD){
	    return new WallTurn();
	} else if(input.runTime > RobotMap.FIND_HOME_TIME) {
	    return new WallFollowFast();
	}
	else if (input.seesTarget) {
		return new FindBlockTest();
	} else {
	    return this;
	}
    }
}

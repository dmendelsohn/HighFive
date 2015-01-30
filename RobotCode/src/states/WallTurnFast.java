package states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class WallTurnFast extends StateBase{
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.driveTrainMethod = DriveTrainMethod.SET_TURN_ROUGH;
	output.driveTrainSpeed = -0.25;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();	
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	if(input.frontShortIRVal == 1 && input.frontIRDist < RobotMap.FRONT_IR_LOWER_THRESHOLD){
	    return new WallFollowFast();
	} else{
	    return this;
	}
    }
}

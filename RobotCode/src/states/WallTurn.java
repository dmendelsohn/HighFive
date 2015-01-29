package states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class WallTurn extends StateBase{
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.driveTrainMethod = DriveTrainMethod.SET_TURN_ROUGH;
	output.driveTrainSpeed = -0.25;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	super.run(input); //Boilerplate
	OutputStateVariables output = getDefaultOutput();	
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	if(input.frontIRDist < RobotMap.FRONT_IR_LOWER_THRESHOLD){
	    return new WallFollow();
	} else{
	    return this;
	}
    }
}

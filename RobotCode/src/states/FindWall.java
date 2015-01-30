package states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class FindWall extends StateBase{
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	output.driveTrainSpeed = 0.2;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();	
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	if(input.frontShortIRVal == 0 || input.frontIRDist > RobotMap.FRONT_IR_UPPER_THRESHOLD){
	    return new WallTurn();
	} else if(input.rightFrontIRDist > RobotMap.RIGHT_FRONT_IR_THRESHOLD){
	    return new WallFollow();
	} else{
	    return this;
	}
    }
}

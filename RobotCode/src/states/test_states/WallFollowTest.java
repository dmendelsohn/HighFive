package states.test_states;
import states.*;
import states.test_states.*;
import robot.*;

import static robot.Enums.*;

public class WallFollowTest extends StateBase{
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE_TWO_INPUTS;
	output.driveTrainPidAngle = 0.0;
	output.driveTrainSpeed = 0.2;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();
	
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	if (getElapsedTime() > 60000){
	    return new BoxSearchTest();
	}else{	
	return this;
	}
    }
}

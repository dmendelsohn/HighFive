package states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class TurnOut extends StateBase{
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
	if(getElapsedTime() > 1000){
	    return new Deploy();
	} else{
	    return this;
	}
    }
}

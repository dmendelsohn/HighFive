package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class WallFollowTest extends StateBase{
	@Override
	public OutputStateVariables getDefaultOutput() {
		OutputStateVariables output = super.getDefaultOutput();
		output.driveTrainMethod = DriveTrainMethod.PID_DRIVE_TWO_INPUTS;
		output.driveTrainSpeed = 0.2;
		return output;
	}

    public OutputStateVariables run(InputStateVariables input){
		super.run(input); //Boilerplate
		OutputStateVariables output = getDefaultOutput();	
		return output;
    }

    public StateBase getNext(InputStateVariables input){
		return this;
    }
}

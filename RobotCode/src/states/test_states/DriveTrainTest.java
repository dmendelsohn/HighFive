package states.test_states;
import states.*;
import robot.*;

import robot.Enums.*;

public class DriveTrainTest extends StateBase{
    public DriveTrainTest(){
		super();
	}

	@Override
	protected OutputStateVariables getDefaultOutput() {
		OutputStateVariables output = super.getDefaultOutput();
		output.driveTrainMethod = DriveTrainMethod.MOVE_STRAIGHT_ROUGH;
		output.driveTrainSpeed = 0.2;
		return output;
	}

    public OutputStateVariables run(InputStateVariables input){
		super.run(input); //Boilerplate
		OutputStateVariables output = getDefaultOutput();
		if (elapsedTime<5000){
			//Use default
		} else{
			output.driveTrainSpeed = 0;
		}
		return output;
    }

    public StateBase getNext(InputStateVariables input){
		return this;
    }
}

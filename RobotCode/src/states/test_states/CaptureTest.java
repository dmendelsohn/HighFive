package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class CaptureTest extends StateBase{
	@Override
	public OutputStateVariables getDefaultOutput() {
		OutputStateVariables output = super.getDefaultOutput();
		
		//DriveTrain
		output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
		output.driveTrainSpeed = 0.2;

		//Conveyor
		output.conveyorMethod = ConveyorMethod.MOVE_BELT;
		output.conveyorSpeed = 0.1;

		return output;
	}

	@Override
    public OutputStateVariables run(InputStateVariables input){
		OutputStateVariables output = super.getDefaultOutput();
		if (getElapsedTime() < 15000){
			//Do nothing
		} else {
	    	output.conveyorMethod = ConveyorMethod.STOP_BELT;
		}
		return output;
	}

    public StateBase getNext(InputStateVariables input){
		return this;
    }
}

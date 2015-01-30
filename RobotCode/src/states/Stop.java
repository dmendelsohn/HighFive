package states;

import robot.*;
import states.*;
import states.test_states.*;

import static robot.Enums.*;

public class Stop extends StateBase {
	@Override
	public OutputStateVariables run(InputStateVariables input) {
		OutputStateVariables output = getDefaultOutput();
		output.hopperMethod = HopperMethod.MOVE_BOTH;
		output.hopperLeftOpen = true;
		output.hopperRightOpen = true;
		output.driveTrainMethod = DriveTrainMethod.STOP;
		output.conveyorMethod = ConveyorMethod.STOP_BELT;
		return output;
	}

	@Override
	public StateBase getNext(InputStateVariables input) {
			return this;
	}
}

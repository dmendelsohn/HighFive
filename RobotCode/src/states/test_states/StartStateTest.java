package states.test_states;

import robot.*;
import states.*;
import states.test_states.*;

import static robot.Enums.*;

public class StartStateTest extends StateBase {
	@Override
	public OutputStateVariables run(InputStateVariables input) {
		OutputStateVariables output = getDefaultOutput();
		output.hopperMethod = HopperMethod.MOVE_BOTH;
		output.hopperLeftOpen = false;
		output.hopperRightOpen = false;
		return output;
	}

	@Override
	public StateBase getNext(InputStateVariables input) {
		if (getElapsedTime() > 10000) {
			return new FindBlockTest();
		} else {
			return this;
		}
	}
}
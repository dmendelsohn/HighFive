package states.test_states;

import robot.*;
import states.*;
import states.test_states.*;

import static robot.Enums.*;

public class StartStateTest extends StateBase {
	@Override
	public OutputStateVariables run(InputStateVariables input) {
		return getDefaultOutput();
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

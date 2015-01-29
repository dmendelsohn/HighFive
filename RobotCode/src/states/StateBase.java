package states;
import robot.*;

import static robot.Enums.*;

public abstract class StateBase{
    protected long stateStartTime;
	protected long elapsedTime;

    public StateBase(){	
		stateStartTime = System.currentTimeMillis();
    }

	public String getStateName() {
		return this.getClass().getName();
	}

	protected OutputStateVariables getDefaultOutput() {
		OutputStateVariables output = new OutputStateVariables();
		output.driveTrainMethod = DriveTrainMethod.DO_NOTHING;
		output.conveyorMethod = ConveyorMethod.DO_NOTHING;
		output.sorterMethod = SorterMethod.DO_NOTHING;
		output.hopperMethod = HopperMethod.DO_NOTHING;
		output.zeroGyro = false;
		return output;
	}

    public OutputStateVariables run(InputStateVariables input) {
		elapsedTime = System.currentTimeMillis();
		return getDefaultOutput();
	}

    public StateBase getNext(InputStateVariables input) {
		return this;
	}
		
}

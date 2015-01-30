package states;
import robot.*;

import static robot.Enums.*;

public abstract class StateBase{
    protected long stateStartTime;

    public StateBase(){	
		stateStartTime = System.currentTimeMillis();
    }

	public String getStateName() {
		return this.getClass().getName();
	}

	public long getElapsedTime() {
		return System.currentTimeMillis() - stateStartTime;
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

    public abstract OutputStateVariables run(InputStateVariables input);
    public abstract StateBase getNext(InputStateVariables input);
}

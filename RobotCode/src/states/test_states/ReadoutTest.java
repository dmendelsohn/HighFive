package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class ReadoutTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public ReadoutTest(){
	super();
	output = new OutputStateVariables();
	output.driveTrainMethod = DriveTrainMethod.DO_NOTHING;
	output.driveTrainSpeed = 0;
	output.sorterMethod = SorterMethod.DO_NOTHING;
	output.hopperMethod = HopperMethod.DO_NOTHING;
	output.conveyorMethod = ConveyorMethod.DO_NOTHING;
    }

    public OutputStateVariables run(InputStateVariables input){
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class VisionTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public VisionTest(){

	super();

	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.DO_NOTHING;
	output.conveyorMethod = ConveyorMethod.DO_NOTHING;
	output.sorterMethod = SorterMethod.DO_NOTHING;
	output.hopperMethod = HopperMethod.DO_NOTHING;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
	long elapsedTime = System.currentTimeMillis()-stateStartTime;
	
	//TODO: implement	
		
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

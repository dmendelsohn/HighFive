package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class HopperColorTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public HopperColorTest(){

	super();

	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.DO_NOTHING;
	output.conveyorMethod = ConveyorMethod.DO_NOTHING;

	output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
	output.sorterPosition = SorterPosition.MIDDLE;

	output.hopperMethod = HopperMethod.DO_NOTHING;

	output.zeroGyro = false;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){

	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	     
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }

}

package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class SorterColorTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public SorterColorTest(){

	super();

	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.DO_NOTHING;
	output.conveyorMethod = ConveyorMethod.DO_NOTHING;

	output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
	output.sorterPosition = SorterPosition.MIDDLE;

	output.hopperMethod = HopperMethod.MOVE_BOTH;
	output.hopperLeftOpen = false;
	output.hopperRightOpen = false;

	output.zeroGyro = false;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
	
	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<120000){	
	    output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
	}
	     
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

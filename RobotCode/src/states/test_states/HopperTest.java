package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class HopperTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public HopperTest(){

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
	     
	if (elapsedTime<5000){
	    output.hopperMethod = HopperMethod.MOVE_LEFT;
	    output.hopperLeftOpen = true;
	}else if (elapsedTime<6000){
	    output.hopperMethod = HopperMethod.MOVE_LEFT;
	    output.hopperLeftOpen = false;
	}else if (elapsedTime<7000){
	    output.hopperMethod = HopperMethod.MOVE_RIGHT;
	    output.hopperRightOpen = true;
	}else if (elapsedTime<8000){
	    output.hopperMethod = HopperMethod.MOVE_RIGHT;
	    output.hopperRightOpen = false;
	}
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class SorterTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public SorterTest(){

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
	    output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
	    output.sorterPosition = SorterPosition.LEFT;
	}else if (elapsedTime<6000){
	    output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
	    output.sorterPosition = SorterPosition.MIDDLE;
	}else if (elapsedTime<7000){
	    output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
	    output.sorterPosition = SorterPosition.RIGHT;
	}else if (elapsedTime<8000){
	    output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
	    output.sorterPosition = SorterPosition.MIDDLE;
	} 
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

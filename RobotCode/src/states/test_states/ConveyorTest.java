package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class ConveyorTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public ConveyorTest(){

	super();

	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.DO_NOTHING; 
		
	output.conveyorMethod = ConveyorMethod.MOVE_BELT;
	output.conveyorSpeed = 0.1;
	output.sorterMethod = SorterMethod.DO_NOTHING;
	output.hopperMethod = HopperMethod.DO_NOTHING;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){

	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<25000){	
	    output.conveyorMethod = ConveyorMethod.MOVE_BELT;
	    output.conveyorSpeed = 0.1;
	}else{
	    output.conveyorMethod = ConveyorMethod.STOP_BELT;
	}
		
	System.out.println(output.conveyorMethod);
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

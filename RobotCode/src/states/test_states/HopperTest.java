package states.test_states;
import states.*;
import robot.*;


public class HopperTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public HopperTest(){

	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "doNothing";
	output.conveyorMethod = "doNothing";

	output.sorterMethod = "setSorterPosition";
	output.sorterPosition = -0.06;

	output.hopperMethod = "doNothing";
	output.hopperOpenLeft = false;
	output.hopperOpenRight = false;

	output.visionMethod = "doNothing";

	output.zeroGyro = false;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){

	System.out.println("HopperTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;
	     
	if (elapsedTime<5000){
	    output.hopperMethod = "hopperOpenLeft";
	    output.hopperOpenLeft = true;
	}else if (elapsedTime<6000){
	    output.hopperMethod = "hopperOpenLeft";
	    output.hopperOpenLeft = false;
	}else if (elapsedTime<7000){
	    output.hopperMethod = "hopperOpenRight";
	    output.hopperOpenRight = true;
	}else if (elapsedTime<8000){
	    output.hopperMethod = "hopperOpenRight";
	    output.hopperOpenRight = false;
	}
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

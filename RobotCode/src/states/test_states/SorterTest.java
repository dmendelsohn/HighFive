package states.test_states;
import states.*;
import robot.*;


public class SorterTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public SorterTest(){

	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "doNothing";
	output.conveyorMethod = "doNothing";

	output.sorterMethod = "setSorterPosition";
	output.sorterPosition = -0.03;

	output.hopperMethod = "hopperOpenBoth";
	output.hopperOpenLeft = false;
	output.hopperOpenRight = false;

	output.visionMethod = "doNothing";

	output.zeroGyro = false;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
	output.hopperMethod = "doNothing";

	System.out.println("SorterTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;
	
	if (elapsedTime<5000){	
	    output.sorterMethod = "setSorterPosition";
	    output.sorterPosition = -1.0;
	}else if (elapsedTime<6000){
	    output.sorterMethod = "setSorterPosition";
	    output.sorterPosition = -0.03;
	}else if (elapsedTime<7000){
	    output.sorterMethod = "setSorterPosition";
	    output.sorterPosition = 1.0;
	}else if (elapsedTime<8000){
	    output.sorterMethod = "setSorterPosition";
	    output.sorterPosition = -0.03;
	} 
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

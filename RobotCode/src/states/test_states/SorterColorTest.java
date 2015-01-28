package states.test_states;
import states.*;
import robot.*;


public class SorterColorTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public SorterColorTest(){

	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "doNothing";
	output.conveyorMethod = "doNothing";

	output.sorterMethod = "setSorterPosition";
	output.sorterPosition = -.06;

	output.hopperMethod = "hopperOpenBoth";
	output.hopperOpenLeft = false;
	output.hopperOpenRight = false;

	output.visionMethod = "doNothing";

	output.zeroGyro = false;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
	
	System.out.println("SorterColorTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<120000){	
	    output.sorterMethod = "setSorterPositionColor";
	}
	     
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
    public String getName(){
	return "SorterColorTest";
    }
}

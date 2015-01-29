package states.test_states;
import states.*;
import robot.*;


public class HopperColorTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public HopperColorTest(){

	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "doNothing";
	output.conveyorMethod = "doNothing";

	output.hopperMethod = "setSorterPosition";
	output.sorterPosition = -.1;
	output.hopperOpenLeft = false;
	output.hopperOpenRight = false;

	output.visionMethod = "doNothing";

	output.zeroGyro = false;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){

	System.out.println("HopperTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<120000){	
	    output.hopperMethod = "setSorterPositionColor";
	}
	     
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }

}

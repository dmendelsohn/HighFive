package states.test_states;
import states.*;
import robot.*;

public class WallFollowTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public WallFollowTest(){
	super();
	output = new OutputStateVariables();
	output.drivetrainMethod = "pidDriveTwoInputs";
	output.drivetrainSpeed = 0.2;
	output.sorterMethod = "doNothing";
	output.hopperMethod = "doNothing";
	output.conveyorMethod = "doNothing";
	output.visionMethod = "senseTarget";
    }

    public OutputStateVariables run(InputStateVariables input){
	System.out.println("WallFollowTest");
	if (System.currentTimeMillis()-stateStartTime>10000){
	    output.drivetrainMethod = "stop";
	}	
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

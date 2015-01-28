package states.test_states;
import states.*;
import robot.*;


public class VisionTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public VisionTest(){

	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "doNothing";
	output.conveyorMethod = "doNothing";
	output.sorterMethod = "doNothing";
	output.hopperMethod = "doNothing";
	output.visionMethod = "doNothing";

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
		
	System.out.println("VisionTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;
		
	if (elapsedTime<4000){	
	    output.visionMethod = "senseTarget";
	}else if (elapsedTime<8000){
	    output.visionMethod = "getDistance";
	}else if (elapsedTime<12000){
	    output.visionMethod = "howCentered";
	}else{
	    output.visionMethod = "doNothing";
	}
		
	System.out.println(output.visionMethod);
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
    public String getName(){
	return "VisionTest";
    }
}

package states;
import robot.*;


public class ConveyorTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public ConveyorTest(){

	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "doNothing";
		
	output.conveyorMethod = "moveBelt";
	output.conveyorSpeed = 0.2;

	output.hopperMethod = "doNothing";
	output.visionMethod = "doNothing";

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){

	System.out.println("ConveyorTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<25000){	
	    output.conveyorMethod = "moveBelt";
	    output.conveyorSpeed = 0.2;
	}else{
	    output.conveyorMethod = "stopBelt";
	}
		
	System.out.println(output.conveyorMethod);
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }

}

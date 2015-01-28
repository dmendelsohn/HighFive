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

	output.hopperMethod = "setSorterPosition";
	output.hopperPosition = -0.03;
	output.leftHatchOpen = false;
	output.rightHatchOpen = false;

	output.visionMethod = "doNothing";

	output.zeroGyro = false;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){

	System.out.println("HopperTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<5000){	
	    output.hopperMethod = "setSorterPosition";
	    output.hopperPosition = -1.0;
	}else if (elapsedTime<6000){
	    output.hopperMethod = "setSorterPosition";
	    output.hopperPosition = -0.03;
	}else if (elapsedTime<7000){
	    output.hopperMethod = "setSorterPosition";
	    output.hopperPosition = 1.0;
	}else if (elapsedTime<8000){
	    output.hopperMethod = "setSorterPosition";
	    output.hopperPosition = -0.03;
	} else if (elapsedTime<9000){
	    output.hopperMethod = "openLeftHatch";
	    output.leftHatchOpen = true;
	}else if (elapsedTime<10000){
	    output.hopperMethod = "openLeftHatch";
	    output.leftHatchOpen = false;
	}else if (elapsedTime<11000){
	    output.hopperMethod = "openRightHatch";
	    output.rightHatchOpen = true;
	}else if (elapsedTime<12000){
	    output.hopperMethod = "openRightHatch";
	    output.rightHatchOpen = false;
	}
	     
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }

}

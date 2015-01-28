package states.test_states;
import states.*;
import robot.*;


public class CaptureTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public CaptureTest(){

	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "pidDrive";
	output.drivetrainPIDType = "Gyroscope";
	output.drivetrainSpeed = 0.2;

	output.conveyorMethod = "moveBelt";
	output.conveyorSpeed = 0.1;

	output.sorterMethod = "doNothing";

        output.hopperMethod = "hopperOpenBoth";
	output.hopperOpenLeft = false;
	output.hopperOpenRight = false;

	output.visionMethod = "doNothing";

	output.zeroGyro = true;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
	output.hopperMethod = "doNothing";
	output.zeroGyro = false;

	System.out.println("CaptureTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<15000){

	    output.drivetrainMethod = "pidDrive";
	    output.drivetrainPIDType = "Gyroscope";
	    output.drivetrainSpeed = 0.2;

	    output.conveyorMethod = "moveBelt";
	    output.conveyorSpeed = 0.2;

	    output.hopperMethod = "setSorterPositionColor";

	}else{
	    output.drivetrainMethod = "stop";
	    output.conveyorMethod = "stopBelt";
	}

	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
    public String getName(){
	return "CaptureTest";
    }
}

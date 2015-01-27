package states;
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
	output.conveyorSpeed = 0.2;

	output.hopperMethod = "doNothing";
	output.visionMethod = "doNothing";

	output.zeroGyro = true;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
	output.zeroGyro = false;

	System.out.println("CaptureTest");
	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<15000){

	    output.drivetrainMethod = "pidDrive";
	    output.drivetrainPIDType = "Gyroscope";
	    output.drivetrainSpeed = 0.2;

	    output.conveyorMethod = "moveBelt";
	    output.conveyorSpeed = 0.2;

	}else{
	    output.drivetrainMethod = "stop";
	    output.conveyorMethod = "stopBelt";
	}

	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }

}

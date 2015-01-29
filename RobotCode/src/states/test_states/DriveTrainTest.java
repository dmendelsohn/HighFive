package states.test_states;
import states.*;
import robot.*;

import robot.Enums.*;

public class DriveTrainTest extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public DriveTrainTest(){

	super();

	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.STOP;

	output.conveyorMethod = ConveyorMethod.DO_NOTHING;
	output.sorterMethod = SorterMethod.DO_NOTHING;
	output.hopperMethod = HopperMethod.DO_NOTHING;

	output.zeroGyro = true;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
	output.zeroGyro = false;

	long elapsedTime = System.currentTimeMillis()-stateStartTime;

	if (elapsedTime<5000){
	    output.driveTrainMethod = DriveTrainMethod.MOVE_STRAIGHT_ROUGH;
	    output.driveTrainSpeed = 0.2;
	}else{
	    output.driveTrainMethod = DriveTrainMethod.MOVE_STRAIGHT_ROUGH;
	    output.driveTrainSpeed = 0;
	}
	/*
	if (elapsedTime<2000){	
	    output.drivetrainMethod = "moveStraightRough";
	    output.drivetrainSpeed = 0.2;		
	}else if (elapsedTime<2100){
	    output.drivetrainMethod = "stop";
	}else if (elapsedTime<4000){
	    output.drivetrainMethod = "moveStraightRough";
	    output.drivetrainSpeed = -0.2;
	}else if (elapsedTime<4100){
	    output.drivetrainMethod = "stop";
	}else if (elapsedTime<6000){
	    output.drivetrainMethod = "setTurnRough";
	    output.drivetrainSpeed = 0.2;
	}else if (elapsedTime<6100){
	    output.drivetrainMethod = "stop";
	}else if (elapsedTime<8000){
	    output.drivetrainMethod = "setTurnRough";
	    output.drivetrainSpeed = -0.2;
	}else if (elapsedTime<8100){
	    output.drivetrainMethod = "stop";
	}else if (elapsedTime<10000){
	    output.drivetrainMethod = "pidDrive";
	    output.drivetrainPIDType = "Gyroscope";
	    output.drivetrainSpeed = 0.2;
	}else{
	    output.drivetrainMethod = "stop";
	}
	*/
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

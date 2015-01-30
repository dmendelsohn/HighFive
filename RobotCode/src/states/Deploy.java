package states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class Deploy extends StateBase{

    boolean firstTime = true;
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	output.driveTrainSpeed = 0.2;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();
	long elapsed = getElapsedTime();
	if(elapsed < 2000){
	    output.driveTrainMethod = DriveTrainMethod.STOP;
	    output.hopperMethod = HopperMethod.MOVE_RIGHT;
	    output.hopperRightOpen = true;
	} else if(elapsed < 8000 || !input.isInHomeBase){
	    output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	} else if(elapsed < 9000){
	    output.driveTrainMethod = DriveTrainMethod.STOP;
	    output.hopperMethod = HopperMethod.MOVE_LEFT;
	    output.hopperLeftOpen = true;
	} else if(elapsed < 10000){
	    output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	} else{
	    output.driveTrainMethod = DriveTrainMethod.STOP;
	}
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	if(input.frontShortIRVal == 0 || input.frontIRDist > RobotMap.FRONT_IR_UPPER_THRESHOLD){
	    return new Stop();
	} else{
	    return this;
	}
    }
}

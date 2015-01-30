package states.test_states;
import states.*;
import states.test_states.*;
import robot.*;

import static robot.Enums.*;

public class BoxSearchTest extends StateBase{
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	//output.zeroGyro = true;
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	output.driveTrainSpeed = 0.0;
	output.driveTrainPidAngle = -360.0;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();
	if(super.getElapsedTime()%5000<2500){
	    output.driveTrainMethod = DriveTrainMethod.STOP;
	}else{
	    output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	    output.driveTrainSpeed = 0.0;
	    output.driveTrainPidAngle = -360.0;
	}
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	if(input.seesTarget){
	    return new VisionTest();
	}else if(input.gyroAngle>360.0){
	    return new VisionTest();
	}else if(super.getElapsedTime()>15000){
	    return new FindWallTest();
	} else{
	    return this;
	}
    }
}

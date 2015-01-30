package states.test_states;
import states.*;
import states.test_states.*;
import robot.*;

import static robot.Enums.*;

public class ForwardTest extends StateBase{
    private long followTime;

    public ForwardTest(){
	followTime = 3000;
    }

    public ForwardTest(long inputTime){
	followTime = inputTime;
    }
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.zeroGyro = true;
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	output.driveTrainSpeed = 0.2;
	output.driveTrainPidAngle = 0.0;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();
	return output;
    }

    public StateBase getNext(InputStateVariables input){
        if(super.getElapsedTime()>followTime){
	    return new FindWall();
	} else{
	    return this;
	}
    }
}

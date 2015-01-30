package states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class BoxSearch extends StateBase{
    @Override
    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.zeroGyro = true;
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	output.driveTrainSpeed = 0.2;
	output.driveTrainPidAngle = 360.0;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	//if(input.seesTarget){
	    //return new BoxFollow();
	//}else if{
	if(super.getElapsedTime()>3000.){
	    return new WallFollow();
	} else{
	    return this;
	}
    }
}

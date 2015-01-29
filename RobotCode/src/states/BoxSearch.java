package states;
import robot.*;

import static robot.Enums.*;

public class BoxSearch extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public BoxSearch(){
	super();

	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
	output.driveTrainPidType = DriveTrainPidType.GYROSCOPE;
	output.driveTrainSpeed = 0.5;

	output.sorterMethod = SorterMethod.DO_NOTHING;
	output.hopperMethod = HopperMethod.DO_NOTHING;
	output.conveyorMethod = ConveyorMethod.DO_NOTHING;

	stateStartTime = System.currentTimeMillis();
		
    }

    public OutputStateVariables run(InputStateVariables input){
		return output;
    }

    public StateBase getNext(InputStateVariables input){
	if(false){
	    return new BoxFollow();
	    //if(input.seesTarget){
	    //	return new BoxFollow();
	}else if(System.currentTimeMillis()-stateStartTime > 3000.){
	    return new WallFollow();
	}else if(input.gyroAngle > 360.0){
	    return new WallFollow();
	} else {
	    return this;
	}
    }
}

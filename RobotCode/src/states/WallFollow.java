package states;
import robot.*;

import static robot.Enums.*;

public class WallFollow extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public WallFollow(){
	super();
	output = new OutputStateVariables();
	output.driveTrainMethod = DriveTrainMethod.PID_DRIVE_TWO_INPUTS;
	output.driveTrainSpeed = 0.2;
	output.sorterMethod = SorterMethod.DO_NOTHING;
	output.hopperMethod = HopperMethod.DO_NOTHING;
	output.conveyorMethod = ConveyorMethod.DO_NOTHING;
    }

    public OutputStateVariables run(InputStateVariables input){
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	//if(input.seesTarget){
	if(false){
	    return new BoxFollow();
	}else if(System.currentTimeMillis()-stateStartTime > 4000.){
	    return new BoxSearch();
	}else if(input.frontUltraDist<0.1){
	    return new WallTurn();
	}else{
	    return this;
	}
    }
}

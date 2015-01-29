package states;
import robot.*;

import static robot.Enums.*;

public class WallTurn extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public WallTurn(){
	super();

	output = new OutputStateVariables();

	output.driveTrainMethod = DriveTrainMethod.SET_TURN_ROUGH;
	if(input.closerSide == CloserSide.LEFT){
	    output.driveTrainSpeed = 0.2;
	}else{
	    output.driveTrainSpeed = -0.2;
	}
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
	}else if(input.frontUltraDist>0.1){
	    return new WallFollow();
	}else{
	    return this;
	}
    }
}

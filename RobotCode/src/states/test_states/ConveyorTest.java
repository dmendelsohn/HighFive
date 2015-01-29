package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class ConveyorTest extends StateBase{
	@Override
	protected OutputStateVariables getDefaultOutput() {
		OutputStateVariables output = super.getDefaultOutput();
		output.conveyorMethod = ConveyorMethod.MOVE_BELT;
		output.conveyorSpeed = 0.1;		
		return output;
	}

    public OutputStateVariables run(InputStateVariables input){
		OutputStateVariables output = getDefaultOutput();
		if (getElapsedTime() < 25000){	
			//Default behavior, move belt
		}else{
		    output.conveyorMethod = ConveyorMethod.STOP_BELT;
		}
		return output;
    }

    public StateBase getNext(InputStateVariables input){
		return this;
    }
}

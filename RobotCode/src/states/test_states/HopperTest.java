package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class HopperTest extends StateBase{
	@Override
    public OutputStateVariables run(InputStateVariables input){
	    OutputStateVariables output = getDefaultOutput(); 
		if (getElapsedTime() < 5000){
	    	output.hopperMethod = HopperMethod.MOVE_LEFT;
	    	output.hopperLeftOpen = true;
		}else if (getElapsedTime() < 6000){
	    	output.hopperMethod = HopperMethod.MOVE_LEFT;
	    	output.hopperLeftOpen = false;
		}else if (getElapsedTime() < 7000){
	    	output.hopperMethod = HopperMethod.MOVE_RIGHT;
	    	output.hopperRightOpen = true;
		}else if (getElapsedTime() < 8000){
	    	output.hopperMethod = HopperMethod.MOVE_RIGHT;
	    	output.hopperRightOpen = false;
		}
		return output;
    }

    public StateBase getNext(InputStateVariables input){
		return this;
    }
}

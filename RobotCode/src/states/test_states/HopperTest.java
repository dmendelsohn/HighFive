package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class HopperTest extends StateBase{
    public HopperTest(){
		super();
	}

	@Override
    public OutputStateVariables run(InputStateVariables input){
		super.run(input); //Boilerplate
	    OutputStateVariables output = getDefaultOutput(); 
		if (elapsedTime<5000){
	    	output.hopperMethod = HopperMethod.MOVE_LEFT;
	    	output.hopperLeftOpen = true;
		}else if (elapsedTime<6000){
	    	output.hopperMethod = HopperMethod.MOVE_LEFT;
	    	output.hopperLeftOpen = false;
		}else if (elapsedTime<7000){
	    	output.hopperMethod = HopperMethod.MOVE_RIGHT;
	    	output.hopperRightOpen = true;
		}else if (elapsedTime<8000){
	    	output.hopperMethod = HopperMethod.MOVE_RIGHT;
	    	output.hopperRightOpen = false;
		}
		return output;
    }

    public StateBase getNext(InputStateVariables input){
		return this;
    }
}

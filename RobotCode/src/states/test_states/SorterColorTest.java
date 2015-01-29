package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class SorterColorTest extends StateBase{

    @Override
	public OutputStateVariables run(InputStateVariables input){
		super.run(input); //Boilerplate	
		OutputStateVariables output = getDefaultOutput();
		return output;  //Adding sorter logic happen in Robot.java, since it's common to all states
    }

    public StateBase getNext(InputStateVariables input){
		return this;
    }
}

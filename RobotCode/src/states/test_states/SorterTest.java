package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class SorterTest extends StateBase{
	@Override
	protected OutputStateVariables getDefaultOutput() {
		OutputStateVariables output = super.getDefaultOutput();
		output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
		output.sorterPosition = SorterPosition.MIDDLE;
		return output;
	}
		
    public OutputStateVariables run(InputStateVariables input){
		OutputStateVariables output = getDefaultOutput();	
		if (getElapsedTime() < 5000){	
			output.sorterPosition = SorterPosition.LEFT;
		} else if (getElapsedTime() < 6000){
			output.sorterPosition = SorterPosition.MIDDLE;
		} else if (getElapsedTime() < 7000){
			output.sorterPosition = SorterPosition.RIGHT;
		} else if (getElapsedTime() < 8000){
			output.sorterPosition = SorterPosition.MIDDLE;
		} 
		return output;
    }

    public StateBase getNext(InputStateVariables input){
		return this;
    }
}

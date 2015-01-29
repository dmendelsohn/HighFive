package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class SorterTest extends StateBase{
    public SorterTest(){
		super();
	}

	@Override
	protected OutputStateVariables getDefaultOutput() {
		OutputStateVariables output = super.getDefaultOutput();
		output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
		output.sorterPosition = SorterPosition.MIDDLE;
		return output;
	}
		
    public OutputStateVariables run(InputStateVariables input){
		super.run(input); //Boilerplate
		OutputStateVariables output = getDefaultOutput();	
		if (elapsedTime<5000){	
			output.sorterPosition = SorterPosition.LEFT;
		} else if (elapsedTime<6000){
			output.sorterPosition = SorterPosition.MIDDLE;
		} else if (elapsedTime<7000){
			output.sorterPosition = SorterPosition.RIGHT;
		} else if (elapsedTime<8000){
			output.sorterPosition = SorterPosition.MIDDLE;
		} 
		return output;
    }

    public StateBase getNext(InputStateVariables input){
		return this;
    }
}

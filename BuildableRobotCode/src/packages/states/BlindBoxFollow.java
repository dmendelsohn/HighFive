package packages.states;
import packages.*;

public class BlindBoxFollow extends StateBase{
	
	public OutputStateVariables output;

	public BlindBoxFollow(){

		output = new OutputStateVariables();

		output.drivetrainMethod = "moveStraight(.8,true)";
		output.hopperMethod = "setSorterPosition(0)";
		output.conveyorMethod = "checkFeedLimitSwitch()";
		output.visionMethod = "senseTarget()";
		
	}

	public OutputStateVariables run(InputStateVariables input){
		
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if(input.outerFeedLimitEngaged){
			return new ForwardCapture();
		} else if(System.currentTimeMillis()-stateStartTime > 4000.){
			return new BoxSearch();
		} else if(input.seesTarget){
			return new BoxFollow();
		} else {
			return this;
		}
	}
}


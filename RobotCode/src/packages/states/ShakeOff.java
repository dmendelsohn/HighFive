package packages.states;
import packages.*;

public class ShakeOff extends StateBase{
	
	public OutputStateVariables output;

	public ShakeOff(){

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
		if(System.currentTimeMillis()-stateStartTime > 2000.){
			return new BoxSearch();
		} 
	}
}


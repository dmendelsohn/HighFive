package states;
import robot.*;


public class ForwardCapture extends StateBase{

	public OutputStateVariables output;

	public ForwardCapture(){

		output = new OutputStateVariables();

		output.drivetrainMethod = "moveStraight(.3,true)";
		output.hopperMethod = "setSorterPosition(0)";
		output.conveyorMethod = "engageLock()";
		output.visionMethod = "doNothing()";
		
	}

	public OutputStateVariables run(InputStateVariables input){
		
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		
		if(input.innerFeedLimitEngaged){
			return new ConveyorCapture();
		} else if(System.currentTimeMillis()-stateStartTime > 2500.){
			return new ShakeOff();
		} else {
			return this;
		}
	}
}

package states;
import robot.*;

public class ConveyorCapture extends StateBase{

	public OutputStateVariables output;

	public ConveyorCapture(){

		output = new OutputStateVariables();

		output.drivetrainMethod = "moveStraight(.3,true)";
		output.hopperMethod = "setSorterPosition(0)";
		output.conveyorMethod = "engageLock()";
		output.visionMethod = "senseTarget()";
		
	}

	public OutputStateVariables run(InputStateVariables input){
		
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if(System.currentTimeMillis()-stateStartTime > 500.){
			return new BoxSearch();
		} else{
			return this;
		}
	}
}

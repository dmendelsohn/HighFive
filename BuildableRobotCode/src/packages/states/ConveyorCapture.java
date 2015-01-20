package packages.states;
import packages.*;

public class ConveyorCapture extends StateBase{

	public OutputStateVariables output;

	public ConveyorCapture(){

		output = new OutputStateVariables();

		output.drivetrainMethod = "moveStraight(0,true)";
		output.hopperMethod = "setSorterPosition(0)";
		output.conveyorMethod = "encoderMoveBelt()";
		output.visionMethod = "doNothing()";
		
	}

	public OutputStateVariables run(InputStateVariables input){
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if (input.conveyorLifted){
			return new Sort();
		}else if(System.currentTimeMillis()-stateStartTime > 2500.){ 
			return new BoxSearch();
		}else{	
			return this;
		}
	}
}

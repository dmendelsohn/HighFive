package packages.states;
import packages.*;

public class ShakeOff extends StateBase{
	
	public OutputStateVariables output;
	public int counter;
	public boolean flag;

	public ShakeOff(){

		output = new OutputStateVariables();

		output.drivetrainMethod = "moveStraight(.8,false)";
		output.hopperMethod = "setSorterPosition(0)";
		output.conveyorMethod = "disengageLock()";
		output.visionMethod = "doNothing()";

	}

	public OutputStateVariables run(InputStateVariables input){
		output.drivetrainMethod = "moveStraight(0.8,false)";
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if(System.currentTimeMillis()-stateStartTime > 3000.){
			return new BoxSearch();
		} else {
			return this;
		}
	}
}


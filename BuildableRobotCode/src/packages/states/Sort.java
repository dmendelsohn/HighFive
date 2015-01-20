package packages.states;
import packages.*;

public class Sort extends StateBase{
	
	public OutputStateVariables output;

	public Sort(){

		output = new OutputStateVariables();

		output.drivetrainMethod = "moveStraight(.8,true)";
		output.hopperMethod = "setSorterPosition(0)";
		output.conveyorMethod = "doNothing()";
		output.visionMethod = "senseTarget()";
		
	}

	public OutputStateVariables run(InputStateVariables input){
		if (input.boxColor == "Red"){
			output.hopperMethod = "setSorterPosition(1)";
		} else if (input.boxColor == "Green"){
			output.hopperMethod = "setSorterPosition(-1)";
		}
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if(System.currentTimeMillis()-stateStartTime > 1000.){
			return new BoxSearch();
		} else {
			return this;
		}
	}
}


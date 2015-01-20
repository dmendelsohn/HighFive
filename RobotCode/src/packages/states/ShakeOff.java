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
		output.conveyorMethod = "checkFeedLimitSwitch()";
		output.visionMethod = "doNothing()";
		
		counter = 0;
	}

	public OutputStateVariables run(InputStateVariables input){
		if(counter%100<50){
			output.drivetrainMethod = "moveStraight(0.8,true)";
		}else{
			output.drivetrainMethod = "moveStraight(0.8,false)";
		}
		counter++;
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if(System.currentTimeMillis()-stateStartTime > 2000.){
			return new BoxSearch();
		} else {
			return this;
		}
	}
}


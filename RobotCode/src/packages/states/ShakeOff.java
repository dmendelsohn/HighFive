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
			moveStraight(.8, false);
		}else{
			moveStraight(.8, true);
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


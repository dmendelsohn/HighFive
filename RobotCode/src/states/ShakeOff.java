package states;
import robot.*;


public class ShakeOff extends StateBase{
	
	public OutputStateVariables output;
	public int counter;
	public boolean flag;

	public ShakeOff(){
		super();

		output = new OutputStateVariables();

		output.drivetrainMethod = "moveStraightRough";
		output.drivetrainSpeed = -0.2;

		output.hopperMethod = "doNothing";
		output.conveyorMethod = "doNothing";
		output.visionMethod = "doNothing";
	}

	public OutputStateVariables run(InputStateVariables input){
		System.out.println("ShakeOff");
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


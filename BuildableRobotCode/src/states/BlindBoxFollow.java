package states;
import robot.*;

public class BlindBoxFollow extends StateBase{
	
	public OutputStateVariables output;

	public BlindBoxFollow(){
		super();
		output = new OutputStateVariables();

		output.drivetrainMethod = "pidDrive";
		output.drivetrainMethodVals.add(0.5);
		//output.drivetrainMethodVals.add(input.gyroAngle);
		output.drivetrainMethodVals.add(10.0);
		output.drivetrainMethodVals.add(-1.0/20.0);
		output.drivetrainMethodVals.add(0);
		output.drivetrainMethodVals.add(0);

		output.hopperMethod = "doNothing";
		output.conveyorMethod = "doNothing";

		output.visionMethod = "senseTarget";
		
		stateStartTime = System.currentTimeMillis();
	}

	public OutputStateVariables run(InputStateVariables input){
		System.out.println("BlindWallFollow");
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		//if(input.feedLimitEngaged){
		if(false){
			return new ConveyorCapture();
		} else if(System.currentTimeMillis()-stateStartTime>4000.){
			return new BoxSearch();
		//} else if(input.seesTarget){
		}else if (false){
			return new BoxFollow();
		} else {
			return this;
		}
	}
}


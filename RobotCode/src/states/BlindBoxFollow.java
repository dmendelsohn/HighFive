package states;
import robot.*;

public class BlindBoxFollow extends StateBase{
	
	public OutputStateVariables output;

	public BlindBoxFollow(){
		super();
		output = new OutputStateVariables();

		output.drivetrainMethod = "pidDrive";
		output.drivetrainPIDType = "Gyroscope";
		output.drivetrainSpeed = 0.4;

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


package states;
import robot.*;

public class WallFollow extends StateBase{

	public OutputStateVariables output;
	public InputStateVariables input;

	public WallFollow(){
		super();

		output = new OutputStateVariables();

		output.drivetrainMethod = "pidDrive";
		output.drivetrainPIDType = "Ultrasonic";
		output.drivetrainSpeed = 0.5;

		output.hopperMethod = "doNothing";
		output.conveyorMethod = "doNothing";

		output.visionMethod = "senseTarget";
		
	}

	public OutputStateVariables run(InputStateVariables input){
		System.out.println("WallFollow");
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		//if(input.seesTarget){
		if(false){
			return new BoxFollow();
		}else if(System.currentTimeMillis()-stateStartTime > 4000.){
			return new BoxSearch();
		} else {
			return this;
		}
	}
}

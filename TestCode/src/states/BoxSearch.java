package states;
import robot.*;


public class BoxSearch extends StateBase{

	public OutputStateVariables output;
	public InputStateVariables input;

	public BoxSearch(){
		super();

		output = new OutputStateVariables();

		output.drivetrainMethod = "pidDrive";
		output.drivetrainPIDType = "Gyroscope";
		output.drivetrainSpeed = 0.5;

		output.hopperMethod = "doNothing";

		output.conveyorMethod = "doNothing";

		output.visionMethod = "senseTarget";

		stateStartTime = System.currentTimeMillis();
		
	}

	public OutputStateVariables run(InputStateVariables input){
		System.out.println("BoxSearch");
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if(false){
			return new BoxFollow();
		//if(input.seesTarget){
		//	return new BoxFollow();
		}else if(System.currentTimeMillis()-stateStartTime > 4000.){
			return new WallFollow();
		}else if(input.gyroAngle > 360.0){
			return new WallFollow();
		} else {
			return this;
		}
	}

}

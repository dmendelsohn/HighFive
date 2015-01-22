package states;
import robot.*;

public class ConveyorCapture extends StateBase{

	public OutputStateVariables output;
	public InputStateVariables input;

	public ConveyorCapture(){
		super();

		output = new OutputStateVariables();

		output.drivetrainMethod = "pidDrive";	
		output.drivetrainPIDType = "Gyroscope";
		output.drivetrainSpeed = 0.4;

		output.hopperMethod = "setSorterPosition";
		output.hopperPosition = 0;

		output.conveyorMethod = "moveBelt";
		output.conveyorSpeed = 0.5;

		output.visionMethod = "senseTarget";
		
	}

	public OutputStateVariables run(InputStateVariables input){
		System.out.println("ConveyorCapture");
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if(System.currentTimeMillis()-stateStartTime > 2000.){
			return new BoxSearch();
		} else{
			return this;
		}
	}
}

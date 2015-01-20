package states;
import robot.*;

public class ConveyorCapture extends StateBase{

	public OutputStateVariables output;
	public InputStateVariables input;

	public ConveyorCapture(){
		super();

		output = new OutputStateVariables();

		output.drivetrainMethod = "pidDrive";
		output.drivetrainMethodVals.add(0.5);
		//output.drivetrainMethodVals.add(input.gyroAngle);
		output.drivetrainMethodVals.add(10.0);
		output.drivetrainMethodVals.add(-1.0/20.0);
		output.drivetrainMethodVals.add(0);
		output.drivetrainMethodVals.add(0);

		output.hopperMethod = "setSorterPosition";
		output.hopperMethodVals.add(0);

		output.conveyorMethod = "moveBelt";
		output.conveyorMethodVals.add(.5);

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

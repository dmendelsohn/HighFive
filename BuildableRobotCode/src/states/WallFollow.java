package states;
import robot.*;

public class WallFollow extends StateBase{

	public OutputStateVariables output;
	public InputStateVariables input;

	public WallFollow(){
		super();

		output = new OutputStateVariables();

		output.drivetrainMethod = "pidDrive";
		output.drivetrainMethodVals.add(2.0);
		output.drivetrainMethodVals.add(0.7);
		//output.drivetrainMethodVals.add(input.rightUltraDist);
		output.drivetrainMethodVals.add(10.0);
		output.drivetrainMethodVals.add(-1.0/50.0);
		output.drivetrainMethodVals.add(0);
		output.drivetrainMethodVals.add(0);

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

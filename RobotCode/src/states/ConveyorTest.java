package states;
import robot.*;


public class ConveyorTest extends StateBase{

	public OutputStateVariables output;
	public InputStateVariables input;

	public ConveyorTest(){

		super();

		output = new OutputStateVariables();

		output.drivetrainMethod = "doNothing";
		
		output.conveyorMethod = "moveBelt";
		output.conveyorSpeed = 0.3;

		output.hopperMethod = "doNothing";
		output.visionMethod = "doNothing";

		stateStartTime = System.currentTimeMillis();
		
	}

	public OutputStateVariables run(InputStateVariables input){

		System.out.println("DriveTrainTest");
		long elapsedTime = System.currentTimeMillis()-stateStartTime;
		
		if (elapsedTime<2000){	
		    output.conveyorMethod = "moveBelt";
		    output.conveyorSpeed = 0.3;
		}else if (elapsedTime<2100){
		    output.drivetrainMethod = "stopBelt";
		}
		
		System.out.println(output.drivetrainMethod);
		return output;
	}

	public StateBase getNext(InputStateVariables input){
	        return this;
	}

}

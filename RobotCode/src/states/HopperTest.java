package states;
import robot.*;


public class DriveTrainTest extends StateBase{

	public OutputStateVariables output;
	public InputStateVariables input;

	public DriveTrainTest(){

		super();

		output = new OutputStateVariables();

		output.drivetrainMethod = "moveStraightRough";
		output.drivetrainSpeed = 0.3;

		output.conveyorMethod = "doNothing";
		output.hopperMethod = "doNothing";
		output.visionMethod = "doNothing";

		output.zeroGyro = true;

		stateStartTime = System.currentTimeMillis();
		
	}

	public OutputStateVariables run(InputStateVariables input){
		output.zeroGyro = false;

		System.out.println("DriveTrainTest");
		long elapsedTime = System.currentTimeMillis()-stateStartTime;
		
		if (elapsedTime<500){	
		    output.hopperMethod = "setSorterPosition";
		    output.hopperPosition = -1;
		}else if (elapsedTime<1000){
		    output.hopperMethod = "setSorterPosition";
		    output.hopperPosition = 0;
		}else if (elapsedTime<1400){
		    output.hopperMethod = "setSorterPosition";
		    output.hopperPosition = 1;
		}else if (elapsedTime<1500){
		    output.hopperMethod = "setSorterPosition";
		    output.hopperPosition = 0;
		}else if (elapsedTime<1750){
		    output.hopperMethod = "openLeftHatch";
		    output.leftHatchOpen = true;
		}else if (elapsedTime<2000){
		    output.hopperMethod = "openLeftHatch";
		    output.leftHatchOpen = false;
		}else if (elapsedTime<2250){
		    output.hopperMethod = "openRightHatch";
		    output.leftHatchOpen = true;
		}else if (elapsedTime<2500){
		    output.hopperMethod = "openRightHatch";
		    output.leftHatchOpen = false;
		}
	     
		System.out.println(output.hopperMethod);
		return output;
	}

	public StateBase getNext(InputStateVariables input){
	        return this;
	}

}

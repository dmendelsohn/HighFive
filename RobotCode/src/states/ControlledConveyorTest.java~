package states;
import robot.*;


public class ControlledConveyorTest extends StateBase{

	public OutputStateVariables output;
	public InputStateVariables input;

	public ControlledConveyorTest(){

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

		System.out.println("ControlledConveyorTest");
		long elapsedTime = System.currentTimeMillis()-stateStartTime;
		

		if(elapsedTime<15000){
			if (input.conveyorEncoderCount % 500.0 > 5.0){	
			    output.conveyorMethod = "moveBelt";
			    output.conveyorSpeed = 0.3;
			}else{
			    output.conveyorMethod = "stopBelt";
			    try{
				 Thread.sleep(10);
		            } catch(Exception e){
				    //IDC
			    }
			    output.conveyorSpeed = 0.3;
			    try{
				 Thread.sleep(10);
		            } catch(Exception e){
				    //IDC
			    }
			}
		}else{
			output.conveyorMethod = "stopBelt";
		}
			
		System.out.println(output.conveyorMethod);
		return output;
	}

	public StateBase getNext(InputStateVariables input){
	        return this;
	}

}

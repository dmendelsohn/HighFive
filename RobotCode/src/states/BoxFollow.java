package states;
import robot.*;

public class BoxFollow extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;
 
    public double drive_adjustment;
    public double left_drive_speed;
    public double right_drive_speed;

    public BoxFollow(){
	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "pidDrive";
	output.drivetrainPIDType = "Gyroscope";
	output.drivetrainSpeed = 0.3;
	
	output.sorterMethod = "doNothing";

	output.hopperMethod = "doNothing";
	
	output.conveyorMethod = "doNothing";

	output.visionMethod = "howCentered";
		
    }

    public OutputStateVariables run(InputStateVariables input){
	System.out.println("BoxFollow");
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	//if(input.feedLimitEngaged){
/*	if(false){
	    return new ConveyorCapture();
	}else if(System.currentTimeMillis()-stateStartTime > 1500.){
	    return new BlindBoxFollow();
	} else{
	    return this;
	}*/
		return this;
    }
}

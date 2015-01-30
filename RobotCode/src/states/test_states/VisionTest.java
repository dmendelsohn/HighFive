package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class VisionTest extends StateBase{

    double howCentered = 0, boxDistance = -1;
    long lastCenter = 0;

    public OutputStateVariables getDefaultOutput() {
	OutputStateVariables output = super.getDefaultOutput();
	output.driveTrainSpeed = 0;
	return output;
    }

    public OutputStateVariables run(InputStateVariables input){
	OutputStateVariables output = getDefaultOutput();
	if(Math.abs(howCentered-input.howCentered) > 0.1 || Math.abs(boxDistance - input.boxDistance) > 0.01){
	    howCentered = input.howCentered;
	    boxDistance = input.boxDistance;
	    System.out.println("sees target: " + input.seesTarget + "  howCentered: " + input.howCentered + "  distance: " + input.boxDistance);
	}
	if(!input.seesTarget){
	    output.driveTrainMethod = DriveTrainMethod.STOP;
	}else{
	    output.driveTrainMethod = DriveTrainMethod.VISION_TURN;
	    if(System.currentTimeMillis()-lastCenter > 2000){
		lastCenter = System.currentTimeMillis();
	    }
	}
	return output;
    }

    public StateBase getNext(InputStateVariables input){
	return this;
    }
}

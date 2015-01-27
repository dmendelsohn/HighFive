package states;
import robot.*;

public class WallFollow extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public WallFollow(){

	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "pidDriveTwoInputs";
	output.drivetrainSpeed = 0.2;
		
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
	}else if(input.frontUltraDist<0.1){
	    return new WallTurn();
	}else{
	    return this;
	}
    }
}

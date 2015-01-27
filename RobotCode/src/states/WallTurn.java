package states;
import robot.*;

public class WallTurn extends StateBase{

    public OutputStateVariables output;
    public InputStateVariables input;

    public WallTurn(){
	super();

	output = new OutputStateVariables();

	output.drivetrainMethod = "setTurnRough";
	if(input.closerSide.equals("left")){
	    output.drivetrainSpeed = 0.2;
	}else{
	    output.drivetrainSpeed = -0.2;
	}
		
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
	}else if(input.frontUltraDist>0.1){
	    return new WallFollow();
	}else{
	    return this;
	}
    }
}

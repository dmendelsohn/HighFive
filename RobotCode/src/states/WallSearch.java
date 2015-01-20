package states;
import robot.*;

public class WallSearch extends StateBase{

	public OutputStateVariables output;

	public WallSearch(){

		output = new OutputStateVariables();

		output.drivetrainMethod = "setClockwiseTurn(.5)";
		output.hopperMethod = "setSorterPosition(0)";
		output.conveyorMethod = "moveBelt(false)";
		output.visionMethod = "senseTarget()";
		
	}

	public OutputStateVariables run(){
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if(System.currentTimeMillis()-stateStartTime > 4000. ){
			return new BoxFollow();
		} else {
			return this;
		}
	}
}

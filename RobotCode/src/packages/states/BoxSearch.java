package packages.states;
import packages.*;

public class BoxSearch extends StateBase{

	public OutputStateVariables output;

	public BoxSearch(){
		output = new OutputStateVariables();

		output.drivetrainOutputMethod = "setClockwiseTurn(.5)";
		output.hopperOutputMethod = "setSorterPosition(0)";
		output.conveyorOutputMethod = "moveBelt(false)";
		output.visionOutputMethod = "senseTarget()";
		
	}

	public OutputStateVariables run(){
		return output;
	}

	public StateBase getNext(InputStateVariables input){
		if(System.currentTimeMillis()-stateStartTime > 5000. ){
			return new BoxFollow();
		} else{
			return this;
		}
	}
}

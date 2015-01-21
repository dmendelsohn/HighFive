package states;
import robot.*;


public class StateBase{
	public long stateStartTime;

	public StateBase(){	
		stateStartTime = System.currentTimeMillis();
	}

	public OutputStateVariables run(InputStateVariables input){
		//this gets over-written by which ever state is being called
		return new OutputStateVariables();
	}

	public StateBase getNext(InputStateVariables input){
		//this also gets over-written by which ever state is being called
		return this;
	}

		
}

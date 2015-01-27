package states;
import robot.*;


public abstract class StateBase{
	public long stateStartTime;

	public StateBase(){	
		stateStartTime = System.currentTimeMillis();
	}

	public abstract OutputStateVariables run(InputStateVariables input);

	public abstract StateBase getNext(InputStateVariables input);
		
}

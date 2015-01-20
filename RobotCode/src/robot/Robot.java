package robot;
import subsystems.*;
import states.*;


public class Robot{

	public StateBase state;

	public long startTime;

	public Robot(){

		startTime = System.currentTimeMillis();

		state = new BoxSearch();

	}

	public static void main(String[] args){
		Robot robot = new Robot();

		InstantiatedSystems systems;
		systems = robot.startSystems();

		//implement state-system functionality here
		InputStateVariables input;
		OutputStateVariables output;

		for (int i = 1; i<2; i++){
			input = robot.generateInputStateVariables(systems);
			robot.setState(input);
			output = robot.readState(input);
			robot.processOutput(output);
		}

	}

        public void setState(InputStateVariables input){
	    state = state.getNext(input);
        }

        public OutputStateVariables readState(InputStateVariables input){
	    //use state to determine output state variables
	    return state.run(input); 
        }
		
	public void processOutput (OutputStateVariables output){
		
	}
	public InstantiatedSystems startSystems(){
		return new InstantiatedSystems();
	}
	public InputStateVariables generateInputStateVariables(InstantiatedSystems systems){
		//get these from robot
		return new InputStateVariables(systems);
	}
}

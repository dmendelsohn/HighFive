package packages;
import packages.subsystems.*;
import packages.states.*;
import packages.*;

public class Robot{

	static{System.loadLibrary("jmraa");} 

	public StateBase state;

	public DriveTrain drivetrain;
	public Hoppers hopper;
	public ConveyorBelt conveyor;
	public Vision vision;

	public long startTime;

	public Robot(){

		startTime = System.currentTimeMillis();

		state = new BoxSearch();

		drivetrain = new DriveTrain();
		hopper = new Hoppers();
		conveyor = new ConveyorBelt();
		vision = new Vision();

	}

	public static void main(String[] args){
		Robot robot = new Robot();

		//implement state-system functionality here
		InputStateVariables inputstatevariables;
		OutputStateVariables outputstatevariables;

		for (int i = 1; i<2; i++){
			inputstatevariables = robot.generateInputStateVariables();
			robot.setState(inputstatevariables);
			outputstatevariables = robot.readState(inputstatevariables);
			robot.processOutput(outputstatevariables);
		}

	}

        public void setState(InputStateVariables inputstatevariables){
	    state = state.getNext(inputstatevariables);
        }

        public OutputStateVariables readState(InputStateVariables input){
	    //use state to determine output state variables
	    return state.run(input); 
        }
		
	public void processOutput (OutputStateVariables outputstatevariables){
		
	}

	public InputStateVariables generateInputStateVariables(){
		//get these from robot
		return new InputStateVariables();
	}
}

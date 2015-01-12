package packages;
import packages.subsystems.*;
import packages.states.*;
import packages.*;

public class Robot{

	public StateBase state;

	public DriveTrain drivetrain;
	public Hoppers hopper;
	public ConveyorBelt conveyor;
	public Vision vision;

	public Robot(){

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
			outputstatevariables = robot.readState();
			robot.processOutput(outputstatevariables);
		}

	}

        public void setState(InputStateVariables inputstatevariables){
	    state = state.getNext(inputstatevariables);
        }

        public OutputStateVariables readState(){
	    //use state to determine output state variables
	    return state.run(); 
        }
		
	public void processOutput (OutputStateVariables outputstatevariables){
		
	}

	public InputStateVariables generateInputStateVariables(){
		//get these from robot
		return new InputStateVariables();
	}
}

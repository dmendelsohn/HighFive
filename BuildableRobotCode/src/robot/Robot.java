package robot;
import subsystems.*;
import states.*;
import java.util.ArrayList;
//import jmraa.Utils;


public class Robot{

//	static{System.loadLibrary("jmraa");}

	public StateBase state;

	public static long runTime;

	public Robot(){

		runTime = System.currentTimeMillis();
		 
		state = new BoxSearch();
		

	}

	public static void main(String[] args){
		Robot robot = new Robot();

		InstantiatedSystems systems;
		systems = robot.startSystems();
		
		//implement state-system functionality here
		InputStateVariables input;
		OutputStateVariables output;

		//time = System.currentTimeMillis();

		//for (int i = 1; i<1000; i++){
		while(System.currentTimeMillis()-runTime<5000.){
			input = robot.generateInputStateVariables(systems);
			robot.setState(input);
			output = robot.readState(input);
			robot.processOutput(output, systems);
			//Utils.msleep(10);
			try{
				Thread.sleep(10);
			}catch(Exception e){
				//w/e
			}
		}

	}

        public void setState(InputStateVariables input){
	    state = state.getNext(input);
        }

        public OutputStateVariables readState(InputStateVariables input){
	    //use state to determine output state variables
	    return state.run(input); 
        }
		
	public InstantiatedSystems startSystems(){
		return new InstantiatedSystems();
	}
	public InputStateVariables generateInputStateVariables(InstantiatedSystems systems){
		return new InputStateVariables(systems);
	}

	public void processOutput (OutputStateVariables output, InstantiatedSystems systems){
		System.out.println(output.drivetrainMethod);

		ArrayList<Object> driveList = output.drivetrainMethodVals;
		ArrayList<Object> conveyorList = output.conveyorMethodVals;
		ArrayList<Object> hopperList = output.hopperMethodVals;
		ArrayList<Object> visionList = output.visionMethodVals;
		/*
		switch(output.drivetrainMethod){
			
			case "pidDrive":
				systems.drivetrain.pidDrive(driveList.get(0), driveList.get(1), driveList.get(2), driveList.get(3), driveList.get(4), driveList.get(5), driveList.get(6));
				break;
			case "moveStraightRough":
				systems.drivetrain.moveStraightRough(driveList.get(0), driveList.get(1));
				break;
			case "setTurnRough":
				systems.drivetrain.setTurnRough(driveList.get(0), driveList.get(1));
				break;
			case "setLeftSpeed":
				systems.drivetrain.setLeftSpeed(driveList.get(0), driveList.get(1));
				break;
			case "setRightSpeed":
				systems.drivetrain.setRightSpeed(driveList.get(0), driveList.get(1));
				break;
			case "stop":
				systems.drivetrain.stop();
				break;
			case "doNothing":
				systems.drivetrain.doNothing();
				break;
		}	
		switch(output.conveyorMethod){

			case "moveBelt":
				systems.conveyor.moveBelt(conveyorList.get(0));
				break;
			case "stopBelt":
				systems.conveyor.stopBelt();
				break;
			case "doNothing":
				systems.conveyor.doNothing();
				break;
		}		
		*/
	}			

}

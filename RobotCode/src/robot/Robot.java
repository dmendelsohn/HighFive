package robot;
import subsystems.*;
import states.*;
import states.test_states.*;
import jmraa.Utils;

import static robot.Enums.*;

public class Robot{
    static{System.loadLibrary("jmraa");}

    public StateBase state;
    public static long runTime;
    public InstantiatedSystems systems;
    public RobotLogger logger;

    public boolean inEnemyZone;

    public Robot(){
		runTime = System.currentTimeMillis();
		state = new SorterColorTest();
		systems = new InstantiatedSystems();
		logger = new RobotLogger();
		inEnemyZone = false;
    }

    public Robot(StateBase startingState) {
		runTime = System.currentTimeMillis();
		state = startingState;
		systems = new InstantiatedSystems();
		logger = new RobotLogger();
		inEnemyZone = false;
    }

    public static void main(String[] args){
	Robot robot;
	if (args.length == 0) {
	    robot = new Robot();
	} else {
	    //Use first argument to determine test type
	    StateBase startState = new ManualTest(); //This is to get Java to compile, it will be overwritten...
	    try {
		startState = (StateBase)Class.forName(args[0]).newInstance();
	    } catch (Exception e) {
		e.printStackTrace();	
		System.out.println("Invalid Start State: " + args[0]);
		System.exit(0);
	    }
	    robot = new Robot(startState);
	}
	robot.addShutdown();
		
	//implement state-system functionality here
	InputStateVariables input;
	OutputStateVariables output;
		
	while(System.currentTimeMillis()-runTime<3000000.){
	    input = robot.generateInputStateVariables();
	    robot.setState(input);
	    output = robot.readState(input);
	    robot.addPassiveOutputs(output, input);
	    robot.processOutput(output, input);

	    //Logging
	    robot.getLogger().logInputs(input);
	    robot.getLogger().logState(robot.getState());
	    robot.getLogger().logOutputs(output);
			
	    Utils.msleep(10);
	}
    }

    public StateBase getState() {
	return state;
    }

    public RobotLogger getLogger() {
	return logger;
    }

    public void addShutdown(){
	Runtime.getRuntime().addShutdownHook(
					     new Thread() {
						 public void run() {
						     systems.kill();
						     Utils.msleep(100);
						     systems.kill();
						 }
					     });
    }


    public void setState(InputStateVariables input){
		state = state.getNext(input);
    }

    public OutputStateVariables readState(InputStateVariables input){
		//use state to determine output state variables
		return state.run(input); 
    }

    public InputStateVariables generateInputStateVariables(){
		return new InputStateVariables(systems);
    }

    public void processOutput (OutputStateVariables output, InputStateVariables input){
		if(output.zeroGyro){
			systems.zeroGyro();
		}
		switch(output.drivetrainMethod){
			case "pidDrive":
				systems.drivetrain.pidDrive(0, output.drivetrainSpeed, input.gyroAngle, RobotMap.KP_PID_DRIVE, RobotMap.KI_PID_DRIVE, RobotMap.KD_PID_DRIVE);
				break;
			case "pidDriveTwoInputs":
				if (input.closerSide.equals("left")){
					systems.drivetrain.pidDriveTwoInputs("left", 0.5,
						     output.drivetrainSpeed, input.leftBackUltraDist, input.leftFrontUltraDist,
						     RobotMap.KP_DOUBLE_PID_DRIVE, RobotMap.KI_DOUBLE_PID_DRIVE, RobotMap.KD_DOUBLE_PID_DRIVE);
				}else{
					systems.drivetrain.pidDriveTwoInputs("right", 0.5, 
						     output.drivetrainSpeed, input.rightBackUltraDist, input.rightFrontUltraDist, 
						     RobotMap.KP_DOUBLE_PID_DRIVE, RobotMap.KI_DOUBLE_PID_DRIVE, RobotMap.KD_DOUBLE_PID_DRIVE);
				}
				break;
			case "moveStraightRough":
				systems.drivetrain.moveStraightRough(output.drivetrainSpeed);	
				break;
			case "setTurnRough":
				systems.drivetrain.setTurnRough(output.drivetrainSpeed);
				break;
			case "setLeftSpeed":
				systems.drivetrain.setLeftSpeed(output.drivetrainSpeed);
				break;
			case "setRightSpeed":
				systems.drivetrain.setRightSpeed(output.drivetrainSpeed);
				break;
			case "stop":
				systems.drivetrain.stop();
				break;
			case "doNothing":
				systems.drivetrain.doNothing();
				break;
		}
		
	switch(output.hopperMethod) {
	case MOVE_BOTH:
		System.out.println("Move both hoppers, (left right) = " + output.hopperLeftOpen + " " + output.hopperRightOpen);
	    systems.hopper.setBoth(output.hopperLeftOpen, output.hopperRightOpen);
	    break;
	case MOVE_LEFT:
		System.out.println("Move left hopper, open = " + output.hopperLeftOpen);
	    systems.hopper.setLeft(output.hopperLeftOpen);
	    //true opens hatch
	    break;
	case MOVE_RIGHT:
		System.out.println("Move right hopper, open = " + output.hopperRightOpen);
	    systems.hopper.setRight(output.hopperRightOpen);
	    //true opens hatch				
	    break;
	case DO_NOTHING:
	    systems.hopper.doNothing();
	    break;
	}	

	switch(output.sorterMethod){
	case SET_SORTER_POSITION:
		System.out.println("Setting sorter position to: " + output.sorterPosition.name());
	    systems.sorter.setSorterPosition(output.sorterPosition);
	    break;
	case DO_NOTHING:
	    systems.sorter.doNothing();
	    break;
	}
	
	switch(output.conveyorMethod){
	case MOVE_BELT:
	    systems.conveyor.moveBelt(output.conveyorSpeed);
	    break;
	case STOP_BELT:
	    systems.conveyor.stopBelt();
	    break;
	case DO_NOTHING:
	    systems.conveyor.doNothing();
	    break;
	}
    }

	private void addPassiveOutputs(OutputStateVariables output, InputStateVariables input) {
		//Sorting
		double analogReading = input.photoReading;
		systems.sorter.addDataPoint(analogReading);
		if (RobotMap.AUTO_SORT) {
			if (systems.sorter.hasColorStreak()) {
					output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
				BlockColor color = systems.sorter.getLastColor(); //Color of block to be sorted, can be NONE
				output.sorterPosition = systems.sorter.getSorterPositionForColor(color);  //Which side the sorter should move to (or middle)
			} else {
				output.sorterMethod = SorterMethod.DO_NOTHING;
			}
		}

		//TODO: Line Following
	}
}

package robot;
import subsystems.*;
import states.*;
import states.test_states.*;
import jmraa.Utils;


public class Robot{
    static{System.loadLibrary("jmraa");}

    public StateBase state;
    public static long runTime;
    public InstantiatedSystems systems;
    public RobotLogger logger;

    public Robot(){
	runTime = System.currentTimeMillis();
	state = new SorterColorTest();
	systems = new InstantiatedSystems();
	logger = new RobotLogger();
    }

    public Robot(StateBase startingState) {
	runTime = System.currentTimeMillis();
	state = startingState;
	systems = new InstantiatedSystems();
	logger = new RobotLogger();
    }

    public static void main(String[] args){
	Robot robot;
	if (args.length == 0) {
	    robot = new Robot();
	} else {
	    //Use first argument to determine test type
	    StateBase startState;
	    switch (args[0]) {
		/*case "Main":
		startState = new BoxSearch();
		break;*/
	    case "SorterColorTest":
		startState = new SorterColorTest();
		break;
	    case "SorterTest":
		startState = new SorterTest();
		break;
	    case "HopperTest":
		startState = new HopperTest();
		break;
	    case "ConveyorTest":
		startState = new ConveyorTest();
		break;
	    case "CaptureTest":
		startState = new CaptureTest();
		break;
	    case "VisionTest":
		startState = new VisionTest();
		break;
	    case "WallFollowTest":
		startState = new WallFollowTest();
		break;
	    case "DriveTrainTest":
		startState = new DriveTrainTest();
		break;
		/*case "ManualTest":
		startState = new ManualTest();
		break;*/
	    default:
		startState = new HopperTest(); //This is a nonsense line to get stuff to compile
		System.out.println("Invalid Start State");
		System.exit(0);
		break;
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
	    robot.processOutput(output, input);
			
	    //Logging
	    robot.getLogger().logInputs(input);
	    robot.getLogger().logState(robot.getState());
	    robot.getLogger().logOutputs(output);
			
	    Utils.msleep(10);
	}
    }

    public RobotLogger getLogger() {
	return logger;
    }

    public StateBase getState() {
	return state;
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
	    systems.drivetrain.pidDriveTwoInputs(500, output.drivetrainSpeed, input.rightBackIRDist, input.rightFrontIRDist, RobotMap.KP_DOUBLE_PID_DRIVE, RobotMap.KI_DOUBLE_PID_DRIVE, RobotMap.KD_DOUBLE_PID_DRIVE);
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
		
	switch(output.hopperMethod){
	case "hopperOpenBoth":
	    systems.hopper.hopperOpenBoth(output.hopperOpenLeft, output.hopperOpenRight);
	    break;
	case "hopperOpenLeft":
	    systems.hopper.hopperOpenLeft(output.hopperOpenLeft);
	    //true opens hatch
	    break;
	case "hopperOpenRight":
	    systems.hopper.hopperOpenRight(output.hopperOpenRight);
	    //true opens hatch				
	    break;
	case "doNothing":
	    systems.hopper.doNothing();
	    break;
	}	

	switch(output.sorterMethod){
	case "setSorterPosition":
	    systems.sorter.setSorterPosition(output.sorterPosition);
	    break;
	case "setSorterPositionColor":
	    systems.sorter.setSorterPositionColor(input.photoState);
	    break;
	case "doNothing":
	    systems.sorter.doNothing();
	    break;
	}
	
	switch(output.conveyorMethod){
	case "moveBelt":
	    systems.conveyor.moveBelt(output.conveyorSpeed);
	    break;
	case "stopBelt":
	    systems.conveyor.stopBelt();
	    break;
	case "doNothing":
	    systems.conveyor.doNothing();
	    break;
	}
		
	switch(output.visionMethod){
	case "senseTarget":
	    systems.vision.senseTarget();
	    break;
	case "getDistance":
	    systems.vision.getDistance();
	    break;
	case "howCentered":
	    systems.vision.howCentered();
	    break;
	case "doNothing":
	    systems.conveyor.doNothing();
	    break;
	}	
    }
}

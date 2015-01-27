package robot;
import subsystems.*;
import states.*;
import jmraa.Utils;


public class Robot{
    static{System.loadLibrary("jmraa");}

    public StateBase state;
    public static long runTime;
    public InstantiatedSystems systems;

    public Robot(){
	runTime = System.currentTimeMillis();		 
	state = new HopperTest();
	systems = new InstantiatedSystems();
    }

	public Robot(StateBase startingState) {
		runTime = System.currentTimeMillis();
		state = startingState;
	}

    public static void main(String[] args){
		Robot robot;
		if (args.length == 0) {
			robot = new Robot();
		} else {
			//Use first argument to determine test type
			StateBase startState;
			switch (args[0]) {
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

		while(System.currentTimeMillis()-runTime<30000.){
			input = robot.generateInputStateVariables();
			robot.setState(input);
			output = robot.readState(input);
			robot.processOutput(output, input);
			Utils.msleep(10);
		}
    }

    public void addShutdown(){
		Runtime.getRuntime().addShutdownHook(
	    new Thread() {
			public void run() {
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

		switch(output.hopperMethod){
			case "setSorterPosition":
				systems.hopper.setSorterPosition(output.hopperPosition);
				break;
			case "setSorterPositionColor":
				systems.hopper.setSorterPositionColor(input.photoState);
				break;
			case "openLeftHatch":
				systems.hopper.openLeftHatch(output.leftHatchOpen);
				//true opens hatch
				break;
			case "openRightHatch":
				systems.hopper.openRightHatch(output.rightHatchOpen);
				//true opens hatch				
				break;
			case "doNothing":
				systems.hopper.doNothing();
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

package robot;
import subsystems.*;
import states.*;
import jmraa.Utils;


public class Robot{
    static{System.loadLibrary("jmraa");}

    public StateBase state;
    public static long runTime;


    public Robot(){
	runTime = System.currentTimeMillis();		 
	state = new CaptureTest();	
    }

    public static void main(String[] args){
	Robot robot = new Robot();
	InstantiatedSystems systems = robot.startSystems();

	//implement state-system functionality here
	InputStateVariables input;
	OutputStateVariables output;

	while(System.currentTimeMillis()-runTime<30000.){
	    input = robot.generateInputStateVariables(systems);
	    robot.setState(input);
	    output = robot.readState(input);
	    robot.processOutput(output, input, systems);
	    Utils.msleep(10);
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

    public void processOutput (OutputStateVariables output, InputStateVariables input, InstantiatedSystems systems){
	if(output.zeroGyro){
	    systems.zeroGyro();
	}
	switch(output.drivetrainMethod){
	case "pidDrive":
	    systems.drivetrain.pidDrive(0, output.drivetrainSpeed, input.gyroAngle, -1.0/400000, 0, 0);
	    break;
	case "pidDriveTwoInputs":
	    if (input.closerSide.equals("left")){
		systems.drivetrain.pidDriveTwoInputs("left", 0.5, output.drivetrainSpeed,input.leftBackUltraDist,input.leftFrontUltraDist, 1.0/20.0 , 0, 0);
	    }else{
		systems.drivetrain.pidDriveTwoInputs("right", 0.5, output.drivetrainSpeed,input.rightBackUltraDist,input.rightFrontUltraDist, 1.0/20.0 , 0, 0);
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
	    systems.hopper.setSorterPosition(input.photoState);
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

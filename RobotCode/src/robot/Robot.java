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
    public HomeBaseTracker homeBaseTracker;

    public boolean inEnemyZone;
    public boolean colorReadingFlag;

    public Robot(){
	runTime = System.currentTimeMillis();
	state = new SorterColorTest();
	systems = new InstantiatedSystems();
	logger = new RobotLogger();
	inEnemyZone = false;
	colorReadingFlag = false;
    }

    public Robot(StateBase startingState) {
	runTime = System.currentTimeMillis();
	state = startingState;
	systems = new InstantiatedSystems();
	logger = new RobotLogger();
	inEnemyZone = false;

    public boolean isInHomeBase;

    public Robot(){
		this(new ManualTest());
    }

    public Robot(StateBase startingState) {
		runTime = System.currentTimeMillis();
		state = startingState;
		systems = new InstantiatedSystems();
		logger = new RobotLogger();
		isInHomeBase = RobotMap.STARTS_IN_HOME_BASE;
		homeBaseTracker = new HomeBaseTracker(isInHomeBase);
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

	public HomeBaseTracker getHomeBaseTracker() {
	return homeBaseTracker;
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
	switch(output.driveTrainMethod){
	case PID_DRIVE:
	    systems.driveTrain.pidDrive(0, output.driveTrainSpeed, input.gyroAngle, RobotMap.KP_PID_DRIVE, RobotMap.KI_PID_DRIVE, RobotMap.KD_PID_DRIVE);
	    break;
	case PID_DRIVE_TWO_INPUTS:
	    systems.driveTrain.pidDriveTwoInputs(500, output.driveTrainSpeed, input.rightBackIRDist, input.rightFrontIRDist, RobotMap.KP_DOUBLE_PID_DRIVE, RobotMap.KI_DOUBLE_PID_DRIVE, RobotMap.KD_DOUBLE_PID_DRIVE);
	    break;
	case MOVE_STRAIGHT_ROUGH:
	    systems.driveTrain.moveStraightRough(output.driveTrainSpeed);	
	    break;
	case SET_TURN_ROUGH:
	    systems.driveTrain.setTurnRough(output.driveTrainSpeed);
	    break;
	case SET_LEFT_SPEED:
	    systems.driveTrain.setLeftSpeed(output.driveTrainSpeed);
	    break;
	case SET_RIGHT_SPEED:
	    systems.driveTrain.setRightSpeed(output.driveTrainSpeed);
	    break;
	case STOP:
	    systems.driveTrain.stop();
	    break;
	case DO_NOTHING:
	    systems.driveTrain.doNothing();
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
	    /*case SET_SORTER_POSITION_REFINED:
	      systems.sorter.setSorterPositionRefined(output.sorterPosition);
	      break;*/
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
	if(RobotMap.AUTO_SORT){
	    //System.out.println("colorFlag:"+colorReadingFlag);
	    //System.out.println("irReadings:" + systems.sorter.irReadings);
	    //System.out.println("colorReadings:" + systems.sorter.colorReadings);
	    boolean irReading = input.blockIRBoolean;
	    System.out.println("irReading:"+irReading);
	    /*
	    if (!colorReadingFlag) {
		
		systems.sorter.addIRDataPoint(irReading);
		if(systems.sorter.hasIRStreak()){
		    colorReadingFlag = true;
		    //empty ir array and color array
		    systems.sorter.clearColorReadings();
		    systems.sorter.clearIRReadings();
		}else{
		    //this sets back to center position if less than TOTAL_SORT_TIME
		    output.sorterPosition = SorterPosition.MIDDLE;
		}
	    	
	    }
	    else{
	    
		double analogReading = input.photoReading;
		systems.sorter.addColorDataPoint(analogReading);
		if (systems.sorter.hasColorStreak()) {
		    output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
		    BlockColor color = systems.sorter.getLastColor(); //Color of block to be sorted, can be NONE
		    output.sorterPosition = systems.sorter.getSorterPositionForColor(color);  //Which side the sorter should move to (or middle)
		    colorReadingFlag = false;
		    //clear readings
		    systems.sorter.clearColorReadings();
		    systems.sorter.clearIRReadings();
		} else {
		    output.sorterMethod = SorterMethod.DO_NOTHING;
		}

		}*/
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

		int[] lineReadings = input.lineReadings;
		HomeBaseTracker homeBaseTracker = getHomeBaseTracker();
		homeBaseTracker.update(lineReadings);
		isInHomeBase = homeBaseTracker.isInHomeBase();

	}

	//TODO: Line Following
    }
}

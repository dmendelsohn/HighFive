package robot;
import subsystems.*;
import states.*;
import states.test_states.*;
import jmraa.Utils;

import static robot.Enums.*;

public class Robot{

    static{System.loadLibrary("jmraa");}

    public StateBase state;
    public long startTime;
    public InstantiatedSystems systems;
    public RobotLogger logger;
    public HomeBaseTracker homeBaseTracker;

    public boolean colorReadingFlag;    
    public long escapeStartTime; //Since runTime

    //Overall game constants
    public boolean isInHomeBase;
    public Phase phase;
	public BlockColor myColor;

    public Robot(StateBase startingState, BlockColor myColor) {
	state = startingState;
	systems = new InstantiatedSystems(myColor);
	logger = new RobotLogger();
	isInHomeBase = RobotMap.STARTS_IN_HOME_BASE;
	homeBaseTracker = new HomeBaseTracker(isInHomeBase);
	startTime = System.currentTimeMillis();
	escapeStartTime = System.currentTimeMillis();
	this.myColor = myColor;
    }

    public long getRunTime() {
	return System.currentTimeMillis() - startTime;
    }

    public long getEscapeTime() {
	return System.currentTimeMillis() - escapeStartTime;
    }

    public static void main(String[] args){
	Robot robot;
	if (args.length < 2) {
	    System.out.println("Not enough arguments given to main method");
	    System.exit(0);
	    robot = new Robot(new WallFollow(), BlockColor.RED); //This will never happen, it's just to appease the compiler
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

		//my color
		BlockColor inputColor = BlockColor.valueOf(args[1]);
	    robot = new Robot(startState, inputColor);
	}
	robot.addShutdown();
		
	//implement state-system functionality here
	InputStateVariables input;
	OutputStateVariables output;
		
	while(robot.getRunTime() < RobotMap.MAX_RUN_TIME) {
	    input = robot.generateInputStateVariables();
	    robot.updateRobotVariables(input); //This might switch the state if we need to escape from something
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
	    systems.driveTrain.pidDrive(output.driveTrainPidAngle, output.driveTrainSpeed, input.gyroAngle, RobotMap.KP_PID_DRIVE, RobotMap.KI_PID_DRIVE, RobotMap.KD_PID_DRIVE);
	    break;
	case PID_DRIVE_TWO_INPUTS:
	    systems.driveTrain.pidDriveTwoInputs(500, output.driveTrainSpeed, input.rightBackIRDist, input.rightFrontIRDist, RobotMap.KP_DOUBLE_PID_DRIVE, RobotMap.KI_DOUBLE_PID_DRIVE, RobotMap.KD_DOUBLE_PID_DRIVE);
	    break;
	case VISION_TURN:
	    systems.driveTrain.pidDrive(input.howCentered*30-15, output.driveTrainSpeed, input.gyroAngle, RobotMap.KP_PID_TURN, RobotMap.KI_PID_TURN, RobotMap.KD_PID_TURN);
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
	    systems.hopper.setBoth(output.hopperLeftOpen, output.hopperRightOpen);
	    break;
	case MOVE_LEFT:
	    systems.hopper.setLeft(output.hopperLeftOpen);
	    //true opens hatch
	    break;
	case MOVE_RIGHT:
	    systems.hopper.setRight(output.hopperRightOpen);
	    //true opens hatch				
	    break;
	case DO_NOTHING:
	    systems.hopper.doNothing();
	    break;
	}	

	switch(output.sorterMethod){
	case SET_SORTER_POSITION:
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

    private void updateRobotVariables(InputStateVariables input) {
	//Home base tracking
	int[] lineReadings = input.lineReadings;
	HomeBaseTracker homeBaseTracker = getHomeBaseTracker();
	homeBaseTracker.update(lineReadings);
	this.isInHomeBase = homeBaseTracker.isInHomeBase();
	input.isInHomeBase = homeBaseTracker.isInHomeBase();

	input.runTime = getRunTime();

	if (getEscapeTime() > RobotMap.ESCAPE_TIMEOUT && input.rightFrontContact) {
	    System.out.println("Detected bumper contact");
	    escapeStartTime = System.currentTimeMillis();
	    StateBase returnState = state;
	    this.state = new Escape(returnState); //will return to current state after turning away from wall
	}
    }

    private void addPassiveOutputs(OutputStateVariables output, InputStateVariables input) {
	output.conveyorMethod = ConveyorMethod.MOVE_BELT;
	output.conveyorSpeed = 0.2;

	if(RobotMap.AUTO_SORT){
	    boolean irReading = input.blockIRBoolean;

	    if (System.currentTimeMillis() - systems.sorter.getLastMovementTime() > RobotMap.THERE_SORT_TIME) {
		output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
		output.sorterPosition = SorterPosition.MIDDLE;
	    }
	    if(System.currentTimeMillis() - systems.sorter.getLastMovementTime() > RobotMap.TOTAL_SORT_TIME){
		output.conveyorMethod = ConveyorMethod.MOVE_BELT;
		output.conveyorSpeed = .2;
	    }else{
		output.conveyorMethod = ConveyorMethod.STOP_BELT;
	    }

	    double analogReading = input.photoReading;
	    systems.sorter.addColorDataPoint(analogReading);
	    System.out.println("Color analog reading: " + analogReading);
	    if(systems.sorter.hasAnyColor()){
		output.conveyorMethod = ConveyorMethod.STOP_BELT;
	    }
	    if (systems.sorter.hasColorStreak()) {
		output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
		BlockColor color = systems.sorter.getLastColor(); //Color of block to be sorted, can be NONE
		System.out.println("Color streak of color: " + color.name());
		output.sorterPosition = systems.sorter.getSorterPositionForColor(color);  //Which side the sorter should move to (or middle)
		//colorReadingFlag = false;
		//clear readings
		//systems.sorter.clearColorReadings();
		//systems.sorter.clearIRReadings();
	    } else {
		output.sorterMethod = SorterMethod.DO_NOTHING;
	    }


	    /*	    if (!colorReadingFlag) {
		    systems.sorter.addIRDataPoint(irReading);
		    if(systems.sorter.hasIRStreak()){
		    colorReadingFlag = true;
		    //empty ir array and color array
		    systems.sorter.clearColorReadings();
		    systems.sorter.clearIRReadings();
		    }
		
		    if(systems.sorter.hasAnyIR()){
		    output.conveyorMethod = ConveyorMethod.STOP_BELT;
		    }
		    } else { //We've got a block, got to determine color
	    
		    boolean isRed = input.isRed;
		    systems.sorter.addColorDataPoint(isRed);
		    System.out.println("is red?: " + isRed);
		    if (systems.sorter.hasColorStreak()) {
		    output.sorterMethod = SorterMethod.SET_SORTER_POSITION;
		    BlockColor color = systems.sorter.getLastColor(); //Color of block to be sorted, can be NONE
		    System.out.println("Color streak of color: " + color.name());
		    output.sorterPosition = systems.sorter.getSorterPositionForColor(color);  //Which side the sorter should move to (or middle)
		    colorReadingFlag = false;
		    //clear readings
		    systems.sorter.clearColorReadings();
		    systems.sorter.clearIRReadings();
		    } else {
		    output.sorterMethod = SorterMethod.DO_NOTHING;
		    }

		    }*/
	}

    }
}

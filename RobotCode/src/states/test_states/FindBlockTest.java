package states.test_states;
import states.*;
import robot.*;

import static robot.Enums.*;

public class FindBlockTest extends StateBase{
	public enum FindBlockState { WAIT, UPDATE, DRIVE }

	public static long WAIT_TIME = 1500;
	public static long DRIVE_TIME = 1000;

	private long cycleStartTime; //millis since state started
	private FindBlockState findBlockState;
	private boolean exitState;


    public FindBlockTest(){
		super();
		cycleStartTime = 0;
		findBlockState = FindBlockState.WAIT;
		exitState = false;
		System.out.println("Constructing FindBlockTest()");
	}

    public OutputStateVariables run(InputStateVariables input) {
		OutputStateVariables output = getDefaultOutput();
		long elapsedTime = getElapsedTime();
		long cycleTime = elapsedTime - cycleStartTime;

		switch (findBlockState) {
			case WAIT:
				if (cycleTime > WAIT_TIME) {
					findBlockState = FindBlockState.UPDATE;
					System.out.println("Transitioning to Update");
				}
				output.driveTrainMethod = DriveTrainMethod.STOP;
				output.zeroGyro = true;
				break;
			case UPDATE:
				if (input.seesTarget) {
					double angle = input.targetAngle;
					double driveSpeed = 0.2; //Math.max(0.2, 0.1 + 0.2*input.boxDistance);
					output.driveTrainMethod = DriveTrainMethod.PID_DRIVE;
					output.driveTrainSpeed = driveSpeed;
					output.driveTrainPidAngle = angle;
					findBlockState = FindBlockState.DRIVE;
					System.out.println("Driving...speed:  " + output.driveTrainSpeed + ", angle: " + output.driveTrainPidAngle);
				} else {
					exitState = true;
					System.out.println("Going to exit FindBlockTest");
					output.driveTrainMethod = DriveTrainMethod.STOP;
				}
				break;
			case DRIVE:
				if (cycleTime > WAIT_TIME + DRIVE_TIME) { //New cycle begins
					System.out.println("Finished a FindBlock cycle");
					cycleStartTime = elapsedTime;
					output.driveTrainMethod = DriveTrainMethod.STOP;
					findBlockState = FindBlockState.WAIT;
				} else {
					//Keep driving, do nothing
				}
				break;
		}
		return output;
	}

    public StateBase getNext(InputStateVariables input){
		if (exitState) {
			return new FindWall();
		} else {
			return this;
		}
    }
}
